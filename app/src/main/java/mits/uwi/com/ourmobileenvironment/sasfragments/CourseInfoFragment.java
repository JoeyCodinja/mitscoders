package mits.uwi.com.ourmobileenvironment.sasfragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by User on 9/18/2015.
 */
public class CourseInfoFragment extends Fragment {
    Button mRequestOverride;
    private static final String OVER_RIDE = "Request Override";
    public CourseInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.title_activity_sas);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_courseinfo, parent, false);

        mRequestOverride = (Button)v.findViewById(R.id.request_override);
        mRequestOverride.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                RequestOverrideDialog dialog = new RequestOverrideDialog();
                dialog.show(fm, OVER_RIDE);
            }
        });

        return v;
    }


}
