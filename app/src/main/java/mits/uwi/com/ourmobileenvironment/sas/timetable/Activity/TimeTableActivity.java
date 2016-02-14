package mits.uwi.com.ourmobileenvironment.sas.timetable.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.ToprightBar;
import mits.uwi.com.ourmobileenvironment.sas.course.CourseInfoFragment;
import mits.uwi.com.ourmobileenvironment.sas.timetable.Fragments.TimeTableFragment;
import mits.uwi.com.ourmobileenvironment.sas.timetable.receiver.TimeTableReceiver;

/**
 * Created by User on 1/28/2016.
 */
public class TimeTableActivity extends AppCompatActivity {

private ListView mNavList;
private DrawerLayout mDrawerLayout;
private ArrayAdapter<String> mAdapter;
private ArrayAdapter<TextView> sAdapter;
private ActionBarDrawerToggle mDrawerToggle;
Toolbar toolbar;
private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.sas_fragmentContainer);
        if (fragment == null) {
            fragment = new TimeTableFragment();//CourseFragment();
            fm.beginTransaction()
                    .add(R.id.sas_fragmentContainer, fragment)
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .commit();
        }
        ToprightBar.setTopOverflow(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.abc_ic_commit_search_api_mtrl_alpha);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Will filter timetable search", Toast.LENGTH_SHORT).show();
            }
        });

        scheduleAlarm();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Setup a recurring alarm every half hour
    public void scheduleAlarm() {

        Intent alarm = new Intent(getApplicationContext(), TimeTableReceiver.class);
        boolean alarmRunning = (PendingIntent.getBroadcast(getApplicationContext(), 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
        if(alarmRunning == false) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarm, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 180000, pendingIntent);
        }/*
        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), TimeTableReceiver.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, TimeTableReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Setup periodic alarm every 5 seconds
        long firstMillis = System.currentTimeMillis(); // alarm is set right away
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, firstMillis,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, pIntent);*/
    }

    public void cancelAlarm() {
        Intent intent = new Intent(getApplicationContext(), TimeTableReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, TimeTableReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }
}
