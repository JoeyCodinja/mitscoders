package mits.uwi.com.ourmobileenvironment.sasfragments.transcriptfragments;

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

    Button mRequestTranscriptButton, mViewTranscriptButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transcript, container, false);

        mRequestTranscriptButton = (Button)v.findViewById(R.id.request_transcript);
        mRequestTranscriptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm =getActivity().getSupportFragmentManager();
                Fragment fragment = new TranscriptRequestFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        mViewTranscriptButton = (Button)v.findViewById(R.id.view_transcript);
        mViewTranscriptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm =getActivity().getSupportFragmentManager();
                //v = (LinearLayout) inflater.inflate(R.layout.fragment_transcript_web,container, false);
                Fragment fragment = new TranscriptViewFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }
}
