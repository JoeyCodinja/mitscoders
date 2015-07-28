package mits.uwi.com.ourmobileenvironment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class LandingActivity extends AppCompatActivity {

    private Button mToSASActivity, mToBOSSActivity, mToOURLVEActivity, mToCampusInformationActivity;
    private ImageView mFloatingActionButton, mFloatingActionButton2, mFloatingActionButton3,
            mFloatingActionButton4, mFloatingActionButton5, mFloatingActionButton6;
    private Boolean mMenuExpanded = false;

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
        mFloatingActionButton5 = (ImageView)findViewById(R.id.landing_pageFAB4);
        mFloatingActionButton5.setVisibility(View.INVISIBLE);
        mFloatingActionButton6 = (ImageView)findViewById(R.id.landing_pageFAB5);
        mFloatingActionButton6.setVisibility(View.INVISIBLE);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMenuExpanded == false) {
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_fab);
                    mFloatingActionButton2.setVisibility(View.VISIBLE);
                    mFloatingActionButton2.startAnimation(animation);

                    Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_fab2);
                    mFloatingActionButton3.setVisibility(View.VISIBLE);
                    mFloatingActionButton3.startAnimation(animation1);

                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_fab3);
                    mFloatingActionButton4.setVisibility(View.VISIBLE);
                    mFloatingActionButton4.startAnimation(animation2);

                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_fab4);
                    mFloatingActionButton5.setVisibility(View.VISIBLE);
                    mFloatingActionButton5.startAnimation(animation3);

                    Animation animation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_fab5);
                    mFloatingActionButton6.setVisibility(View.VISIBLE);
                    mFloatingActionButton6.startAnimation(animation4);

                    mMenuExpanded = true;
                }
                else
                {
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rvrs_translate_fab);
                    mFloatingActionButton2.startAnimation(animation);

                    Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rvrs_translate_fab2);
                    mFloatingActionButton3.startAnimation(animation1);

                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rvrs_translate_fab3);
                    mFloatingActionButton4.startAnimation(animation2);

                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rvrs_translate_fab4);
                    mFloatingActionButton5.startAnimation(animation3);

                    Animation animation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rvrs_translate_fab5);
                    mFloatingActionButton6.startAnimation(animation4);

                    mFloatingActionButton2.setVisibility(View.GONE);
                    mFloatingActionButton3.setVisibility(View.GONE);
                    mFloatingActionButton4.setVisibility(View.GONE);
                    mFloatingActionButton5.setVisibility(View.GONE);
                    mFloatingActionButton6.setVisibility(View.GONE);

                    mMenuExpanded = false;
                }
            }
        });

        mToSASActivity = (Button)findViewById(R.id.to_sas_activity_button);
        mToSASActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LandingActivity.this, SASActivity.class);
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

        mToCampusInformationActivity = (Button)findViewById(R.id.to_campus_information_activity_button);
        mToCampusInformationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(LandingActivity.this, CampusInformationActivity.class);
                Log.d(TAG, "Starting CampusInfo Activity");
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
