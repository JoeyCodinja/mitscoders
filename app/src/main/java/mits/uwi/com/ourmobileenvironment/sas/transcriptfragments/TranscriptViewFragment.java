package mits.uwi.com.ourmobileenvironment.sas.transcriptfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by User on 10/8/2015.
 */
public class TranscriptViewFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.transcriptFragment_title);
    }
    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle(R.string.transcriptFragment_title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transcript_web,container, false);
        return v;
    }
}
