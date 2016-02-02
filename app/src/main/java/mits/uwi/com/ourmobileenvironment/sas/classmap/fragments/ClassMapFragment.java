package mits.uwi.com.ourmobileenvironment.sas.classmap.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mits.uwi.com.ourmobileenvironment.R;

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

        return v;
    }
}
