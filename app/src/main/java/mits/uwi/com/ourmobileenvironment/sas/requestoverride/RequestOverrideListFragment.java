package mits.uwi.com.ourmobileenvironment.sas.requestoverride;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sas.Course;
import mits.uwi.com.ourmobileenvironment.sas.CourseOverride;
import mits.uwi.com.ourmobileenvironment.sas.CourseOverrideList;
import mits.uwi.com.ourmobileenvironment.sas.course.CourseInfoActivity;
import mits.uwi.com.ourmobileenvironment.sas.course.CourseInfoFragment;

/**
 * CRN
 * COURSE
 * STREAM
 * ACTION
 * COURSE TITLE
 * STATUS/Over-ride
 * Note to Lecturer
 * Created by User on 11/5/2015.
 */
public class RequestOverrideListFragment extends ListFragment {
     ArrayList<CourseOverride> mOverride;
     private CourseOverrideAdapter adapter;
     //ListView mOverridesList;
     //ArrayAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.requestOverride_title);
        mOverride = CourseOverrideList.get(getActivity()).getmCoursesOverrides();
        adapter = new CourseOverrideAdapter(mOverride);
        setListAdapter(adapter);
        setRetainInstance(true);
    }

    /*@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.fragment_request_override_list,container,false);
       /* mOverridesList = (ListView)v.findViewById(R.id.overrides);
        adapter = new ArrayAdapter<CourseOverride>(getActivity(),
                android.R.layout.simple_list_item_1,
                mOverride);
        mOverridesList.setAdapter(adapter);
        mOverridesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //navigate to course info page
                CourseOverride c = (CourseOverride)mOverridesList.getItemAtPosition(position);
                Intent i = new Intent (getActivity(), CourseInfoActivity.class);
                i.putExtra(CourseInfoFragment.EXTRA_COURSE_ID,c.getMcourse().getCRN());
                        startActivity(i);
            }
        });
        return v;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.requestOverride_title);
        ((CourseOverrideAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
        setEmptyText("" + R.string.noOverrides);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        CourseOverride c = (CourseOverride)(getListAdapter()).getItem(position);
        Intent i = new Intent (getActivity(), CourseInfoActivity.class);
        i.putExtra(CourseInfoFragment.EXTRA_COURSE_ID, c.getMcourse().getCRN());
        startActivity(i);
    }

    private class CourseOverrideAdapter extends ArrayAdapter<CourseOverride> {

        public CourseOverrideAdapter (ArrayList<CourseOverride> overrides){
            super(getActivity(), 0, overrides);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.fragment_request_override_list, null);
            }

            // Configure the view for this Crime
            CourseOverride c = getItem(position);

            TextView subject = (TextView)convertView.findViewById(R.id.course_subject);
            subject.setText(c.getMcourse().getSubj());

            TextView code = (TextView)convertView.findViewById(R.id.course_code);
            code.setText(Integer.toString(c.getMcourse().getCourseCode()));

            TextView title = (TextView)convertView.findViewById(R.id.course_title);
            title.setText(c.getMcourse().getTitle());

            TextView status = (TextView)convertView.findViewById(R.id.Status);
            status.setText(c.getStatus());

            TextView note = (TextView) convertView.findViewById(R.id.Note);
            note.setText(c.getNote());

           /* ImageView alert = (ImageView)convertView.findViewById(R.id.alert);
            alert.setImageDrawable(TextDrawable.builder().beginConfig().bold().endConfig()
                    .buildRound("!" ,getResources().getColor(R.color.Orange)));//R.drawable.lecture);*/

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
