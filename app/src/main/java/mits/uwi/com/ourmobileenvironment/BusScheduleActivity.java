package mits.uwi.com.ourmobileenvironment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class BusScheduleActivity extends AppCompatActivity {

    private TextView tex;
    private ArrayList<BusRoute> broutelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ViewPager bPage;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        FragmentManager fm=getSupportFragmentManager();
        Fragment BusScheduleFragment=fm.findFragmentById(R.id.BusScheduleContainer);
        if (BusScheduleFragment==null){
            BusScheduleFragment=new BusSchedule();
            fm.beginTransaction().add(R.id.BusScheduleContainer,BusScheduleFragment).commit();
        }
        bPage=(ViewPager)findViewById(R.id.buspager);
        bPage.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return new BusSchedule();
            }

            @Override
            public int getCount() {
                return 0;
            }
        });


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
