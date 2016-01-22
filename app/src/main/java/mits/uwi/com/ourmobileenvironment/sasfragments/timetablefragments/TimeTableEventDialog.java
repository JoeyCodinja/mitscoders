package mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by User on 11/13/2015.
 */
public class TimeTableEventDialog extends DialogFragment {
    private static final String REQUEST = "This is the Request";
    private String mRequest;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_timetable_event_dialog, null);
        //return super.onCreateDialog(savedInstanceState);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.new_event)
                .setPositiveButton(R.string.request_ok, null)
                .setNegativeButton(R.string.request_cancel,null)
                .create();
    }
}
