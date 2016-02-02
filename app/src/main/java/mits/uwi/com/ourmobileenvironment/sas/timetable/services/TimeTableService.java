package mits.uwi.com.ourmobileenvironment.sas.timetable.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by User on 1/31/2016.
 */
public class TimeTableService extends IntentService {
    private static final String TAG = "EventService";

    public TimeTableService (){
        super(TAG);
    }

    public TimeTableService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Received intent:"+intent);
        String resultId;

    }
}
