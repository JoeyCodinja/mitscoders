package mits.uwi.com.ourmobileenvironment.sas.classmap.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
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
 * Created by User on 12/8/2015.
 *
 * Classes are Red
 * Tutorials are Green
 * Labs are yellow
 * Exams Blue
 */
public class ClassMapActivity extends FragmentActivity implements OnMapReadyCallback {
    List<MapItem> mMaps;
    private ArrayList<MarkerOptions> markers = new ArrayList<MarkerOptions>();
    MapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_classmap);
        try {
            mMapFragment = (MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map);
            mMapFragment.getMapAsync(this);
            ToprightBar.setTopOverflow(this);

            mMaps = MapItemList.get(getApplicationContext()).getmMapItems();
        } catch (Exception e) {
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
        //map.addMarker(marker);
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        markers.add(marker);
        for (MapItem items : mMaps) {
            MarkerOptions mark = (new MarkerOptions()
                    .position(new LatLng(items.getLatitude(), items.getLongtitude()))
                    .title(items.getTitle())
                    .snippet(items.getDescription()));
            if (items.getType() == 'C') {
                mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            } else if (items.getType() == 'A') {
                mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            } else if (items.getType() == 'E') {
                mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            } else
                mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
            //Toast.makeText(this,items.toString(),Toast.LENGTH_SHORT).show();
            markers.add(mark);
        }
        for (MarkerOptions op : markers) {
            map.addMarker(op);
        }

        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(18.005941, -76.746896), 15.5f));
        map.getUiSettings().setScrollGesturesEnabled(false);
        //map.
        //map.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(new LatLng(17.999116, -76.743155),new LatLng(18.010231, -76.748827)),0));
        //map.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(new LatLng(18.0033896,-76.7458279),new LatLng(18.0062918,-76.7469994)),0));

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

