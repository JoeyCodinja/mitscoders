package mits.uwi.com.ourmobileenvironment.sasfragments.requestoverridefragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * CRN
 * COURSE
 * STREAM
 * ACTION
 * COURSE TITLE
 * STATUS/Over-ride
 * Note to Lecturer
 * Created by User on 11/5/2015.
 */
public class RequestOverrideListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.fragment_request_override_list,container,false);
        return v;
    }
}
