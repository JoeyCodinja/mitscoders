package mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sasfragments.classmapfragments.ClassMapActivity;

/**
 * Created by Danuel on 16/06/2015.
 */
public class TimeTableFragment extends Fragment {

    Button mToClassMapFragmentButton, mToWeek,mToStudentDetailTimetable, mToStudentTimetable, mToTeachingTimeTable, mToDepartmentTimeTable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.timetableFragment_title);

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.timetableFragment_title);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timetable, container, false);

        mToClassMapFragmentButton = (Button)v.findViewById(R.id.to_classmapFragment);
        mToClassMapFragmentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ClassMapActivity.class);
                //ClassMapActivity.class
                // i.putExtra(CourseInfoFragment.EXTRA_COURSE_ID, c.getCRN());
                startActivity(i);
                //Starts ClassMapFragment
                /*FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new StudentTimeTableWeekFragment();//new ClassMapFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();*/
            }
        });

        mToStudentDetailTimetable = (Button)v.findViewById(R.id.to_student_detail_timetable);
        mToStudentDetailTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new StudentDetailTableFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        mToStudentTimetable = (Button)v.findViewById(R.id.to_student_timetable);
        mToStudentTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new StudentTimeTableWeekFragment();//StudentTimeTableFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        mToTeachingTimeTable = (Button)v.findViewById(R.id.to_teaching_timetable);
        mToTeachingTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new TeachingTimeTableFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        mToDepartmentTimeTable = (Button)v.findViewById(R.id.to_timetable_by_dept);
        mToDepartmentTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new TimeTableByDeptFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer,fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }
}
