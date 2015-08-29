package mits.uwi.com.ourmobileenvironment.homepagefragments;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.TextView;

import org.apache.http.conn.ConnectTimeoutException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

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
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_news, container, false);

                ImageView fabButton = (ImageView) v.findViewById(R.id.landing_pageFAB);

        new RetrieveRSSFeedTask().execute(getActivity());
        return v;
    }

    public class RetrieveRSSFeedTask extends AsyncTask<Context, Void, Home_News>{
        ArrayList<Bitmap> newsItemImages = new ArrayList<>();
        ArrayList<String> newsItemTitles = new ArrayList<>();

        @Override
        protected Home_News doInBackground(Context... params) {
            try {
                mNewsItems = new Home_News(params[0]);
                if (!mNewsItems.didConnect){
                    for(Element element: mNewsItems.getCachedNewsItems()){
                        newsItemTitles.add(mNewsItems.getNewsItemTitle(element));
                        newsItemImages.add(mNewsItems.getNewsItemImage(element));
                    }
                }
                else{
                    Elements newsItemElements = mNewsItems.getNewsItems();
                    for (Element element: newsItemElements){
                        newsItemTitles.add(mNewsItems.getNewsItemTitle(element));
                        newsItemImages.add(mNewsItems.getNewsItemImage(element));
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
            LayoutInflater layoutInflater = (LayoutInflater)  getActivity()
                                                            .getBaseContext()
                                                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            for (int i=0;i<newsItemTitles.size();i++) {
                View newsCard = layoutInflater.inflate(R.layout.news_card, null);
                ImageView newsCardImage = (ImageView)newsCard.findViewById(R.id.news_card_image);
                TextView newsCardTitle = (TextView)newsCard.findViewById(R.id.news_card_title);

                newsCard.setLayoutParams(new CardView.
                                             LayoutParams(columns[i % 2].getWidth(),
                                             320));

                newsCardImage.setImageBitmap(newsItemImages.get(i));

                newsCardTitle.setText(newsItemTitles.get(i));
                newsCardTitle.setBackground(contextView.getResources().getDrawable(R.drawable.text_shadow));

                columns[i % 2].addView(newsCard);
//                ImageView newsCardImage = new ImageView(getActivity());
//                TextView newsCardTitle = new TextView(getActivity());
//
//                newsCard.setLayoutParams(new CardView.
//                        LayoutParams(columns[i % 2]
//                        .getWidth(), 320));
//                newsCardImage.setLayoutParams(new CardView.
//                        LayoutParams(CardView.LayoutParams.MATCH_PARENT,
//                        CardView.LayoutParams.MATCH_PARENT));
//                newsCardImage.setImageBitmap(newsItemImages.get(i));
//                newsCardImage.setScaleType(ImageView.ScaleType.FIT_XY);
//                newsCard.addView(newsCardImage);
//
//                newsCardTitle.setText(newsItemTitles.get(i));
//                CardView.LayoutParams layoutParams = new CardView.
//                        LayoutParams(CardView.LayoutParams.WRAP_CONTENT,
//                        CardView.LayoutParams.WRAP_CONTENT);
//                layoutParams.gravity = Gravity.BOTTOM;
//                newsCardTitle.setLayoutParams(layoutParams);
//                newsCardTitle.setGravity(Gravity.BOTTOM);
//                newsCardTitle.setTextColor(Color.argb(255, 215, 218, 219));
//                newsCardTitle.setPadding(16, 0, 16, 0);
//                newsCard.addView(newsCardTitle);

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
