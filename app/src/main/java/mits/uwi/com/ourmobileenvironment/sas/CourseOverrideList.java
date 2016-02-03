package mits.uwi.com.ourmobileenvironment.sas;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by User on 1/18/2016.
 */
public class CourseOverrideList {
    private ArrayList<CourseOverride> mOverrides;
    private static CourseOverrideList sCourseList;
    private Context mAppContext;

    private CourseOverrideList (Context appContext) {
        mAppContext = appContext;
        mOverrides = new ArrayList<CourseOverride>();
        mOverrides.add(new CourseOverride(1,
                new Course(54231, "COMP", 1126, "Introduction to Computing I", "The introduction to Computing Course is...", "Undergraduate", "Lecture"),
                "Prerequisite and Test Score Error"));
        mOverrides.add(new CourseOverride(2,new Course(123156, "COMP", 1127, "Introduction to Computing II", "The introduction to Computing Course is...", "Undergraduate", "Lab"),"Lecturer Approval Needed"));
        mOverrides.add(new CourseOverride(3,new Course(678995, "COMP", 3161, "Database Management Systems", "The introduction to Computing Course is...", "Undergraduate", "Lecture"),"Test Score Error"));
        }

    public static CourseOverrideList get (Context c){
        if (sCourseList == null){
            sCourseList = new CourseOverrideList(c.getApplicationContext());
        }
        return sCourseList;
    }
    public ArrayList<CourseOverride> getmCoursesOverrides(){
        return mOverrides;
    }
    public CourseOverride getOverride(int Id){
        for (CourseOverride c : mOverrides){
            if (c.getmId()== Id)
                return c;
        }
        return null;
    }
}
