package mits.uwi.com.ourmobileenvironment.sasfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 16/06/2015.
 */
public class CourseFragment extends Fragment {
    Button mToAddDropFragmentButton, mToTimetableFragmentButton, mToTranscriptFragmentButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflates the view's layout using the respective layout file
        View v = inflater.inflate(R.layout.fragment_course,container,false);

        //Button that transitions us to the Add Drop screen
        mToAddDropFragmentButton = (Button)v.findViewById(R.id.to_addDropCourse_fragment);
        mToAddDropFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starts AddDropCourseFragment
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new AddDropCourseFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .commit();
            }
        });

        mToTimetableFragmentButton = (Button)v.findViewById(R.id.to_timetable_fragment);
        mToTimetableFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starts TimeTableFragment
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new TimeTableFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .commit();
            }
        });

        mToTranscriptFragmentButton = (Button)v.findViewById(R.id.to_transcriptFragment);
        mToTranscriptFragmentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Starts the TranscriptFragment
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new TranscriptFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .commit();
            }
        });

        return v;
    }
}
