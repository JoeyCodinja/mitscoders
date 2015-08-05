package mits.uwi.com.ourmobileenvironment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class BusScheduleActivity extends AppCompatActivity {

    private TextView tex;
    private ArrayList<BusRoute> broutelist;
    ViewPager bPage;
    private SlidingTabLayout mSlidingTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buspager);
        FragmentManager fm=getSupportFragmentManager();
        Busfactory.populate();
        broutelist=Busfactory.getBusList();
        bPage=(ViewPager)findViewById(R.id.buspager);
        bPage.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return new BusScheduleFragment();
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                ArrayList<String> title=new ArrayList<String>();
                title.add("Shuttle");
                title.add("JUTC");
                title.add("Taxi");
                return title.get(position);

            }


        });
        mSlidingTabLayout=(SlidingTabLayout) findViewById(R.id.sltab);
        mSlidingTabLayout.setCustomTabView(R.layout.tabview, R.id.tabtitle);
        mSlidingTabLayout.setViewPager(bPage);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        mSlidingTabLayout.setDividerColors(getResources().getColor(R.color.actionbar_background));
        mSlidingTabLayout.setTabsBackgroundColor(getResources().getColor(R.color.actionbar_background));



    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
            mSlidingTabLayout.populateTabStrip();
        Log.d("test","it worked");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bus_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}