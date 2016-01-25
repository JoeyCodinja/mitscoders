package mits.uwi.com.ourmobileenvironment.sasfragments.coursefragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.SASActivity;
import mits.uwi.com.ourmobileenvironment.sasfragments.Course;
import mits.uwi.com.ourmobileenvironment.sasfragments.CourseList;

/**
 * Created by User on 11/14/2015.
 */
public class CourseListFragment extends ListFragment {
    private ArrayList <Course> mCourses;
    private CourseAdapter adapter;
    private TextView mNotify;
    private String mTitle;
    private FloatingActionButton fab;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.to_sas_home);
        mCourses = CourseList.get(getActivity()).getmCourses(); //Course.getmCourses();
        adapter = new CourseAdapter(mCourses);
        setListAdapter(adapter);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);//;getActivity().getLayoutInflater()
                //.inflate(R.layout.custom_sas_courses,container,false,);
        fab = (FloatingActionButton)v.findViewById(R.id.fab);
        //fab.setVisibility(getView().GONE);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Fab Pressed", Toast.LENGTH_SHORT);
            }
        });*/

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.to_sas_home);
        ((CourseAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
        setEmptyText("" + R.string.noCourses);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        Course c = (Course)(getListAdapter()).getItem(position);
        Intent i = new Intent (getActivity(), CourseInfoActivity.class);
        i.putExtra(CourseInfoFragment.EXTRA_COURSE_ID, c.getCRN());
        startActivity(i);
    }

    private class CourseAdapter extends ArrayAdapter<Course> {

        public CourseAdapter (ArrayList<Course> courses){
            super(getActivity(),0,courses);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.custom_sas_courses, null);
            }

            // Configure the view for this Crime
            Course c = getItem(position);
            TextView title = (TextView)convertView.findViewById(R.id.course_title);
            title.setText(c.getTitle());
            mTitle = c.getTitle();
            TextView subject = (TextView)convertView.findViewById(R.id.course_subject);
            subject.setText(c.getSubj());

            TextView code = (TextView)convertView.findViewById(R.id.course_code);
            code.setText(Integer.toString(c.getCourseCode()));
            TextView type = (TextView)convertView.findViewById(R.id.course_type);
            type.setText(c.getType());
            ImageView course = (ImageView) convertView.findViewById(R.id.course);
            if (c.getType().charAt(0)=='L' && c.getType().charAt(1)=='e'){

                course.setImageDrawable(TextDrawable.builder().beginConfig().bold().endConfig()
                        .buildRound("" + mTitle.charAt(0),getResources().getColor(R.color.accent_1)));//R.drawable.lecture);
            }
            else if (c.getType().charAt(0)=='L' && c.getType().charAt(1)==('a')){
                course.setImageDrawable(TextDrawable.builder().beginConfig().bold().endConfig()
                        .buildRound("" + mTitle.charAt(0), getResources().getColor(R.color.event_color_03)));
            }else{
                course.setImageDrawable(TextDrawable.builder().beginConfig().bold().endConfig()
                        .buildRound("" + mTitle.charAt(0), getResources().getColor(R.color.event_color_04)));
            }
            /*fab = (FloatingActionButton)convertView.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(), "Fab Pressed", Toast.LENGTH_SHORT);
                }
            });*/
            return convertView;
        }
    }
}
