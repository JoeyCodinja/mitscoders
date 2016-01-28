package mits.uwi.com.ourmobileenvironment.sas.requestoverride;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by User on 10/2/2015.
 */
public class RequestOverrideFragment extends Fragment {
    TextView mSubmit, mCancel;
    private static final String OVER_RIDE = "Request CourseOverride";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.requestOverride_title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_request_override, container, false);

        mSubmit = (TextView)v.findViewById(R.id.request_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                RequestOverrideDialog dialog = new RequestOverrideDialog();
                dialog.show(fm, OVER_RIDE);
            }
        });

        mCancel = (TextView)v.findViewById(R.id.request_cancel);
        mCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });
        return v;
    }
}
