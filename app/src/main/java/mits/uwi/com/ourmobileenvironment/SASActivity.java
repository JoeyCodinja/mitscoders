package mits.uwi.com.ourmobileenvironment;



import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import mits.uwi.com.ourmobileenvironment.sasfragments.AddDropCourseFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.CourseFragment;


public class SASActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sas);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.sas_fragmentContainer);

        if (fragment == null){
            fragment = new CourseFragment();
            fm.beginTransaction()
                    .add(R.id.sas_fragmentContainer, fragment)
                    .commit();
        }


    }
}
