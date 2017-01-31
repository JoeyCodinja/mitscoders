package mits.uwi.com.ourmobileenvironment.sas.dialog;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers.GlobalRequestHandler;
import mits.uwi.com.ourmobileenvironment.HomeActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sas.course.CourseListFragment;

/**
 * Created by Danuel on 30/1/2017.
 */
public class LoginDialog extends DialogFragment {
    public static final String dialogKey = "login";

    private TextView username, password;
    private ViewGroup mContainer;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("Log in", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Sign-in
                // Remove the dialog
                // Load the CourseListFragment
                LoginDialog.this.getDialog().dismiss();
                HashMap<String, String> credentials = new HashMap<>();
                credentials.put("username", username.getText().toString());
                credentials.put("password", password.getText().toString());
                signIn(credentials, getActivity().getApplicationContext());


            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Return to the HomeActivity
                LoginDialog.this.getDialog().cancel();
                Intent i = new Intent(getActivity(), HomeActivity.class);
                startActivity(i);

            }
        }).setOnDismissListener( new DialogInterface.OnDismissListener(){
            @Override
            public void onDismiss(DialogInterface dialog) {
                // Never dismiss the dialog
                onCreateDialog(null);
            }
        });

        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getActivity().getWindow().requestFeature();
        View v =inflater.inflate(R.layout.sas_dialog_signin, container, false);

        mContainer = container;

        username = ((EditText)v.findViewById(R.id.studentId));
        password = ((EditText)v.findViewById(R.id.password));

        return v;
    }

    private void signIn(HashMap<String, String> credentials, Context context){
        GlobalRequestHandler globalRequestHandler = GlobalRequestHandler.getInstance(context);
        globalRequestHandler.getSASAuthToken(credentials);
        globalRequestHandler.getStudentInfo(credentials.get("username"),
                CourseListFragment.instantiate(getActivity().getApplicationContext(),
                                               CourseListFragment.TAG),
                (AppCompatActivity)getActivity());
    }
}
