package mits.uwi.com.ourmobileenvironment.sas.timetable.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sas.sas_database.TimeTable;
import mits.uwi.com.ourmobileenvironment.sas.settings.SasSettingsActivity;
import mits.uwi.com.ourmobileenvironment.sas.timetable.Fragments.StudentTimeTableWeekFragment;

/**
 * Created by User on 1/31/2016.
 */
public class TimeTableService extends IntentService {
    private static final String TAG = "EventService";
    private static final int NOTIFICATION = R.string.cast_notification_connected_message;
    public static final int CLASS_INTERVAL = 1000 * 60 * 30;
    List<TimeTable> mtimetable;
    Calendar calendar,calendar2;
    Long mTime;
    Date mDt1,mDt2;
    Boolean malarm, mvibrate;

    SharedPreferences prefs;
    public TimeTableService (){
        super(TAG);
    }

    public TimeTableService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Received intent:"+intent);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        malarm = prefs.getBoolean("timetable_notification",true);
        mvibrate = prefs.getBoolean("vibrate_notification",true);
        PendingIntent pi = PendingIntent
                .getActivity(this, 0, new Intent(this, StudentTimeTableWeekFragment.class), 0);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
                .setTicker(getResources().getText(R.string.sas))
                .setSmallIcon(R.drawable.ic_event_white_24dp)
                .setContentTitle("Upcoming Class")
                .setContentText("You have a class right about...now?")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        //Toast.makeText(this,"Alarm "+malarm+"\nVibrate"+mvibrate,Toast.LENGTH_SHORT).show();
       if (malarm == true) {
            notificationManager.notify(NOTIFICATION, notification);
           if (mvibrate==true) {
                Vibrator vibrator = (Vibrator) getApplicationContext()
                        .getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
            }
       }
        mtimetable = SugarRecord.listAll(TimeTable.class);
        calendar.getInstance();
        if (mtimetable.isEmpty()){
            Toast.makeText(getApplicationContext(),"No Events to Report", Toast.LENGTH_SHORT).show();
        }
        /* mDt1 = calendar.getTime();
        for(TimeTable event: mtimetable){
        mDt2 =event.getStartTime();
            mTime =  mDt1.getTime() - mDt2.getTime();
            if (mTime == CLASS_INTERVAL){
                notificationManager.notify(NOTIFICATION, notification);
            }
        }
        String resultId;
        */
    }

}
