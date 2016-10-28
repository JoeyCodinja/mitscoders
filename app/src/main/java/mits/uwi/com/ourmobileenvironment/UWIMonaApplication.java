package mits.uwi.com.ourmobileenvironment;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.orm.SugarApp;

/**
 * Created by Danuel on 12/8/2016.
 */
public class UWIMonaApplication extends SugarApp{
    private Tracker mTracker;

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    synchronized public Tracker getDefaultTracker(){
        if (mTracker == null){
            GoogleAnalytics analytics =  GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.analytics);
        }
        return mTracker;
    }

    synchronized public void screenViewHitAnalytics(String screenName){
        Tracker mTracker = this.getDefaultTracker();
        mTracker.setScreenName(screenName);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    synchronized public void timingAnalytics(String category,
                                                 long timeElapsed,
                                                 String name,
                                                 String label){
        Tracker mTracker = this.getDefaultTracker();
        HitBuilders.TimingBuilder userTiming = new HitBuilders.TimingBuilder()
                .setCategory(category)
                .setLabel(label)
                .setVariable(name)
                .setValue(timeElapsed);
        mTracker.send(userTiming.build());

    }
}



