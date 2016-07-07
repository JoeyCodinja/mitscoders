package mits.uwi.com.ourmobileenvironment.homepagefragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * A simple {@link Fragment} subclass.
 *
 * Use the {@link HomeVideosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeVideosFragment extends Fragment {

    public static HomeVideosFragment newInstance(String param1, String param2) {
        HomeVideosFragment fragment = new HomeVideosFragment();

        return fragment;
    }

    public HomeVideosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_videos, container, false);
    }




}
