package mits.uwi.com.ourmobileenvironment;

import android.content.Context;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

/**
 * Created by rox on 7/31/15.
 * repositions overflow for jellybean devices
 *
 */
public abstract class ToprightBar {
    // Call this method in the Actvity classes in order to reposition overflow
    public static void setTopOverflow(Context context){
        try {
            ViewConfiguration config = ViewConfiguration.get(context);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
