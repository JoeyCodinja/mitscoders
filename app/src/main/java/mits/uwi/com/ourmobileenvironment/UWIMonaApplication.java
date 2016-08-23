package mits.uwi.com.ourmobileenvironment;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.orm.SugarApp;

/**
 * Created by Danuel on 12/8/2016.
 */
public class UWIMonaApplication extends SugarApp{

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

