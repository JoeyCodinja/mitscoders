package mits.uwi.com.ourmobileenvironment.homepagefragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.models.Home.Home_News;

public class HomeNewsFragment extends Fragment {

    private ArrayList<CardView> mNewsCards = new ArrayList<>();
    private Home_News mNewsItems;
    private ArrayList<String> newsTitleStrings = new ArrayList<>();
    private String[] newsDescriptionStrings = new String[50];

    PropertyChangeEvent mTitleStringWatcher = new PropertyChangeEvent(newsTitleStrings, "length", newsTitleStrings.size(), newsTitleStrings.size());

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

        LinearLayout column1 = (LinearLayout) v.findViewById(R.id.newsColumn1);
        LinearLayout column2 = (LinearLayout) v.findViewById(R.id.newsColumn2);
        LinearLayout[] columns = {column1, column2};
        new RetrieveRSSFeedTask().execute("");

        for (int i=0;
             i < 25;
             i++){
            CardView cardNewsItem = (CardView) new CardView(getActivity()).inflate(getActivity(), R.layout.news_item_layout, null);
            TextView newsCardTitle = (TextView) cardNewsItem.findViewById(R.id.news_item_card_title);
            newsCardTitle.setId(i);
            mNewsCards.add(cardNewsItem);
            columns[i%columns.length].addView(cardNewsItem);
        }
        return v;
    }

    public class RetrieveRSSFeedTask extends AsyncTask<String, Void, Home_News>{
        ArrayList<Bitmap> newsItemImages = new ArrayList<>();
        ArrayList<String> newsItemTitles = new ArrayList<>();

        @Override
        protected Home_News doInBackground(String... params) {
            try {
                mNewsItems = new Home_News();
                for (Element element: mNewsItems.getNewsItems()){
                    newsItemTitles.add(mNewsItems.getNewsItemTitle(element));
                    newsItemImages.add(mNewsItems.getNewsItemImage(element));
                }
                return mNewsItems;
            }
            catch(Exception e)
            {
                mNewsItems = null;
                Log.e("HomeNewsFragment", e.getMessage());
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(Home_News home_news) {
            if (mNewsItems != null){

                Elements newsItems = mNewsItems.getNewsItems();

                for (Element newsItem: newsItems){
                    ImageView newsItemCardImage = (ImageView) mNewsCards.get(newsItems
                            .indexOf(newsItem)).getChildAt(0);
                    TextView newsItemCardTitle = (TextView) mNewsCards.get(newsItems.
                            indexOf(newsItem)).getChildAt(1);
                    newsItemCardImage.setImageBitmap(newsItemImages.get(newsItems.indexOf(newsItem)));
                    newsItemCardTitle.setText(newsItemTitles.get(newsItems.indexOf(newsItem)));
                }

            }

        }
    }
}
