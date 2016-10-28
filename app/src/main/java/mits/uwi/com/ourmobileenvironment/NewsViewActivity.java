package mits.uwi.com.ourmobileenvironment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.IDocumentLayout;
import com.bluejamesbond.text.StringDocumentLayout;
import com.bluejamesbond.text.style.TextAlignment;

import java.util.ArrayList;

public class NewsViewActivity extends AppCompatActivity {

    private ArrayList<String> newsDescription;
    private String newsTitle;
    private Bitmap newsImage;
    private static DisplayMetrics displayMetrics = new DisplayMetrics();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        byte[] newsByteArray = getIntent().getByteArrayExtra("news_images");
        newsImage = BitmapFactory.decodeByteArray(newsByteArray, 0, newsByteArray.length);
        newsDescription = getIntent().getStringArrayListExtra("news_body");
        newsTitle = getIntent().getStringExtra("news_title");

        TextView newsTitleView = (TextView)findViewById(R.id.news_title);
        ImageView newsImageView = (ImageView)findViewById(R.id.news_image);
        ScrollView scrollParent = (ScrollView)findViewById(R.id.newsViewScrollParent);

        LinearLayout paragraphContainer = new LinearLayout(this);
        paragraphContainer.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams paragraphLayoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        for(String newsBodyParagraphs: newsDescription){
            DocumentView newsParagraph = new DocumentView(this);

            IDocumentLayout.LayoutParams docLayout =
                    newsParagraph.getDocumentLayoutParams();

            docLayout.setAntialias(true);
            docLayout.setTextAlignment(TextAlignment.JUSTIFIED);
            docLayout.setTextSize(14);
            int insetPadding = dpToPx(10);
            docLayout.setInsetPaddingLeft(insetPadding);
            docLayout.setInsetPaddingTop(insetPadding);
            docLayout.setInsetPaddingRight(insetPadding);
            docLayout.setInsetPaddingBottom(insetPadding);

            newsParagraph.setLayoutParams(paragraphLayoutParams);

            newsParagraph.setText(newsBodyParagraphs);
            // Add each new paragraph to the news view
            paragraphContainer.addView(newsParagraph);
        }
        scrollParent.addView(paragraphContainer);
        newsTitleView.setText(newsTitle);

        newsImageView.setImageBitmap(newsImage);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_view, menu);
        return true;
    }

    private int dpToPx(int dp_value){
        return Math.round(dp_value * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
