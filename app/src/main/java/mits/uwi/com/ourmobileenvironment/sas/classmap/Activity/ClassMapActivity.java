package mits.uwi.com.ourmobileenvironment.sas.classmap.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.ToprightBar;
import mits.uwi.com.ourmobileenvironment.sas.MapItem;
import mits.uwi.com.ourmobileenvironment.sas.MapItemList;


/**
 * Created by User on 12/8/2015.
 *
 *
 * Classes are Red
 * Tutorials are Green
 * Labs are yellow
 * Exams Blue
 */
public class ClassMapActivity extends FragmentActivity implements OnMapReadyCallback  {
    List <MapItem> mMaps ;
    private ArrayList<Marker> markers = new ArrayList<Marker>();
    MapFragment mMapFragment;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_classmap);
        try{
        mMapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
            ToprightBar.setTopOverflow(this);

            mMaps = MapItemList.get(getApplicationContext()).getmMapItems();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),
                    "Unfortunately the Map is down at this time :(", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        MarkerOptions marker = (new MarkerOptions()
                .position(new LatLng(18.005941, -76.746896))
                .title("Welcome")
                .snippet("The University of the West Indies"));
        marker.isVisible();

        map.addMarker(marker);
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        for (MapItem items: mMaps){
            /*Marker mark = map.addMarker(new MarkerOptions()
                    .position(new LatLng(items.getLatitude(), items.getLongtitude()))
                    .title(items.getTitle())
                    .snippet(items.getDescription()));*/
            //mark.isVisible();
            //marker.showInfoWindow();
            if (items.getType()=='C') {
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(items.getLatitude(), items.getLongtitude()))
                        .title(items.getTitle())
                        .snippet(items.getDescription())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            } else if (items.getType()=='A') {
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(items.getLatitude(), items.getLongtitude()))
                        .title(items.getTitle())
                        .snippet(items.getDescription())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }else{
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(items.getLatitude(), items.getLongtitude()))
                        .title(items.getTitle())
                        .snippet(items.getDescription())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
            }
        }
        //map.addMarker(marker);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(18.005941, -76.746896), 16.0f));
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

