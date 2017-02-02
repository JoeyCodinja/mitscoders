package mits.uwi.com.ourmobileenvironment.models.Home;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;


import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by Danuel on 11/8/2015.
 */
public class Home_News {

    private final String TAG = "Home_News";
    private Document RSS1, RSS2;
    private String[] RSS_feeds = {"https://www.mona.uwi.edu/marcom/newsroom/feed",
                                  "https://www.mona.uwi.edu/marcom/uwinotebook/feed"};
    public boolean didConnect;
    public boolean loaded;
    private Context calledContext;
    //Default Constructor

    public Home_News(Context context) throws UnknownHostException {

        calledContext = context;
        try{
            RSS1 = Jsoup.connect(RSS_feeds[0]).get();
            RSS2 = Jsoup.connect(RSS_feeds[1]).get();
            didConnect = true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            Log.e(TAG, e.getClass() + e.getMessage());
            Toast.makeText(context,
                    "Unable to retrieve news items. Please check internet connection",
                    Toast.LENGTH_LONG).show();
            didConnect = false;
            loaded = false;
        }
    }

    public Elements getNewsItems(){
        Elements newsItems = new Elements();

        if (RSS1 != null && RSS2 != null){
            for (Element item: RSS1.getElementsByTag("item")){
                newsItems.add(item);
            }
            for (Element item: RSS2.getElementsByTag("item")){
                newsItems.add(item);
            }
        }
        loaded = true;
        return newsItems;
    }

    public String getNewsItemURL(Element newsItem){
        Elements newsItemURL = newsItem.getElementsByTag("link");
        return newsItemURL.get(0).text();
    }

    public Bitmap getNewsItemImage(Element newsItem){
        Bitmap image = null;
        Document newsItemXMLData;
        String newsItemDataString = newsItem.getElementsByTag("description")
                                .get(0).toString();

        try{
            newsItemXMLData = Jsoup.parse(Parser.unescapeEntities(newsItemDataString, false));
            // TODO: Ensure that HTTP 302 request are resolved
            Element newsImageTag = newsItemXMLData.getElementsByTag("img")
                    .get(0);
            InputStream in = new java.net.URL(newsImageTag.attributes().get("src"))
                    .openStream();
            image = BitmapFactory.decodeStream(in);
            if (image == null){
                String otherDomain = newsImageTag.attributes().get("src");
                otherDomain = otherDomain.replace("http://myspot.mona.uwi.edu", "https://www.mona.uwi.edu");
                in = new java.net.URL(otherDomain).openStream();
                image = BitmapFactory.decodeStream(in);
                if (image == null){
                    throw new IOException("Haven't loaded image");
                }
            }
        }
        catch (IndexOutOfBoundsException|IOException e){
            try{
                image = BitmapFactory.decodeResource(calledContext.getResources(),
                        R.drawable.untitled);
            }
            catch (Exception ex){
                Log.e(TAG, ex.getClass() + "\n" + ex.getMessage());
                e.printStackTrace();
            }
        }
        return image;
    }

    public String getNewsItemTitle(Element newsItem){
        Elements newsItemTitle = newsItem.getElementsByTag("title");
        //Item tag should have within it only one title
        return newsItemTitle.get(0).text();
    }

    public ArrayList<String> getNewsItemArticle(Element newsItem){

        Document article;
        ArrayList<String> newsContentParagraph = new ArrayList<>();

        try {
            article = Jsoup.connect(getNewsItemURL(newsItem)).get();
        } catch (IOException e) {
            return newsContentParagraph;
        } catch (IllegalArgumentException e){
            return newsContentParagraph;
        }

        Elements articleContent = article.getElementById("mainContent").getAllElements();
        articleContent = articleContent.get(2).getElementsByClass("content").get(0).getElementsByTag("p");

        for (Element paragraph: articleContent){
            newsContentParagraph.add(paragraph.text().trim());
        }

        return newsContentParagraph;
    }

    public ArrayList<String> getNewsItemDescription(Element newsItem) {
        Document descriptionHTML;
        String descriptionHTMLString;
        ArrayList<String> descriptionParagraphs = new ArrayList<>();
        Elements newsItemDescription = newsItem.getElementsByTag("description");

        descriptionHTMLString = newsItemDescription.get(0).text();
        descriptionHTML = Jsoup.parse(descriptionHTMLString);
        for (Element paragraph: descriptionHTML.getElementsByTag("p")){
            if (paragraph.text().contains("read more") || paragraph.text().isEmpty())
                continue;
            descriptionParagraphs.add(paragraph.text().trim());
        }

        return descriptionParagraphs;
    }

    public boolean cacheNewsItems(Elements newsItems) throws IOException{
        /* Caches the elements of the RSS feeds that are being displayed */
        File cacheDir = new File(calledContext.getCacheDir(), "newsItems");
        FileOutputStream cacheOutput = new FileOutputStream(cacheDir);
        try {
            cacheOutput.write(newsItems.toString().getBytes());
            cacheOutput.close();
        }
        catch(IOException e){
            Log.e(TAG, e.getClass() + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Elements getCachedNewsItems(){
        /** Will determine if the cache is to be
        retrieved or if there should be a call cacheNewsItems **/
        String cacheString = new String();
        byte[] cacheBuffer = new byte[512];
        Document cachedXMLFile;

        //Retrieve from the internal cache dir
        try {
            File cacheFile = new File(calledContext.getCacheDir(), "newsItems");
            FileInputStream cacheRead = new FileInputStream(cacheFile);
            while(cacheRead.read(cacheBuffer, 0, 512) != -1){
               cacheString +=  new String(cacheBuffer);
            }
        }
        catch(FileNotFoundException fnf){
            Log.e(TAG, fnf.getClass() + fnf.getMessage());
            fnf.printStackTrace();
        }
        catch(IOException io){
            io.printStackTrace();
            Log.e(TAG, io.getClass() + io.getMessage());
        }
        cachedXMLFile = Jsoup.parse(cacheString);
        return cachedXMLFile.getElementsByTag("item");
    }
}
