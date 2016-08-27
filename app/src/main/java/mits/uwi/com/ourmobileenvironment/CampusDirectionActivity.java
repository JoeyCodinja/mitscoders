package mits.uwi.com.ourmobileenvironment;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.Attributes;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Directions");

        ListView locationsListView = (ListView) findViewById(R.id.directionLocationsList);
        adapter = new DirectionAdapter(buildLocations());
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

    }

    private ArrayList<ArrayList<String>> buildLocations(){
        locations = new ArrayList<>();
        ArrayList<String> locationNames = new ArrayList<String>();
        ArrayList<String> locationCoords = new ArrayList<String>();


        locationNames.add("Assembly Hall");
        locationNames.add("Students Administrative Services Building");
        locationNames.add("Admissions Building");

        locationCoords.add("18.005613,-76.747324");
        locationCoords.add("18.005822,-76.747504");
        locationCoords.add("18.006057,- 76.747168");


        for(String location: locationNames){
            int index = locationNames.indexOf(location);

            ArrayList<String> locationInfo =
                    new ArrayList<>(Arrays.asList(location, locationCoords.get(index)));
            locations.add(locationInfo);
        }

        return locations;
    }



}
