package mits.uwi.com.ourmobileenvironment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import mits.uwi.com.ourmobileenvironment.sasfragments.SAS_Splash;


public class LandingActivity extends AppCompatActivity {

    private Button mToSASActivity, mToBOSSActivity, mToOURLVEActivity, mToCampusInformationActivity,mToBusScheduleActivity,mtoDirectoryActivity;
    private ImageView mFloatingActionButton, mFloatingActionButton2, mFloatingActionButton3, mFloatingActionButton4;


    private static final String TAG = "LandingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mFloatingActionButton = (ImageView)findViewById(R.id.landing_pageFAB);
        mFloatingActionButton2 = (ImageView)findViewById(R.id.landing_pageFAB1);
        mFloatingActionButton2.setVisibility(View.INVISIBLE);
        mFloatingActionButton3 = (ImageView)findViewById(R.id.landing_pageFAB2);
        mFloatingActionButton3.setVisibility(View.INVISIBLE);
        mFloatingActionButton4 = (ImageView)findViewById(R.id.landing_pageFAB3);
        mFloatingActionButton4.setVisibility(View.INVISIBLE);


        mFloatingActionButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d(TAG, event.toString());
                return false;
            }
        });

        mToSASActivity = (Button)findViewById(R.id.to_sas_activity_button);
        mToSASActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LandingActivity.this, SAS_Splash.class);
                Log.d(TAG, "Starting SASActivity");
                startActivity(i);
            }
        });

        mToBOSSActivity = (Button)findViewById(R.id.to_boss_activity_button);
        mToBOSSActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LandingActivity.this, BOSSActivity.class);
                Log.d(TAG, "Starting BOSSActivity");
                startActivity(i);
            }
        });

        mToOURLVEActivity = (Button)findViewById(R.id.to_ourvle_activity_button);
        mToOURLVEActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LandingActivity.this, OurVLEActivity.class);
                Log.d(TAG, "StartingOurVLEActivity");
                startActivity(i);
            }
        });
        mToBusScheduleActivity=(Button)findViewById((R.id.to_bus_schedule_activity));
        mToBusScheduleActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LandingActivity.this, BusScheduleActivity.class);
                startActivity(i);
            }
        });


        mToCampusInformationActivity = (Button)findViewById(R.id.to_campus_information_activity_button);
        mToCampusInformationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(LandingActivity.this, CampusInformationActivity.class);
                Log.d(TAG, "Starting CampusInfo Activity");

                startActivity(i);
            }
        });

        mtoDirectoryActivity=(Button)findViewById(R.id.to_directory_activity_button);
        mtoDirectoryActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LandingActivity.this,DirectoryActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
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
