package mits.uwi.com.ourmobileenvironment.sasfragments;



import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by Danuel on 15/06/2015.
 */
public class AddDropCourseFragment extends Fragment {
    TextView mCancel, mDone;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.adddropFragment_title);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.adddropFragment_title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState){
        //Inflates the view's layout using the respective layout file
        View v = inflater.inflate(R.layout.fragment_add_drop, parent, false);


        Spinner spinner  = (Spinner) v.findViewById(R.id.semester);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.semester_test, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        mCancel = (TextView)v.findViewById(R.id.addDrop_Cancel);
        mCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Remove Add Drop Fragment
                getActivity().getSupportFragmentManager().popBackStack();
            }

        });

        mDone = (TextView)v.findViewById(R.id.addDrop_done);
        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Done Adding Courses
                //should add method to append courses to course list.
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return v;
    }
}
