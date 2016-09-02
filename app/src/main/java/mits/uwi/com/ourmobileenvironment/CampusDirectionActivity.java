package mits.uwi.com.ourmobileenvironment;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import mits.uwi.com.ourmobileenvironment.adapters.DirectionAdapter;

public class CampusDirectionActivity extends AppCompatActivity {

    ArrayList<ArrayList<String>> locations;
    DirectionAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_direction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Take me to");

        ListView locationsListView = (ListView) findViewById(R.id.directionLocationsList);
        try {
            adapter = new DirectionAdapter(buildLocations(this));
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        locationsListView.setAdapter(adapter);
        locationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri navigationIntentURI =
                        Uri.parse("google.navigation:q=" +
                                  adapter.getItemLocationCoords(position));
                Intent i = new Intent(Intent.ACTION_VIEW,
                                      navigationIntentURI);
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });
        ProgressBar pbView = (ProgressBar)findViewById(R.id.pb_campus_direction);
        ViewGroup pbParent = (ViewGroup)pbView.getParent();
        pbParent.removeView(pbView);
        locationsListView.setVisibility(View.VISIBLE);
    }

    private ArrayList<ArrayList<String>> buildLocations(Context context)
            throws XmlPullParserException, IOException{
            XmlResourceParser tbt_locations =
                    getResources().getXml(R.xml.tbt_locations);
            locations = new ArrayList<>();
            ArrayList<String> locationNames = new ArrayList<>();
            ArrayList<String> locationCoords = new ArrayList<>();
            ArrayList<String> locationCategory = new ArrayList<>();

            while (tbt_locations.getEventType() != XmlResourceParser.END_DOCUMENT){
                String tag_name = tbt_locations.getName();
                if (tbt_locations.getEventType() == XmlResourceParser.START_TAG){
                    switch (tag_name) {
                        case "Name":
                            locationNames.add(tbt_locations.getAttributeValue(0));
                            break;
                        case "Category":
                            locationCategory.add(tbt_locations.getAttributeValue(0));
                            break;
                        case "Coords":
                            String coordinates;
                            coordinates = tbt_locations.getAttributeValue(0) +
                                    ',' +
                                    tbt_locations.getAttributeValue(1);
                            locationCoords.add(coordinates);
                            break;
                    }
                }
                tbt_locations.next();
            }
            tbt_locations.close();


            for(String location: locationNames){
                int index = locationNames.indexOf(location);

                ArrayList<String> locationInfo =
                        new ArrayList<>(
                                Arrays.asList(location,
                                              locationCoords.get(index),
                                              locationCategory.get(index)));
                locations.add(locationInfo);
            }

            return locations;
    }



}
