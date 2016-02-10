package mits.uwi.com.ourmobileenvironment.sas.timetable.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import mits.uwi.com.ourmobileenvironment.sas.timetable.services.TimeTableService;

/**
 * Created by peoplesoft on 2/9/2016.
 */
public class TimeTableReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i  = new Intent(context, TimeTableService.class);
        i.putExtra("Getting","Times");
        context.startService(i);

    }
}
