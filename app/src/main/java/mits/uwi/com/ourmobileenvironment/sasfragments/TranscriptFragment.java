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
public class TranscriptFragment extends Fragment {

    Button mToCourseFragmentButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transcript, container, false);

        mToCourseFragmentButton = (Button)v.findViewById(R.id.to_courseFragment_transcript);
        mToCourseFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new CourseFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer,fragment)
                        .commit();
            }
        });

        return v;
    }
}
