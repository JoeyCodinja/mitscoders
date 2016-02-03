package mits.uwi.com.ourmobileenvironment.sas.requestoverride;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by User on 9/27/2015.
 */
public class RequestOverrideDialog extends DialogFragment {
    private static final String REQUEST = "This is the Request";
    private String mRequest;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.confirm_dialog, null);
        //return super.onCreateDialog(savedInstanceState);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.requestOverride_title)
                .setPositiveButton(R.string.request_ok, null)
                .setNegativeButton(R.string.request_cancel,null)
                .create();
    }

}
