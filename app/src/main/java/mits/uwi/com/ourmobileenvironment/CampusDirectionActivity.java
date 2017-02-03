package mits.uwi.com.ourmobileenvironment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.adapters.DirectionAdapter;

public class CampusDirectionActivity extends AppCompatActivity {

    ArrayList<ArrayList<String>> locations;
    String locationChosen;
    DirectionAdapter adapter;
    String LOCATION_CHOSEN = "locationChosen";



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UWIMonaApplication application = (UWIMonaApplication) getApplication();
        application.screenViewHitAnalytics("Activity~CampusDirection");

        try{
            locationChosen = savedInstanceState.getString(LOCATION_CHOSEN);
            if (locationChosen == null){
                throw new NullPointerException();
            }
            resumeInterruptedAction(locationChosen, getApplicationContext());
        } catch(NullPointerException e){
            // Not resuming previous action; continue as normal
        }


        setContentView(R.layout.activity_campus_direction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Take me to");

        ListView locationsListView = (ListView) findViewById(R.id.directionLocationsList);
        try {
            adapter = new DirectionAdapter(this);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        locationsListView.setAdapter(adapter);
        locationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                locationChosen = adapter.getItemLocationName(position);
                String locationCoords = adapter.getItemLocationCoords(position);

                // Check to see if package exsists
                if (!isGoogleMapsInstalled()){
                    // Prompt user to go download the application
                    // Save the state of the user's action before we head off
                    Bundle saveState = new Bundle();
                    onSaveInstanceState(saveState);
                    installGoogleMaps();

                } else{
                    navigateTo(locationCoords, view.getContext());
                }

            }
        });
        ProgressBar pbView = (ProgressBar)findViewById(R.id.pb_campus_direction);
        ViewGroup pbParent = (ViewGroup)pbView.getParent();
        pbParent.removeView(pbView);
        locationsListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        try{
            outState.putString(LOCATION_CHOSEN, locationChosen);
        }
        catch (Exception e){
            // TODO: find more accurate Exception to use other than this broad one
            // Save nothing if the locationChoden variable was not set
        }
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void resumeInterruptedAction(String locationChosen, Context context){
        if (!isGoogleMapsInstalled()){
            installGoogleMaps();
        }
        int locationIndex = adapter.getItemIndexFromLocationName(locationChosen);
        if (locationIndex != -1)
            navigateTo(adapter.getItemLocationCoords(locationIndex), context);
        else{
            Toast.makeText(this,
                    "We could not find the location you previously requested",
                    Toast.LENGTH_LONG).show();
        }
    }

    public static void navigateTo(String location_coords, Context context){
        Uri navigationIntentURI =
                Uri.parse("google.navigation:q=" +
                        location_coords +
                        "&mode=w");
        Intent i = new Intent(Intent.ACTION_VIEW,
                navigationIntentURI);
        i.setPackage("com.google.android.apps.maps");
        context.startActivity(i);
    }

    private boolean isGoogleMapsInstalled(){
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);

        for (ApplicationInfo packageInfo: packages){
            if (packageInfo.packageName.equals("com.google.android.apps.maps")){
                return true;
            }
        }
        return false;
    }

    private boolean isGooglePlayStoreInstalled(){
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);

        for (ApplicationInfo packageInfo: packages){
            if (packageInfo.packageName.equals("com.android.vending")){
                return true;
            }
        }
        return false;
    }

    private void installGoogleMaps(){
        // TODO: Receieve communication from App Store that the application was installed
        String googleMapsMarketURI = "market://details?id=com.google.android.apps.maps";
        if (isGooglePlayStoreInstalled()) {
            Intent downloadGMaps = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(googleMapsMarketURI));
            startActivity(downloadGMaps);
        } else{
            Toast.makeText(this, "Unable to find Google Play Store", Toast.LENGTH_LONG);
        }
    }
}
