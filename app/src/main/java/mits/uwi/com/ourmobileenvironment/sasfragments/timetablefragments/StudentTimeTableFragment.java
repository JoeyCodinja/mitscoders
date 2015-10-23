package mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by User on 10/19/2015.
 */
public class StudentTimeTableFragment extends Fragment {
    CalendarView mTimeTable;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student_schedule,container,false);

        mTimeTable = (CalendarView)v.findViewById(R.id.timetable);

        mTimeTable.setShowWeekNumber(false);
        mTimeTable.setFirstDayOfWeek(2);
        /*mTimeTable.setSelectedWeekBackgroundColor(getResources().getColor(R.color.blue_background));
        mTimeTable.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
        mTimeTable.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
        mTimeTable.setSelectedDateVerticalBar(R.color.blue_background);*/
        mTimeTable.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText(getActivity(),day+"/"+month+"/"+year, Toast.LENGTH_LONG)
                        .show();
            }
        });

        return v;
    }
}
