package mits.uwi.com.ourmobileenvironment.sas.timetable.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by User on 1/31/2016.
 */
public class TimeTableDeleteDialog extends DialogFragment {


    public TimeTableDeleteDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_timetable_delete_item, null);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(R.string.request_ok, null)
                .setNegativeButton(R.string.request_cancel,null)
                .create();
    }
}
