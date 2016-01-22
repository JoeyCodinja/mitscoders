
package mits.uwi.com.ourmobileenvironment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.EateriesFragment;
import java.util.ArrayList;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.CampusInformationFragment;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.CampusListingsFragment;

public class CampusInformationActivity extends AppCompatActivity {

    ViewPager ePage;
    private SlidingTabLayout mSlidingTabLayout;
    private ArrayList<Fragment> mFragList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buspager);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment1 = new EateriesFragment();
        Fragment fragment2 = new CampusListingsFragment();
        Fragment fragment3 = new CampusInformationFragment();
        mFragList.add(0, fragment1);
        mFragList.add(1, fragment2);
        mFragList.add(2, fragment3);
        ePage=(ViewPager)findViewById(R.id.buspager);
        ePage.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {

                return mFragList.get(position);
            }

            @Override
            public int getCount() { return 3; }

            @Override
            public CharSequence getPageTitle(int position) {
                ArrayList<String> title=new ArrayList<String>();
                title.add("Eateries");
                title.add("Listings");
                title.add("Info");
                return title.get(position);

            }

        });

        mSlidingTabLayout=(SlidingTabLayout) findViewById(R.id.sltab);
        mSlidingTabLayout.setCustomTabView(R.layout.tabview, R.id.tabtitle);
        mSlidingTabLayout.setViewPager(ePage);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        mSlidingTabLayout.setDividerColors(getResources().getColor(R.color.actionbar_background_eateries));
        mSlidingTabLayout.setTabsBackgroundColor(getResources().getColor(R.color.actionbar_background_eateries));

        //Calls to this function reposition the overflow
        ToprightBar.setTopOverflow(this);
        }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mSlidingTabLayout.populateTabStrip();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_eateries, menu);
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

