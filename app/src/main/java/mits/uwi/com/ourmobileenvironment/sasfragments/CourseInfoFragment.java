package mits.uwi.com.ourmobileenvironment.sasfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by User on 9/18/2015.
 */
public class CourseInfoFragment extends Fragment {
    public CourseInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.CourseInfoFragment);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_courseinfo, parent, false);


        return v;
    }
}
