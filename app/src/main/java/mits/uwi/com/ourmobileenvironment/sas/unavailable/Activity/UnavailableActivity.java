package mits.uwi.com.ourmobileenvironment.sas.unavailable.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.ToprightBar;
import mits.uwi.com.ourmobileenvironment.sas.unavailable.Fragment.UnavailableFragment;

/**
 * Created by jevon.butler on 20/08/2016.
 */
public class UnavailableActivity extends AppCompatActivity {
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unavailable);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.UnContainer);
        if (fragment == null) {
            fragment = new UnavailableFragment();//CourseFragment();
            fm.beginTransaction()
                    .add(R.id.UnContainer, fragment)
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .commit();
        }
        ToprightBar.setTopOverflow(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
