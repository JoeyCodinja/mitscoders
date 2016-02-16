package mits.uwi.com.ourmobileenvironment.sas.timetable.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sas.Course;
import mits.uwi.com.ourmobileenvironment.sas.CourseList;

/**
 * Created by User on 10/19/2015.
 */
public class StudentDetailTableFragment extends Fragment {
    Spinner spinner;
    Button mRequestOverride;
    private ArrayList<Course> mCourses = new ArrayList<Course>();
    LinearLayout mdetails;
    TextView mTitle,mCrn,mCode,mLvl,mDescr,mType;
    View divider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCourses = CourseList.get(getActivity()).getmCourses();
        getActivity().setTitle(R.string.timetableFragment_title);
        setRetainInstance(true);
    }
    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle(R.string.timetableFragment_title);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student_detail_schedule,container,false);
        Spinner spinner  = (Spinner) v.findViewById(R.id.semester);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.semester_test, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        divider = new View(getActivity());
        divider.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 3));
        divider.setBackgroundColor(getResources().getColor(R.color.accent));


        mdetails = (LinearLayout)v.findViewById(R.id.details);
        if (mCourses==null){
            mTitle = new TextView(getActivity());
            mTitle.setText ("You have no courses in this semester");
            mTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        else{
            for (int i = 0; i< mCourses.size();i++){
                RelativeLayout mlistitem = (RelativeLayout)inflater.inflate(R.layout.fragment_courseinfo,null);
                mRequestOverride = (Button)mlistitem.findViewById(R.id.request_override);
                mRequestOverride.setHeight(2);
                mRequestOverride.setBackgroundColor(getResources().getColor(R.color.accent));
                mRequestOverride.setText("");
                mRequestOverride.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,10));
                mRequestOverride.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                //mRequestOverride.setVisibility(View.GONE);
                mTitle = (TextView)mlistitem.findViewById(R.id.course_title);
                mCrn = (TextView)mlistitem.findViewById(R.id.course_crn);
                mCode =(TextView)mlistitem.findViewById(R.id.course_code);
                mDescr = (TextView)mlistitem.findViewById(R.id.course_descr);
                mLvl = (TextView)mlistitem.findViewById(R.id.course_lvl);
                mTitle.setText(mCourses.get(i).getTitle());
                mCrn.setText("CRN : " + mCourses.get(i).getCRN());
                mCode.setText(mCourses.get(i).getSubj() + " " + Integer.toString(mCourses.get(i).getCourseCode()));
                mDescr.setText(mCourses.get(i).getDescription());
                mLvl.setText(mCourses.get(i).getLevel());
                mdetails.addView(mlistitem,i);
               // mdetails.addView(divider);

            }
        }

        return v;
    }
}
