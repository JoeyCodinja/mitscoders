package mits.uwi.com.ourmobileenvironment.sasfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments.TimeTableFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.transcriptfragments.TranscriptFragment;

/**
 * Created by Danuel on 16/06/2015.
 */
public class CourseFragment extends Fragment {
    Button mToAddDropFragmentButton, mToTimetableFragmentButton, mToTranscriptFragmentButton;
    TextView mNotify;
    ArrayList<Course> mDummyCourses = Course.getmCourses();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.title_activity_sas);
        //getActivity().getActionBar().setSubtitle("Registered Coursed");


    }

    @Override
    public void onResume() {
        super.onResume();
        //getActivity().setTitle(R.string.title_activity_sas);
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

        if (mDummyCourses.size()== 0)
           mNotify.setText(R.string.noCourses);
        else
            mNotify.setText(R.string.course_exist);
        //super.onCreate(savedInstanceState);
        ArrayAdapter <Course> adapter = new ArrayAdapter <Course> (getActivity(),
                android.R.layout.simple_list_item_1,
                mDummyCourses);
        ListView listview = (ListView)v.findViewById(R.id.courses_list);
        listview.setAdapter(adapter);



        //Button that transitions us to the Add Drop screen
        mToAddDropFragmentButton = (Button)v.findViewById(R.id.to_addDropCourse_fragment);
        mToAddDropFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starts AddDropCourseFragment
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new AddDropCourseFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        mToTimetableFragmentButton = (Button)v.findViewById(R.id.to_timetable_fragment);
        mToTimetableFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starts TimeTableFragment
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new TimeTableFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        mToTranscriptFragmentButton = (Button)v.findViewById(R.id.to_transcriptFragment);
        mToTranscriptFragmentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Starts the TranscriptFragment
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new TranscriptFragment();

                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
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
