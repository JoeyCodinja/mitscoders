package mits.uwi.com.ourmobileenvironment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import mits.uwi.com.ourmobileenvironment.ourvlefragments.CourseListFragment;


public class OurVLEActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ourvle);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.ourvle_fragmentContainer);

        if (fragment == null) {
            fragment = new CourseListFragment();
            fm.beginTransaction()
                    .add(R.id.ourvle_fragmentContainer, fragment)
                    .commit();
        }
    }
}
