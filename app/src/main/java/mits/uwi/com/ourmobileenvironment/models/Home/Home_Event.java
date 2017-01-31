package mits.uwi.com.ourmobileenvironment.models.Home;

import android.content.Context;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Created by Danuel on 27/1/2017.
 */
public class Home_Event {
    private final String TAG = "Home_Event";
    private String[] eventFeeds = {"http://www.mona.uwi.edu/marcom/ecalendar/feed"};
    private static Context classContext;
    private ArrayList<Document> RSSFeedDocuments = new ArrayList<>();

    public Home_Event(Context context){
        classContext = context;

        for (String feed: eventFeeds){
            try{
                RSSFeedDocuments.add(Jsoup.connect(feed).get());
            } catch(IOException e){
                e.printStackTrace();
                Log.e(TAG, e.getClass() + e.getMessage());
                throw new IOError(e);
            }
        }
    }

    public Elements getEventItems(){
        Elements eventItems = new Elements();

        if (RSSFeedDocuments.size() == 0){

        } else {
            for (Document document: RSSFeedDocuments){
                Elements items = document.getElementsByTag("item");
                for (Element item: items){
                    eventItems.add(item);
                }
            }
        }
        return eventItems;
    }

    public String getEventTitle(Element eventItem){
        String title;

        title = eventItem.getElementsByTag("title").get(0).text();

        return title;
    }

    public String getEventAudience(Element eventItem){
        String audience = null;

        String descriptionBlock = eventItem
                .getElementsByTag("description")
                .get(0)
                .text();
        Document descriptionData = Jsoup.parse(Parser.unescapeEntities(descriptionBlock, false));

        Element audienceData = descriptionData
                .getElementsByClass("field-field-target-audience")
                .get(0);

        for (Element givenAudience: audienceData.getElementsByClass("field-item")){
            if (audience != null)
                audience = audience + " " + givenAudience.text();
            else
                audience = givenAudience.text();

        }

        return audience;
    }

    public String getEventVenue(Element eventItem){
        String venue;

        String descriptionBlock = eventItem.getElementsByTag("description").get(0).text();
        Document descriptionData = Jsoup.parse(Parser.unescapeEntities(descriptionBlock, false));

        Element venueData = descriptionData.getElementsByClass("field-field-venue").get(0);
        venue = venueData.getElementsByClass("field-item").get(0).text();

        return venue;
    }

    public GregorianCalendar getEventDateandTime(Element eventItem){
        GregorianCalendar date = null;

        final HashMap<String, Integer> monthTransform = new HashMap<>();
        {
            monthTransform.put("January", Calendar.JANUARY);
            monthTransform.put("February", Calendar.FEBRUARY);
            monthTransform.put("March", Calendar.MARCH);
            monthTransform.put("April", Calendar.APRIL);
            monthTransform.put("May", Calendar.MAY);
            monthTransform.put("June", Calendar.JUNE);
            monthTransform.put("July", Calendar.JULY);
            monthTransform.put("August", Calendar.AUGUST);
            monthTransform.put("September", Calendar.SEPTEMBER);
            monthTransform.put("October", Calendar.OCTOBER);
            monthTransform.put("November", Calendar.NOVEMBER);
            monthTransform.put("December", Calendar.DECEMBER);
        }

        String descriptionBlock = eventItem.getElementsByTag("description").get(0).text();
        Document descriptionData = Jsoup.parse(Parser.unescapeEntities(descriptionBlock, false));

        String eventBlock = descriptionData.getElementsByClass("date-display-single").text();

        Pattern datePattern = Pattern.compile("(\\w+)\\W+(\\d{1,2}),\\W+(\\d{4})");
        Pattern timePattern = Pattern.compile("\\d{1,2}:\\d{2}\\W*pm|am");

        Matcher eventDate = datePattern.matcher(eventBlock);
        Matcher eventTime = timePattern.matcher(eventBlock);

        if (eventDate.find()){
            String givenDate = eventBlock.substring(eventDate.start(), eventDate.end());
            date = new GregorianCalendar(Integer.valueOf(eventDate.group(2)),
                                         (int)monthTransform.get(eventDate.group(1)),
                                         Integer.valueOf(eventDate.group(0)));

            if (eventTime.find()){
                String givenTime = eventBlock.substring(eventDate.start(), eventDate.end());
                date.set(Calendar.HOUR_OF_DAY, Integer.valueOf(givenTime.split(":")[0]));
                date.set(Calendar.MINUTE, Integer.valueOf(givenTime.split(":")[1].substring(0, 2)));
            }
        }


        return date;
    }
}
