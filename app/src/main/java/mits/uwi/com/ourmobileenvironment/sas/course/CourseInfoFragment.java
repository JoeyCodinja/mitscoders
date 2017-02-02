package mits.uwi.com.ourmobileenvironment.sas.course;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sas.Course;
import mits.uwi.com.ourmobileenvironment.sas.CourseList;
import mits.uwi.com.ourmobileenvironment.sas.requestoverride.RequestOverrideFragment;


/**
 * Created by User on 9/18/2015.
 */
public class CourseInfoFragment extends Fragment {
    Button mRequestOverride;
    TextView mTitle,mCrn,mCode,mLvl,mDescr,mType;
    public static final String EXTRA_COURSE_ID="Course CRN";
    private Course mCourse;
    private int courseId;
    private FloatingActionButton fab;

    public CourseInfoFragment newInstance(JSONObject courseObject){
        Bundle args = new Bundle();
        CourseInfoFragment fragment = new CourseInfoFragment();
        try {
            Course course = new Course(
                    Integer.valueOf(courseObject.get("crn").toString()),
                    courseObject.get("subjectCode").toString(),
                    Integer.valueOf(courseObject.get("courseNumber").toString()),
                    courseObject.get("courseTitle").toString(),
                    "",
                    "",
                    courseObject.get("scheduleType").toString()
            );
            args.putParcelable("course", course);
        } catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(),
                    "Error Loading Course Info",
                    Toast.LENGTH_LONG).show();

        }
        fragment.setArguments(args);
        return fragment;

    }
    
    // or attachToRecyclerView
    public static CourseInfoFragment newInstance(int CRN){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_COURSE_ID, CRN);
        CourseInfoFragment fragment = new CourseInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            courseId = (int)getActivity().getIntent().getSerializableExtra(EXTRA_COURSE_ID);//(int)getArguments().getSerializable(EXTRA_COURSE_ID);
            mCourse = CourseList.get(getActivity()).getCourse(courseId);
            getActivity().setTitle(mCourse.getTitle());
            ((CourseInfoActivity )getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setRetainInstance(true);
        }
        catch (NullPointerException e){
            mCourse = savedInstanceState.getParcelable("course");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_courseinfo, parent, false);

        mRequestOverride = (Button)v.findViewById(R.id.request_override);
        mRequestOverride.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new RequestOverrideFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        mTitle = (TextView)v.findViewById(R.id.course_title);
        mCrn = (TextView)v.findViewById(R.id.course_crn);
        mCode =(TextView)v.findViewById(R.id.course_code);
        mDescr = (TextView)v.findViewById(R.id.course_descr);
        mLvl = (TextView)v.findViewById(R.id.course_lvl);
        mTitle.setText(mCourse.getTitle());
        mCrn.setText("CRN : " + mCourse.getCRN());
        mCode.setText(mCourse.getSubj() + " " + Integer.toString(mCourse.getCourseCode()));
        mDescr.setText(mCourse.getDescription());
        mLvl.setText(mCourse.getLevel());


        return v;
    }


}
