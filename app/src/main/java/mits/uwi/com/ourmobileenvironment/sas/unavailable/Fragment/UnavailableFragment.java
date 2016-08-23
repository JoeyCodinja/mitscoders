package mits.uwi.com.ourmobileenvironment.sas.unavailable.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by jevon.butler on 20/08/2016.
 */
public class UnavailableFragment extends Fragment {
    public UnavailableFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Student Administrative Services");

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Student Administrative Services");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_unavailable, container, false);
        return v;
    }
}
