package mits.uwi.com.ourmobileenvironment.Transport;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.SlidingTabLayout;
import mits.uwi.com.ourmobileenvironment.ToprightBar;

public class TransportActivity extends AppCompatActivity  {

    ViewPager bPage;
    private SlidingTabLayout mSlidingTabLayout;
    private ArrayList<TransportFragment> transportFragments =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buspager);
        FragmentManager fm = getSupportFragmentManager();
        bPage=(ViewPager)findViewById(R.id.buspager);
        bPage.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                transportFragments.add(new BusScheduleFragment());
                transportFragments.add(new JUTCRouteFragment());
                transportFragments.add(new TaxiServiceFragment());
                transportFragments.add(new GuildBusFragment());
                return transportFragments.get(position);
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                ArrayList<String> title = new ArrayList<String>();
                title.add("Shuttle");
                title.add("JUTC");
                title.add("Taxi");
                title.add("Guild");
                return title.get(position);

            }


        });
        bPage.setOffscreenPageLimit(3);
        mSlidingTabLayout=(SlidingTabLayout) findViewById(R.id.sltab);
        mSlidingTabLayout.setTabNum(4);
        mSlidingTabLayout.setCustomTabView(R.layout.tabview, R.id.tabtitle);
        mSlidingTabLayout.setViewPager(bPage);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        mSlidingTabLayout.setDividerColors(getResources().getColor(R.color.actionbar_background));
        mSlidingTabLayout.setTabsBackgroundColor(getResources().getColor(R.color.actionbar_background));
        ToprightBar.setTopOverflow(this);
    }



}