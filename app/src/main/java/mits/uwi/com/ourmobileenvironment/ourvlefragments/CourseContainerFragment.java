package mits.uwi.com.ourmobileenvironment.ourvlefragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 17/06/2015.
 */
public class CourseContainerFragment extends Fragment {

    TextView mCourseContainerFragmentTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course_container, container, false);


        return v;
    }
}
