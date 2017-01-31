package mits.uwi.com.ourmobileenvironment.sas.volley;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sas.course.CourseListFragment;

public class SASDataRequestListener implements Response.Listener<JSONObject> {
    public static final String STUDENT_REQUEST = "STUDENT_REQUEST";
    public static final String COURSE_CC_REQUEST = "CC_REQUEST";
    public static final String COURSE_CRN_REQUEST = "CRN_REQUEST";

    private Fragment resultantFragment;
    private AppCompatActivity fragmentParent;


    public SASDataRequestListener(Fragment resultantFragment, AppCompatActivity fragmentParent) {
        this.resultantFragment = resultantFragment;
        this.fragmentParent = fragmentParent;
    }

    public void onResponse(JSONObject response) {
        if (response.has("studentId")) {
            //StudentRegistration Object

        } else if (response.has("sections")) {
            //Course Object
        } else if (response.has("crn")) {
            //Course Object (CRN Specific)
        } else {
            // Unrecognizable response found
            // raise exception
        }

        if (resultantFragment.getClass() == CourseListFragment.class){
            resultantFragment = ((CourseListFragment)resultantFragment).newInstance(response);
            FragmentManager fm = fragmentParent.getSupportFragmentManager();
            fm.beginTransaction()
                    .add(R.id.sas_fragmentContainer, resultantFragment)
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .commit();
        }
    }
}
