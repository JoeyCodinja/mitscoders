package mits.uwi.com.ourmobileenvironment.homepagefragments;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.widget.LinearLayout.LayoutParams;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.lang.Math;

import mits.uwi.com.ourmobileenvironment.NewsViewActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.models.Home.Home_News;

public class HomeNewsFragment extends Fragment {

    private ArrayList<CardView> mNewsCards = new ArrayList<>();
    private Home_News mNewsItems;
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_news, container, false);

        LinearLayout[] columns = {(LinearLayout) v.findViewById(R.id.newsColumn1),
                                  (LinearLayout) v.findViewById(R.id.newsColumn2)};
        for (LinearLayout column: columns){
            column.setVisibility(View.GONE);
        }

        LinearLayout columnParent = (LinearLayout) columns[0].getParent();

        TextView loadingText = new TextView(columnParent.getContext());
        loadingText.setText("Loading the Lastest News Stories");
        loadingText.setGravity(Gravity.CENTER);
        loadingText.setId(R.id.loadingTextView);

        columnParent.addView(loadingText);

        loadingText.animate()
                .alpha(0)
                .setDuration(1000);
        loadingText.animate()
                .alpha(1)
                .setDuration(1000).start();


        new RetrieveRSSFeedTask().execute(getActivity());
        return v;
    }

    public class RetrieveRSSFeedTask extends AsyncTask<Context, Void, Home_News>{
        ArrayList<Bitmap> newsItemImages = new ArrayList<>();
        ArrayList<String> newsItemTitles = new ArrayList<>();
        ArrayList<String> newsItemURLs = new ArrayList<>();
        ArrayList<String> newsItemDescriptions = new ArrayList<>();
        @Override
        protected Home_News doInBackground(Context... params) {
            try {
                mNewsItems = new Home_News(params[0]);
                if (!mNewsItems.didConnect || mNewsItems.loaded){
                    for(Element element: mNewsItems.getCachedNewsItems()){
                        newsItemTitles.add(mNewsItems.getNewsItemTitle(element));
                        newsItemImages.add(mNewsItems.getNewsItemImage(element));
                        newsItemURLs.add(mNewsItems.getNewsItemURL(element));
                        newsItemDescriptions.add(mNewsItems.getNewsItemDescription(element));
                    }
                }
                else{
                    Elements newsItemElements = mNewsItems.getNewsItems();
                    for (Element element: newsItemElements){
                        newsItemTitles.add(mNewsItems.getNewsItemTitle(element));
                        newsItemImages.add(mNewsItems.getNewsItemImage(element));
                        newsItemURLs.add(mNewsItems.getNewsItemURL(element));
                        newsItemDescriptions.add(mNewsItems.getNewsItemDescription(element));
                    }
                    mNewsItems.cacheNewsItems(newsItemElements);
                    mNewsItems.loaded = true;
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


            LinearLayout newsHeader = (LinearLayout) contextView.findViewById(R.id.newsHeader);

            LayoutInflater layoutInflater = (LayoutInflater) getActivity()
                                                            .getBaseContext()
                                                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
            // Removes the Loading screen
            LinearLayout loadingTextViewParent = (LinearLayout)columns[0].getParent();

            loadingTextViewParent.removeView(loadingTextViewParent.findViewById(R.id.loadingTextView));


            for (LinearLayout column: columns){
                column.setVisibility(View.VISIBLE);
            }

            for (int i = 0; i < newsItemTitles.size(); i++) {
                // Remove dummy views from both columns

                final int finalI = i;
                final Context finalContext = contextView.getContext();


                View newsCard = layoutInflater.inflate(R.layout.news_card, null);
                ImageView newsCardImage = (ImageView)newsCard.findViewById(R.id.news_card_image);
                TextView newsCardTitle = (TextView)newsCard.findViewById(R.id.news_card_title);


                float imageWidth = newsItemImages.get(i).getWidth();
                float imageHeight = newsItemImages.get(i).getHeight();

                if (i == 0){
                    newsCard.setLayoutParams(new CardView.LayoutParams(newsHeader.getWidth(),
                                                                       400));
                    newsCardImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }

                else if (imageWidth/imageHeight <= 1.8 &&
                         imageWidth/imageHeight >= 1){
                    // 4:3 image
//                    newsCard.setLayoutParams(new CardView.LayoutParams(columns[i % 2].getWidth(),
//                                             320));
                    newsCardImage.setScaleType(ImageView.ScaleType.FIT_XY);
                }

                else if (newsItemImages.get(i).getWidth() < newsItemImages.get(i).getHeight()){
                    // Vertical Rectangle
                    if (imageHeight <= 100 || imageWidth <=100) {
//                        newsCard.setLayoutParams(new CardView.LayoutParams(columns[i % 2].getWidth(),
//                                columns[i % 2].getWidth()));
                        newsCardImage.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
//                    else
//                        newsCard.setLayoutParams(new CardView.LayoutParams(columns[i % 2].getWidth(),
//                                             newsItemImages.get(i).getHeight()));
                }

                newsCardImage.setImageBitmap(newsItemImages.get(i));

                if (newsItemTitles.get(i).length() <=25)
                    newsCardTitle.setText(newsItemTitles.get(i));
                else
                    newsCardTitle.setText(newsItemTitles.get(i).substring(0,
                                          (int)Math.floor(newsItemTitles.get(i).length() * 0.5)) + "...");

                newsCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent viewArticleIntent = new Intent(getActivity(), NewsViewActivity.class);

                        viewArticleIntent.putExtra("news_body", newsItemDescriptions.get(finalI));
                        viewArticleIntent.putExtra("news_title", newsItemTitles.get(finalI));

                        ByteArrayOutputStream transferrableBitmap = new ByteArrayOutputStream();
                        newsItemImages.get(finalI).compress(Bitmap.CompressFormat.PNG,
                                100,
                                transferrableBitmap);
                        byte[] imageInBytes = transferrableBitmap.toByteArray();

                        viewArticleIntent.putExtra("news_images", imageInBytes);

                        startActivity(viewArticleIntent);
                    }
                });


                if (i == 0)
                    newsHeader.addView(newsCard);
                else
                    columns[i % 2].addView(newsCard);

                ViewGroup.LayoutParams layoutparams = newsCard.getLayoutParams();
                LayoutParams params = (LayoutParams) layoutparams;

                params.setMargins(5,0,5,10);
                newsCard.setLayoutParams(params);
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
