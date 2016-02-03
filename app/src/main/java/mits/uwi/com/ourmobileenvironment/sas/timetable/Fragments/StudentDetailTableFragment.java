package mits.uwi.com.ourmobileenvironment.sas.timetable.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by User on 10/19/2015.
 */
public class StudentDetailTableFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.timetableFragment_title);
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
        return v;
    }
}
