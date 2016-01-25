package mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by User on 1/16/2016.
 */
public class TimeTableEventFragment extends Fragment{
    EditText descr, location;
    RadioGroup mDays1, mDays2;
    RadioButton Mon,Tues,Weds,Thurs,Fri,Sat,Sun;
    Button startTime, endTime;
    String Description, Location;
    int day,start_time,end_time;
    private static final String NEW_EVENT = "new Event";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timetable_event, container, false);
        descr = (EditText)v.findViewById(R.id.description);
        //Description = descr.getText().toString();
        //location =  (EditText)v.findViewById(R.id.location);
        //Location = location.getText().toString();

        startTime = (Button)v.findViewById(R.id.start_time);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimeTableEventDialog dialog = new TimeTableEventDialog();
                dialog.show(fm, NEW_EVENT);

            }
        });
        endTime = (Button)v.findViewById(R.id.end_time);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimeTableEventDialog dialog = new TimeTableEventDialog();
                dialog.show(fm, NEW_EVENT);
            }

        });
        //Mon = (RadioButton)v.findViewById(R.id.mon);*/
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultcode, Intent data){

    }
}
