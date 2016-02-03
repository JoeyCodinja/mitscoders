package mits.uwi.com.ourmobileenvironment.sasfragments.classmapfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments.TimeTableFragment;

/**
 * Created by Danuel on 16/06/2015.
 */
public class ClassMapFragment extends Fragment {

    Button mToTimeTableFragmentButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_classmap, container, false);

        /*mToTimeTableFragmentButton = (Button)v.findViewById(R.id.to_timetable_fragment_classmap);
        mToTimeTableFragmentButton.setOnClickListener(new View.OnClickListener() {
            @CourseOverride
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new TimeTableFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .commit();
            }
        });*/

        return v;
    }
}
