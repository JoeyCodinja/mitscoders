package mits.uwi.com.ourmobileenvironment.faculty;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.test.suitebuilder.annotation.Suppress;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.XMLFormatter;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 14/10/2016.
 */
public class FacultyInfo {

    public final static String SOCIAL_TWITTER = "Twitter";
    public final static String SOCIAL_INSTAGRAM = "Instagram";
    public final static String SOCIAL_FACEBOOK = "Facebook";
    public final static String SOCIAL_SNAPCHAT = "Snapchat";

    private Context ctx;
    public Map<String,Object> facultyValues;
    public  String facultyId;
    public FacultyInfo(Context ctx, Map<String, Object> values, String facultyId){
        this.ctx = ctx;
        this.facultyValues = values;
        this.facultyId = facultyId;
    }

    @SuppressWarnings("unchecked")
    public static FacultyInfo[] fromXML(Context ctx) throws XmlPullParserException, IOException {
        XmlResourceParser faculty_info = ctx.getResources().getXml(R.xml.faculty_info);
        Map<String, Object> values = new HashMap<>();
        FacultyInfo[] faculty = new FacultyInfo[5];
        int cnt, documentCnt, imageCnt;
        String currentFaculty, imageIdentifier,
                documentIdentifier, tagName;

        cnt = documentCnt = imageCnt =  0;
        currentFaculty = imageIdentifier =
                documentIdentifier = tagName = null;

        while (faculty_info.getEventType() != XmlResourceParser.END_DOCUMENT){
            if (faculty_info.getName() != null)
                tagName = faculty_info.getName();
            if (faculty_info.getEventType() == XmlResourceParser.START_TAG){
                switch(tagName){
                    case "Faculty":
                        currentFaculty = faculty_info.getAttributeValue(0);
                        Map<String, Object> faculty_name = new HashMap<>();
                        faculty_name.put("Name", faculty_info.getAttributeValue(1));

                        values.put(currentFaculty, new HashMap<String, Object>());
                        ((Map<String, Object>)values.get(currentFaculty))
                                .putAll(faculty_name);
                        break;
                    case "About":
                        Map<String, Object> faculty_about = new HashMap<>();
                        faculty_about.put(tagName,
                                faculty_info.getText());
                        ((Map<String,Object>)values.get(currentFaculty))
                                .putAll(faculty_about);
                        break;
                    case "SocialMedia":
                    case "Resources":
                    case "Departments":
                        Map<String, Object> faculty_resources = new HashMap<>();
                        faculty_resources.put(tagName,
                                new HashMap<String, Object>());
                        ((Map<String, Object>)values.get(currentFaculty))
                                 .putAll(faculty_resources);
                        break;
                    case "Facebook":
                    case "Twitter":
                    case "Instagram":
                    case "Snapchat":
                        Map<String, Object> socialMediaEntry = new HashMap<>();
                        socialMediaEntry.put(tagName,
                                faculty_info.getAttributeValue(0));

                        ((Map<String,Object>)
                                ((Map<String, Object>)values.get(currentFaculty))
                                        .get("SocialMedia"))
                                .putAll(socialMediaEntry);
                        break;
                    case "Document":
                        documentIdentifier = "doc" + String.valueOf(documentCnt);

                        Map<String, Object> resourceEntry = new HashMap<>();
                        Map<String, Object> documentEntry = new HashMap<>();
                        //Put the URL and the name of the document within the resource entry
                        documentEntry.put(faculty_info.getAttributeName(0),
                                faculty_info.getAttributeValue(0));
                        resourceEntry.put(documentIdentifier,
                                documentEntry);

                        ((Map<String,Object>)
                                ((Map<String, Object>)values.get(currentFaculty))
                                        .get("Resources"))
                                .putAll(resourceEntry);
                        documentCnt++;
                        break;
                    case "Image":
                        imageIdentifier = "img" + String.valueOf(imageCnt);

                        resourceEntry = new HashMap<>();
                        Map<String, Object> imageEntry = new HashMap<>();
                        imageEntry.put(faculty_info.getAttributeName(0),
                                faculty_info.getAttributeValue(0));
                        imageEntry.put(faculty_info.getAttributeName(1),
                                faculty_info.getAttributeValue(1));

                        resourceEntry.put(imageIdentifier,
                                imageEntry);

                        ((Map<String, Object>)
                                ((Map<String, Object>)values.get(currentFaculty))
                                        .get("Resources"))
                                .putAll(resourceEntry);
                        imageCnt++;
                        break;
                    default:
                        break;
                }
            }
            if (faculty_info.getEventType() == XmlResourceParser.TEXT){
                Map<String, Object> faculty_pointer =
                        (Map<String,Object>)values.get(currentFaculty);
                switch(tagName){
                    case "About":
                        faculty_pointer.put("About", faculty_info.getText());
                        break;
                    case "Document":
                        ((Map<String, Object>)
                                ((Map<String,Object>)faculty_pointer.get("Resources"))
                                        .get(documentIdentifier))
                                .put("name", faculty_info.getText());
                        break;
                    case "Image":
                        ((Map<String, Object>)
                                ((Map<String,Object>)faculty_pointer.get("Resources"))
                                        .get(imageIdentifier))
                                .put("name", faculty_info.getText());
                        break;
                    default:
                        break;
                }
            }
            if (faculty_info.getEventType() == XmlResourceParser.END_TAG &&
                    faculty_info.getName().equals("Faculty")){
                // Start with a fresh dictionary for the new Faculty
                faculty[cnt] = new FacultyInfo(ctx, values, currentFaculty);
                values = new HashMap<>();
                cnt++;
                documentCnt = 0;
                imageCnt = 0;
            }
            faculty_info.next();
        }

        return faculty;
    }

    private Drawable getDrawbablefromStringIdentifier(Context ctx, String identifier){
        //TODO: check exception for when resource not found
        int resourceID = ctx.getResources().getIdentifier(identifier, "drawable", ctx.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return ctx.getDrawable(resourceID);
        } else {
            return ctx.getResources().getDrawable(resourceID);
        }
    }

    @SuppressWarnings("unchecked")
    public String getAboutSnippet(){
        return (String)((Map<String,Object>)this.facultyValues.get(facultyId)).get("About");
    }

    @SuppressWarnings("unchecked")
    public Uri getSocialMediaURI(String outlet){
        Map<String, Object> socialMediaValues = (Map<String, Object>)facultyValues.get("SocialMedia");
        return Uri.parse((String)socialMediaValues.get(outlet));
    }

    @SuppressWarnings("unchecked")
    public Uri getDocumentDownloadURI(String description, String docIdentifier){
        Map<String, Object> resourceValues = ((Map<String, Object>)facultyValues.get("Resources"));
        for (String key: resourceValues.keySet()){
            if (key.contains(description)){
                return Uri.parse((String)(resourceValues.get(key)));
            }
        }
        return new Uri.Builder().build();
    }

}
