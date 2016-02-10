package mits.uwi.com.ourmobileenvironment.sas.timetable.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sas.sas_database.TimeTable;
import mits.uwi.com.ourmobileenvironment.sas.timetable.Fragments.StudentTimeTableWeekFragment;

/**
 * Created by User on 1/31/2016.
 */
public class TimeTableService extends IntentService {
    List<TimeTable> mtimetable;
    Calendar calendar,calendar2;
    Long mTime;
    Date mDt1,mDt2;
    private static final String TAG = "EventService";
    private static final int NOTIFICATION = R.string.cast_notification_connected_message;
    public static final int CLASS_INTERVAL = 1000 * 60 * 30;

    public TimeTableService (){
        super(TAG);
    }

    public TimeTableService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Received intent:"+intent);

        PendingIntent pi = PendingIntent
                .getActivity(this, 0, new Intent(this, StudentTimeTableWeekFragment.class), 0);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
                .setTicker(getResources().getText(R.string.to_sas_home))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle("Checking Timetable")
                .setContentText("Service started")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(NOTIFICATION, notification);
        /*mtimetable = SugarRecord.listAll(TimeTable.class);
        calendar.getInstance();
        mDt1 = calendar.getTime();
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
