package mits.uwi.com.ourmobileenvironment.sasfragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import mits.uwi.com.ourmobileenvironment.HomeActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.SASActivity;

/**
 * Created by User on 10/4/2015.
 */
public class SAS_Splash extends Activity{

    /** Duration of wait **
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    /** Called when the activity is first created. *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sas_splash);

        /* New Handler to start the Menu‐Activity
        * and close this Splash‐Screen after some seconds.*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
/* Create an Intent that will start the Menu‐Activity. *
                Intent mainIntent = new Intent(SAS_Splash.this, SASActivity.class);
                SAS_Splash.this.startActivity(mainIntent);
                SAS_Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);


    }*/

    private Thread mSplashThread;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Splash screen view
        setContentView(R.layout.sas_splash);
        final SAS_Splash sPlashScreen = this;
// The thread to wait for splash screen events
        mSplashThread = new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
// Wait given period of time or exit on touch
                        wait(5000);
                    }
                }
                catch(InterruptedException ex){
                }
                finish();
// Run next activity
                Intent intent = new Intent(sPlashScreen, SASActivity.class);
                SAS_Splash.this.startActivity(intent);
                SAS_Splash.this.finish();
                /*intent.setClass(sPlashScreen, SASActivity.class);
                startActivity(intent);
                stop();*/
            }
        };
        mSplashThread.start();
    }
    /**
     * Processes splash screen touch events
     */
    @Override
    public boolean onTouchEvent(MotionEvent evt)
    {
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mSplashThread){
                mSplashThread.notifyAll();
            }
        }
        return true;
    }
}
