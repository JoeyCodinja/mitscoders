package mits.uwi.com.ourmobileenvironment.campusinformationfragments.UWIInformationFragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils;
import com.google.api.client.util.Strings;
import com.orm.StringUtil;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mits.uwi.com.ourmobileenvironment.CampusInformationSubActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.adapters.FacultyExpandableListAdapter;
import mits.uwi.com.ourmobileenvironment.faculty.FacultyInfo;

public class Faculties extends Fragment {
    private DownloadCoordinator facultyHandbooks;
    private String facultyChosen;
    private Drawable dPicture, deanDrawable;
    private ImageView dean;
    private TextView deanTitleAndName;
    private ActionBar toolbar;
    private DocumentView facultySnippet;
    private Resources drawableResources;
    private FacultyInfo[] faculties;
    private ExpandableListAdapter facultyInformation;

    public static Faculties newInstance(String faculty) {
        Faculties fragment = new Faculties();

        Bundle args = new Bundle();
        args.putString("facultyChosen", faculty);
        fragment.setArguments(args);

        return fragment;
    }

    public Faculties() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facultyChosen = getArguments().getString("facultyChosen");
        drawableResources = getResources();

        try {
            faculties = FacultyInfo.fromXML(this.getActivity().getApplicationContext());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_faculties_campusinfosub,
                                  container,
                                  false);



        ExpandableListView facultyDetails =
                (ExpandableListView)v.findViewById(R.id.faculty_expandable);
        Animation easeIn = AnimationUtils.loadAnimation(this.getActivity(),
                R.anim.ease_fade_in_right);
        LayoutAnimationController layoutAnimationController= new LayoutAnimationController(easeIn);
        facultyDetails.setLayoutAnimation(layoutAnimationController);

        facultySnippet = (DocumentView)v.findViewById(R.id.faculty_snippet);

        dean = (ImageView) v.findViewById(R.id.dean_picture);
        deanTitleAndName = (TextView) v.findViewById(R.id.dean_title);
        toolbar = ((CampusInformationSubActivity)getActivity()).getToolbar();

        for (FacultyInfo faculty: faculties){
            if (faculty.facultyId.equals(facultyChosen)){
                facultyInformation = new FacultyExpandableListAdapter(
                        this.getActivity().getApplicationContext(),
                        faculty,
                        facultyChosen);
                Map<String,Object> facultyPointer = (Map<String,Object>)faculty.facultyValues.get(faculty.facultyId);
                facultyDetails.setAdapter(facultyInformation);
                facultyDetails
                        .setOnChildClickListener(
                                (ExpandableListView.OnChildClickListener)facultyInformation);
                facultySnippet.setText(facultyPointer.get("About").toString());
                String facultyName = (String)facultyPointer.get("Name");
                toolbar.setTitle(facultyName);
                Map<String, Object> facultyResources =
                        (Map<String, Object>)facultyPointer.get("Resources");
                Set<String> resourceIds = facultyResources.keySet();
                for (String id: resourceIds){
                    String resourceName = (String)
                            ((Map<String,Object>)facultyResources.get(id)).get("name");
                    if (isImageIdentifier(id) && containsString("dean", resourceName)){
                        String drawableId = (String)
                                ((Map<String,Object>)facultyResources.get(id)).get("identifier");
                        deanDrawable = fromStringIdentifierToDrawable(drawableId);
                        dean.setImageDrawable(deanDrawable);
                        deanTitleAndName.setText(resourceName);
                    }

                }
                break;
            }
        }
        return v;
    }

    public Drawable fromStringIdentifierToDrawable(String identifier){
        // TODO: Filler image needed
        Drawable drawableRequested = null;
        int resourceId = getResources().getIdentifier(identifier,
                "drawable",
                "mits.uwi.com.ourmobileenvironment");

        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21){
            drawableRequested = getResources().getDrawable(resourceId);
            if (drawableRequested == null)
                drawableRequested = getResources().getDrawable(R.drawable.pelican_white);
        }
        else if (Build.VERSION.SDK_INT >= 21) {
            drawableRequested = getResources().getDrawable(resourceId, null);
            if (drawableRequested == null)
                drawableRequested = getResources().getDrawable(R.drawable.pelican_white, null);
        }

        return drawableRequested;


    }

    public interface DownloadCoordinator {
        public boolean downloadResource(String uri, String resourceId);

        public boolean fileAlreadyExists(String filename);
    }

    private boolean containsString(String searchString, String stringSearched){
        Pattern matchQuery = Pattern.compile(searchString, Pattern.CASE_INSENSITIVE);
        return matchQuery.matcher(stringSearched).find();
    }

    private boolean isImageIdentifier(String query){
        Pattern matchQuery = Pattern.compile("img");
        return matchQuery.matcher(query).find();
    }

    private boolean isDocumentIdentifier(String query){
        Pattern matchQuery = Pattern.compile("doc");
        return matchQuery.matcher(query).find();
    }

    private List<String> getInformationHeadings(String[] headingsKeys){
        ArrayList<String> returnArray = new ArrayList<>();
        Pattern camelCase = Pattern.compile("([A-Z][a-z]*)", 0 );
        for (String heading: headingsKeys){
            if (!(heading.equals("About") || heading.equals("Name"))){
                String newHeading = "";
                Matcher isCamelCase = camelCase.matcher(heading);
                while (isCamelCase.find()){
                    if (newHeading.length() == 0)
                        newHeading = isCamelCase.group();
                    else
                        newHeading += " " + isCamelCase.group();
                }
                heading = newHeading;

                returnArray.add(heading);
            }
        }
        return returnArray;
    }
}

