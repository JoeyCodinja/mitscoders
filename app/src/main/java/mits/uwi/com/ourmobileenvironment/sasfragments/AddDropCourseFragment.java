package mits.uwi.com.ourmobileenvironment.sasfragments;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by Danuel on 15/06/2015.
 */
public class AddDropCourseFragment extends Fragment {

    Button mToCourseFragmentButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState){
        //Inflates the view's layout using the respective layout file
        View v = inflater.inflate(R.layout.fragment_add_drop, parent, false);

        //Button that transitions us to the CourseFragment screen
        mToCourseFragmentButton = (Button)v.findViewById(R.id.to_courseFragment);
        mToCourseFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starts CourseFragment
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
