package mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by User on 10/19/2015.
 */
public class TeachingTimeTableFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.timetableFragment_title);
    }
    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle(R.string.timetableFragment_title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_teaching_timetable,container,false);

        Spinner spinner  = (Spinner) v.findViewById(R.id.semester);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.semester_test, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return v;
    }
}
