package mits.uwi.com.ourmobileenvironment.sasfragments.requestoverridefragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sasfragments.Course;
import mits.uwi.com.ourmobileenvironment.sasfragments.CourseOverride;
import mits.uwi.com.ourmobileenvironment.sasfragments.CourseOverrideList;
import mits.uwi.com.ourmobileenvironment.sasfragments.coursefragments.CourseInfoActivity;
import mits.uwi.com.ourmobileenvironment.sasfragments.coursefragments.CourseInfoFragment;

/**
 * CRN
 * COURSE
 * STREAM
 * ACTION
 * COURSE TITLE
 * STATUS/Over-ride
 * Note to Lecturer
 * Created by User on 11/5/2015.
 */
public class RequestOverrideListFragment extends Fragment {
     ArrayList<CourseOverride> mOverride;
     ListView mOverridesList;
     ArrayAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        mOverride = CourseOverrideList.get(getActivity()).getmCoursesOverrides();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.fragment_request_override_list,container,false);
        mOverridesList = (ListView)v.findViewById(R.id.overrides);
        adapter = new ArrayAdapter<CourseOverride>(getActivity(),
                android.R.layout.simple_list_item_1,
                mOverride);
        mOverridesList.setAdapter(adapter);
        mOverridesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //navigate to course info page
                CourseOverride c = (CourseOverride)mOverridesList.getItemAtPosition(position);
                Intent i = new Intent (getActivity(), CourseInfoActivity.class);
                i.putExtra(CourseInfoFragment.EXTRA_COURSE_ID,c.getMcourse().getCRN());
                        startActivity(i);
            }
        });
        return v;
    }
}
