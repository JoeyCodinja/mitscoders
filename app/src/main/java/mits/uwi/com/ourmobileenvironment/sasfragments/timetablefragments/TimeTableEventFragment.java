package mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sasfragments.databasefragments.TimeTable;

/**
 * Created by User on 1/16/2016.
 */
public class TimeTableEventFragment extends Fragment {
    EditText descr, location;
    TextView mCancel, mOk;
    RadioGroup mDays1, mDays2;
    Button startTime, endTime;
    String Description, Location;
    int day;
    Date mSemStart, mSemEnd, start_time, end_time;
    private static final String NEW_EVENT = "new Event";
    private static final int REQUEST_START = 0;
    private static final int REQUEST_END = 1;
    private CustomTimeTableDbHelper mHelper;
    private TimeTable mEvent;
    private Calendar calendar = Calendar.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mHelper = new CustomTimeTableDbHelper(getActivity().getApplicationContext());
        super.onCreate(savedInstanceState);
        getSemester();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timetable_event, container, false);
        descr = (EditText) v.findViewById(R.id.description);
        location =  (EditText)v.findViewById(R.id.location);
        mDays1 = (RadioGroup)v.findViewById(R.id.Days1);
        mDays2 = (RadioGroup)v.findViewById(R.id.Days2);

        mDays1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int select = mDays2.getCheckedRadioButtonId();
                day = ((checkedId==R.id.mon)? 2: (checkedId == R.id.tues)? 3:
                        (checkedId == R.id.wed)? 4:5);
                if (select != -1)
                    mDays2.clearCheck();

            }
        });
        mDays2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int select = mDays1.getCheckedRadioButtonId();
                day = ((checkedId==R.id.fri)?6:(checkedId==R.id.sat)?7:1);
                if (select != -1)
                    mDays1.clearCheck();
            }
        });

        startTime = (Button) v.findViewById(R.id.start_time);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                //TimeTableEventDialog dialog = new TimeTableEventDialog();
                TimeTableEventDialog dialog = TimeTableEventDialog.newInstance(Calendar.getInstance().getTime());
                dialog.setTargetFragment(TimeTableEventFragment.this, REQUEST_START);
                dialog.show(fm, NEW_EVENT);

            }
        });
        endTime = (Button) v.findViewById(R.id.end_time);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                //TimeTableEventDialog dialog = new TimeTableEventDialog();
                TimeTableEventDialog dialog = TimeTableEventDialog.newInstance(Calendar.getInstance().getTime());
                dialog.setTargetFragment(TimeTableEventFragment.this, REQUEST_END);
                dialog.show(fm, NEW_EVENT);
            }

        });

        mCancel = (TextView) v.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        mOk = (TextView) v.findViewById(R.id.ok);
        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Description = descr.getText().toString();
                Location = location.getText().toString();
                mEvent = new TimeTable(start_time,end_time,mSemStart,mSemEnd, Description,Location,day);
                mEvent.save();
            }
        });
        //Mon = (RadioButton)v.findViewById(R.id.mon);*/
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String am_pm;
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_START) {
            start_time = (Date) data
                    .getSerializableExtra(TimeTableEventDialog.REQUEST);
            //Toast.makeText(getActivity().getApplicationContext(), start_time + "", Toast.LENGTH_SHORT).show();
            calendar.setTime(start_time);
            am_pm = (calendar.get(Calendar.AM_PM) == 0 ? "AM" : "PM");
            startTime.setText("Start Time :" + String.format("  %02d:%02d:00 ", calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE)) + " " + am_pm);
        }
        if (requestCode == REQUEST_END) {
            end_time = (Date) data.getSerializableExtra(TimeTableEventDialog.REQUEST);
            calendar.setTime(end_time);
            am_pm = (calendar.get(Calendar.AM_PM) == 0 ? "AM" : "PM");
            endTime.setText("End Time : " + String.format("  %02d:%02d:00 ", calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE)) + " " + am_pm);
        }

    }

    public void getSemester() {
        calendar = Calendar.getInstance();
        if (calendar.get(Calendar.MONTH) <= 4) {
            calendar.set(Calendar.YEAR, 1,11);
            mSemStart= calendar.getTime();
            calendar.set(Calendar.YEAR, 4,30);
            mSemEnd = calendar.getTime();
        }
        else if(calendar.get(Calendar.MONTH) >7){
            calendar.set(Calendar.YEAR, 8,31);
            mSemStart= calendar.getTime();
            calendar.set(Calendar.YEAR, 11,30);
            mSemEnd = calendar.getTime();

        }
        else{
            calendar.set(Calendar.YEAR, 6,1);
            mSemStart= calendar.getTime();
            calendar.set(Calendar.YEAR, 7,31);
            mSemEnd = calendar.getTime();
        }
    }


}
