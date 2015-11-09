package mits.uwi.com.ourmobileenvironment.homepagefragments;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.conn.ConnectTimeoutException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.models.Home.Home_News;

public class HomeNewsFragment extends Fragment {

    private ArrayList<CardView> mNewsCards = new ArrayList<>();
    private Home_News mNewsItems;
    private ArrayList<String> newsTitleStrings = new ArrayList<>();
    private String[] newsDescriptionStrings = new String[50];

    public static HomeNewsFragment newInstance() {
        HomeNewsFragment fragment = new HomeNewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_news, container, false);

        LinearLayout[] columns = {(LinearLayout) v.findViewById(R.id.newsColumn1),
                                  (LinearLayout) v.findViewById(R.id.newsColumn2)};

        for (int object=0; object <= 10; object++){

            CardView newsCard = (CardView)inflater.inflate(R.layout.news_card, null);

            ProgressBar indeterminateProgress  = new ProgressBar(container.getContext());
            indeterminateProgress.setIndeterminate(true);
            newsCard.addView(indeterminateProgress);

            columns[object%2].addView(newsCard);
        }

        new RetrieveRSSFeedTask().execute(getActivity());
        return v;
    }

    public class RetrieveRSSFeedTask extends AsyncTask<Context, Void, Home_News>{
        ArrayList<Bitmap> newsItemImages = new ArrayList<>();
        ArrayList<String> newsItemTitles = new ArrayList<>();
        ArrayList<String> newsItemURLs = new ArrayList<>();
        @Override
        protected Home_News doInBackground(Context... params) {
            try {
                mNewsItems = new Home_News(params[0]);
                if (!mNewsItems.didConnect){
                    for(Element element: mNewsItems.getCachedNewsItems()){
                        newsItemTitles.add(mNewsItems.getNewsItemTitle(element));
                        newsItemImages.add(mNewsItems.getNewsItemImage(element));
                        newsItemURLs.add(mNewsItems.getNewsItemURL(element));
                    }
                }
                else{
                    Elements newsItemElements = mNewsItems.getNewsItems();
                    for (Element element: newsItemElements){
                        newsItemTitles.add(mNewsItems.getNewsItemTitle(element));
                        newsItemImages.add(mNewsItems.getNewsItemImage(element));
                        newsItemURLs.add(mNewsItems.getNewsItemURL(element));
                    }
                    mNewsItems.cacheNewsItems(newsItemElements);
                }
                return mNewsItems;
            }
            catch(Exception e)
            {
                Log.e("HomeNewsFragment", e.getClass() + "\n" + e.getMessage());
                e.printStackTrace();
                return mNewsItems;
            }

        }



        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        private void makeNewsItemViews(View contextView) {

            LinearLayout[] columns = {(LinearLayout) contextView.findViewById(R.id.newsColumn1),
                    (LinearLayout) contextView.findViewById(R.id.newsColumn2)};
            LayoutInflater layoutInflater = (LayoutInflater) getActivity()
                                                            .getBaseContext()
                                                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (LinearLayout column: columns){
                column.removeAllViewsInLayout();
            }

            for (int i = 0; i < newsItemTitles.size(); i++) {
                // Remove dummy views from both columns

                final int finalI = i;
                final Context finalContext = contextView.getContext();
                View newsCard = layoutInflater.inflate(R.layout.news_card, null);
                ImageView newsCardImage = (ImageView)newsCard.findViewById(R.id.news_card_image);
                TextView newsCardTitle = (TextView)newsCard.findViewById(R.id.news_card_title);

                newsCard.setLayoutParams(new CardView.
                        LayoutParams(columns[i % 2].getWidth(),
                        320));

                newsCardImage.setImageBitmap(newsItemImages.get(i));

                newsCardTitle.setText(newsItemTitles.get(i));
                newsCardTitle.setBackground(contextView.getResources().getDrawable(R.drawable.text_shadow));

                newsCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri articleURI = Uri.parse(newsItemURLs.get(finalI));

                        Intent viewArticleIntent = new Intent(Intent.ACTION_VIEW, articleURI);

                        //Ensure that there are apps to service our intent
                        PackageManager packageManager = finalContext.getPackageManager();
                        List<ResolveInfo> activities = packageManager.queryIntentActivities(viewArticleIntent, 0);

                        Intent viewArticleChooser = Intent.createChooser(viewArticleIntent,
                                getResources().getString(R.string.view_article_string));

                        boolean intentSafe = activities.size() > 0;

                        // If intent is serviceable then go ahead and start the activity
                        if (intentSafe){
                            startActivity(viewArticleIntent);
                        }
                    }
                });
                columns[i % 2].addView(newsCard);

            }
        }

        @Override
        protected void onPostExecute(Home_News home_news) {

            View parentView = getView();
            if (mNewsItems.didConnect){
                Elements newsItems = mNewsItems.getNewsItems();
            }
            makeNewsItemViews(parentView);
        }
    }
}
