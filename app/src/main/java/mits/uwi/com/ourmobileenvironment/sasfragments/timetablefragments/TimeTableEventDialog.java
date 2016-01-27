package mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by User on 11/13/2015.
 */
public class TimeTableEventDialog extends DialogFragment {
    protected static final String REQUEST = "This is the Request";
    private String mRequest;
    private Date mDate;

    public static TimeTableEventDialog newInstance (Date date) {
        Bundle args = new Bundle();
        args.putSerializable(REQUEST, date);

        TimeTableEventDialog fragment = new TimeTableEventDialog();
        fragment.setArguments(args);

        return fragment;
    }

    private void sendResult(int resultCode){
        if(getTargetFragment()==null)
            return;
        Intent i = new Intent();
        i.putExtra(REQUEST,mDate);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(),resultCode,i);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate  = (Date) getArguments().getSerializable(REQUEST);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_timetable_event_dialog, null);

        TimePicker mtimepicker = (TimePicker)v.findViewById(R.id.timePicker);
        mtimepicker.setCurrentHour(hours);
        mtimepicker.setCurrentMinute(minutes);

        mtimepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                c.set(Calendar.MINUTE,minute);
                mDate = c.getTime()  ;
                getArguments().putSerializable(mRequest,mDate);

            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                //.setTitle(R.string.new_event)
                .setPositiveButton(R.string.request_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .setNegativeButton(R.string.request_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_CANCELED);
                    }
                })
                .create();
    }

}
