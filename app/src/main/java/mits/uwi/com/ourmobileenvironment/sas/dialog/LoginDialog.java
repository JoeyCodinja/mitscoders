package mits.uwi.com.ourmobileenvironment.sas.dialog;

import android.annotation.TargetApi;
import android.app.Activity;
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
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers.GlobalRequestHandler;
import mits.uwi.com.ourmobileenvironment.HomeActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sas.course.CourseListFragment;
import mits.uwi.com.ourmobileenvironment.sas.volley.SASAuthRequestListener;

/**
 * Created by Danuel on 30/1/2017.
 */
public class LoginDialog extends DialogFragment {
    public static final String dialogKey = "login";

    private TextView username, password;
    private ViewGroup mContainer;
    private FragmentActivity parentActivity;

    public LoginDialog(){
        // Required empty constructor
    }

    public static LoginDialog newInstance(Activity activity) {
        
        Bundle args = new Bundle();
        
        LoginDialog fragment = new LoginDialog();
        fragment.setArguments(args);
        fragment.setActivity(activity);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.sas_dialog_signin, null);

        password = (TextView)dialogView.findViewById(R.id.password);
        username = (TextView)dialogView.findViewById(R.id.studentId);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView)
          .setPositiveButton("Log in", new DialogInterface.OnClickListener(){
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
        }).setTitle("Login");

        return builder.create();
    }

    private void signIn(HashMap<String, String> credentials, Context context){
        final GlobalRequestHandler globalRequestHandler = GlobalRequestHandler.getInstance(context);
        final HashMap<String, String> studentCreds = credentials;
        globalRequestHandler.getSASAuthToken(credentials, new SASAuthRequestListener(){
            @Override
            public void onResponse(String s) {
                super.onResponse(s);
                globalRequestHandler.getStudentInfo(studentCreds.get("username"),
                        new CourseListFragment(),
                        (AppCompatActivity)parentActivity);
            }
        });
    }

    private void setActivity(Activity activity){
        parentActivity = (FragmentActivity)activity;
    }
}
