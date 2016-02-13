package mits.uwi.com.ourmobileenvironment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;

public class NewsViewActivity extends AppCompatActivity {

    private String newsDescription;
    private String newsTitle;
    private Bitmap newsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        byte[] newsByteArray = getIntent().getByteArrayExtra("news_images");
        newsImage = BitmapFactory.decodeByteArray(newsByteArray, 0, newsByteArray.length);
        newsDescription = getIntent().getStringExtra("news_body");
        newsTitle = getIntent().getStringExtra("news_title");

        DocumentView newsBodyView = (DocumentView)findViewById(R.id.news_body);
        TextView newsTitleView = (TextView)findViewById(R.id.news_title);
        ImageView newsImageView = (ImageView)findViewById(R.id.news_image);

        newsBodyView.setText(newsDescription);
        newsTitleView.setText(newsTitle);

        newsImageView.setImageBitmap(newsImage);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_view, menu);
        return true;
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
