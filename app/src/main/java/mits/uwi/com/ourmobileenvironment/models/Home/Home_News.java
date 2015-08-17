package mits.uwi.com.ourmobileenvironment.models.Home;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.jsoup.select.Elements;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.MalformedURLException;

import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by Danuel on 11/8/2015.
 */
public class Home_News {

    private Document RSS1, RSS2;
    private String[] RSS_feeds = {"http://www.mona.uwi.edu/marcom/newsroom/feed",
                                  "http://www.mona.uwi.edu/marcom/uwinotebook/feed"};

    //Default Constructor
    public Home_News(){
        try{
            RSS1 = Jsoup.connect(RSS_feeds[0]).get();
            RSS2 = Jsoup.connect(RSS_feeds[1]).get();
        }
        catch(IOException e)
        {
            RSS1 = null;
            RSS2 = null;
            System.console().printf("There seems to be a retrieving the files");
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
        return newsItems;
    }

    public Bitmap getNewsItemImage(Element newsItem){
        Bitmap image = null;
        //Alternate
        String newsItemImageURL2 = null;
        Document newsItemXMLData = null;
        String newsItemDataString = newsItem.getElementsByTag("description")
                                .get(0).toString();

        try{
            newsItemXMLData = Jsoup.parse(Parser.unescapeEntities(newsItemDataString, false));
            Element newsItemImage3 = newsItemXMLData.getElementsByTag("img")
                    .get(0);
            InputStream in = new java.net.URL(newsItemImage3.attributes().get("src"))
                    .openStream();
            image = BitmapFactory.decodeStream(in);

        }
        catch (Exception e){
            try{
                image = BitmapFactory.decodeResource(Resources.getSystem(),
                        R.drawable.untitled);
            }
            catch (Exception ex){
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

    public String getNewsItemDescription(Element newsItem) {

        Document descriptionHTML;
        String descriptionHTMLString;
        Elements newsItemDescription = newsItem.getElementsByTag("description");

        descriptionHTMLString = newsItemDescription.get(0).text();
        descriptionHTML = Jsoup.parse(descriptionHTMLString);

        return descriptionHTML.getElementsByTag("p").get(0).text();
    }

}
