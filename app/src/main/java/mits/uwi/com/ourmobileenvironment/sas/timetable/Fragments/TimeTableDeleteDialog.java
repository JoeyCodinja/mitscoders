package mits.uwi.com.ourmobileenvironment.sas.timetable.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.orm.SugarRecord;

import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sas.sas_database.TimeTable;

/**
 * Created by User on 1/31/2016.
 */
public class TimeTableDeleteDialog extends DialogFragment {
    protected static final String REQUEST = "Remove Event";
    private String mRequest;
    private long mEvent;
    List<TimeTable> mtimeTable;


    public static TimeTableDeleteDialog newInstance (long event) {
        Bundle args = new Bundle();
        args.putSerializable(REQUEST, event);

        TimeTableDeleteDialog fragment = new TimeTableDeleteDialog();
        fragment.setArguments(args);

        return fragment;
    }

    private void sendResult(int resultCode){
        if(getTargetFragment()==null)
            return;
        Intent i = new Intent();
        i.putExtra(REQUEST,mEvent);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(),resultCode,i);
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_timetable_delete_item, null);
        mEvent  = (long) getArguments().getSerializable(REQUEST);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
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
