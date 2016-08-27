package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.ToprightBar;
import mits.uwi.com.ourmobileenvironment.sas.MapItem;
import mits.uwi.com.ourmobileenvironment.sas.MapItemList;

/**
 * Created by peoplesoft on 2/12/2016.
 */
public class EateriesMapActivity  extends FragmentActivity implements OnMapReadyCallback {
    List<Restaurant> mEateries;
    private ArrayList<MarkerOptions> markers = new ArrayList<MarkerOptions>();
    MapFragment mMapFragment;
    Double mEateriesLat, mEateriesLong;
    String mName,mHours;
    ImageView mPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_eateriesmap);
        mEateriesLat = (double)getIntent().getSerializableExtra("LATITUDE");
        mEateriesLong = (double)getIntent().getSerializableExtra("LONGTITUDE");
        mName = (String)getIntent().getSerializableExtra("NAME");
        mHours = (String)getIntent().getSerializableExtra("HOURS");
        try {
            mMapFragment = (MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map);
            mMapFragment.getMapAsync(this);
            ToprightBar.setTopOverflow(this);

            mEateries =Restaurant.listAll(Restaurant.class);
            if(mEateries == null){
                Toast.makeText(getApplicationContext(),
                        "No Eateries", Toast.LENGTH_SHORT)
                        .show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Unfortunately the Map is down at this time :(", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        MarkerOptions marker = (new MarkerOptions()
                .position(new LatLng(mEateriesLat ,mEateriesLong))
                .title(mName)
                .snippet(mHours));
        marker.isVisible();
        map.addMarker(marker);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mEateriesLat, mEateriesLong), 17.0f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}