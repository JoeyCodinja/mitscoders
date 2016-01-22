package mits.uwi.com.ourmobileenvironment.sasfragments.coursefragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ListView;
import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sasfragments.Course;
import mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments.TimeTableFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.transcriptfragments.TranscriptFragment;

/**
 * Created by Danuel on 16/06/2015.
 */
public class CourseFragment extends Fragment {
    TextView mNotify;
    ListView listview;
    ArrayList<Course> mDummyCourses = Course.getmCourses();
    CourseAdapter adapterc;
    ArrayAdapter <Course> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.to_sas_home);
        //getActivity().setTitle(R.string.title_activity_sas);
        //getActivity().getActionBar().setSubtitle("Registered Coursed");


    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.to_sas_home);
        adapter.notifyDataSetChanged();
       // getActivity().setTitle(R.string.title_activity_sas);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflates the view's layout using the respective layout file
        View v = inflater.inflate(R.layout.fragment_course, container, false);

        /*RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.course_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        CourseAdapter courseAdapter = new CourseAdapter(Course.getmCourses());

        recyclerView.setAdapter(courseAdapter);*/

        mNotify = (TextView) v.findViewById(R.id.course_notify);
        //super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter <Course> (getActivity(),
                android.R.layout.simple_list_item_1,
                mDummyCourses);
        adapterc = new CourseAdapter(mDummyCourses);

        listview = (ListView)v.findViewById(R.id.courses_list);
        listview.setEmptyView(mNotify);
        listview.setAdapter(adapterc);

        return v;
    }

    private class CourseAdapter extends ArrayAdapter<Course>{

        public CourseAdapter (ArrayList<Course> courses){
            super(getActivity(),0,courses);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.fragment_course, null);
            }
        // Configure the view for this Crime
            Course c = getItem(position);
            TextView title = (TextView)convertView.findViewById(R.id.course_title);
            title.setText(c.getTitle());

            TextView subject = (TextView)convertView.findViewById(R.id.course_subject);
            title.setText(c.getSubj());

            TextView ccode = (TextView)convertView.findViewById(R.id.course_code);
            ccode.setText(Integer.toString(c.getCourseCode()));

            TextView type = (TextView)convertView.findViewById(R.id.course_type);
            type.setText(c.getType());
            /*ImageView course = (ImageView) convertView.findViewById(R.id.course);
            if (c.getType().charAt(0)=='L' && c.getType().charAt(1)=='e'){
                course.setImageResource(R.drawable.lecture);
            }
            elsec.getType().charAt(0)=='L' && c.getType().charAt(1)=='e'*/


            return convertView;
        }
    }

    /*private class  CourseViewHolder extends RecyclerView.ViewHolder {
        protected TextView name,num,title,dept;
        protected ImageView rimg;

        public CourseViewHolder(View v){
            super(v);
            /*crn=(TextView) v.findViewById(R.id.course_crn);
            num=(TextView) v.findViewById(R.id.number);
            title=(TextView)v.findViewById(R.id.title);
            dept=(TextView)v.findViewById(R.id.department);
            rimg=(ImageView)v.findViewById(R.id.roundimg);

        }
    }

    private class CourseAdapter extends RecyclerView.Adapter<CourseFragment.CourseViewHolder> {
        private ArrayList<Course> clist;


        public CourseAdapter(ArrayList<Course> clist){
            this.clist=clist;

        }
        @Override
        public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(CourseViewHolder holder, int position) {

        }

        public int getItemCount(){
        return clist.size();
        }
    }*/
}
