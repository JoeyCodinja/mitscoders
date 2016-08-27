package mits.uwi.com.ourmobileenvironment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import mits.uwi.com.ourmobileenvironment.Directory.DirectoryActivity;
import mits.uwi.com.ourmobileenvironment.Transport.TransportActivity;
import mits.uwi.com.ourmobileenvironment.adapters.HomePageArrayAdapter;
import mits.uwi.com.ourmobileenvironment.additional_systems.Evaluations.activities.TeachingEvalsActivity;
import mits.uwi.com.ourmobileenvironment.homepagefragments.HomeActivityFragment;
import mits.uwi.com.ourmobileenvironment.ourvle.activities.OurVLELoginActivity;
import mits.uwi.com.ourmobileenvironment.sas.SAS_Splash;


public class HomeActivity extends AppCompatActivity {

    private String[] mNavigationMenuTitles;
    private DrawerLayout mNavigationDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mNavigationDrawerToggle;
    private HomePageArrayAdapter mAdapter;
    private FragmentManager fm ;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNavigationMenuTitles = getResources().getStringArray(R.array.navigation_menu_titles);
        mNavigationDrawerLayout = (DrawerLayout)findViewById(R.id.home_drawer);
        mNavigationDrawerToggle = new ActionBarDrawerToggle(this, mNavigationDrawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close){
            /** Called when a drawer is closed */
            @Override
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            /** Called when a drawer is opened */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mNavigationDrawerLayout.setDrawerListener(mNavigationDrawerToggle);

        //Sets the UWI Coat of Arms as the Home Icon in the Action Bar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu_light);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.title_activity_home);


        mDrawerList = (ListView) findViewById(R.id.home_drawer_menu);

        mAdapter = new HomePageArrayAdapter(this, mNavigationMenuTitles);
        mDrawerList.setAdapter(mAdapter);

//        mDrawerList.addFooterView(getLayoutInflater().inflate(R.layout.social, mDrawerList));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //Fragment hosting Content

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.home_content);

        if (fragment == null){
            fragment = new HomeActivityFragment();
            fm.beginTransaction()
                    .add(R.id.home_content, fragment)
                    .commit();
        }

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        String itemName = mAdapter.getItem(position);
        Intent i = null;
        switch(itemName){
            case "E-mail":
            case "Notifications":
            case "Wi-Fi Finder":
            case "Emergency Services":
                break;
            case "Teaching Evaluations":
                i = new Intent(this, TeachingEvalsActivity.class);
                break;
            case "Directory":
                i = new Intent(this, DirectoryActivity.class);
                break;
            case "Student Administrative Services":
                i = new Intent(this, SAS_Splash.class);
                break;
            case "Our Virtual Learning Environment (OurVLE)":
                i = new Intent(this, OurVLELoginActivity.class);
                break;
            case "Transnportation":
                i = new Intent(this, TransportActivity.class);
                break;
            case "Campus Information":
                i = new Intent(this, CampusInformationActivity.class);
                break;
            case "Eateries":
                i = new Intent(this, EateriesActivity.class);
                break;
            default:
                i = null;
                break;
        }

        if (i != null){
            startActivity(i);
            mDrawerList.setItemChecked(position, true);
            mNavigationDrawerLayout.closeDrawer(mDrawerList);
        }

        return;


    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        // Sync the toggle state after onRestoreInstanceState has occurred
        mNavigationDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mNavigationDrawerToggle.onConfigurationChanged(newConfig);
    }

    //For use with the CampusInformation Fragment
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FrameLayout parentView = (FrameLayout)findViewById(R.id.shade).getParent();
        FrameLayout shade = (FrameLayout) parentView.getChildAt(1);
        parentView.removeView(shade);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (mNavigationDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
