package mits.uwi.com.ourmobileenvironment.sas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.SASActivity;

/**
 * Created by User on 10/4/2015.
 */
public class SAS_Splash extends Activity{

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
