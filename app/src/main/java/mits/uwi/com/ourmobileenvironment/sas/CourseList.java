package mits.uwi.com.ourmobileenvironment.sas;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by User on 11/14/2015.
 */
public class CourseList {
    private ArrayList<Course> mCourses;
    private static CourseList sCourseList;
    private Context mAppContext;

    private CourseList (Context appContext) {
        mAppContext = appContext;
        mCourses = new ArrayList<Course>();
        mCourses.add(new Course(54231, "COMP", 1126, "Introduction to Computing I", "The introduction to Computing Course is...", "Undergraduate", "Lecture"));
        mCourses.add(new Course(123156, "COMP", 1127, "Introduction to Computing II", "The introduction to Computing Course is...", "Undergraduate", "Lab"));
        mCourses.add(new Course(678995, "COMP", 3161, "Database Management Systems", "The introduction to Computing Course is...", "Undergraduate", "Lecture"));
        mCourses.add(new Course(78654, "INFO", 2410, "Mathematics and Statistics for IT", "The introduction to Computing Course is...", "Undergraduate", "Lecture"));
        mCourses.add(new Course(792165,"COMP",2190,"Net-Centric Computing","This is a core course in the BSc Computer Science curriculum.It is the updated version of CS20A. Its primary focus is on the method of assessing time complexity of an algorithm, and on several algorithms that efficiently solve common problems across a wide range of domains. Hand in hand with the discussion of these algorithms, goes a discussion of the data structures that support them. Therefore each topic in this course is usually present as a family if problems, the types of algorithmc solutions available, the...\"\n" +
                "        ","Undergraduate","Tutorial"));
        mCourses.add(new Course(22376, "COMP", 3160, "Artificial Intelligence", "The introductoion to Computing Course is...", "Undergraduate", "Lecture"));
    }

    public static CourseList get (Context c){
        if (sCourseList == null){
            sCourseList = new CourseList(c.getApplicationContext());
        }
        return sCourseList;
    }
    public ArrayList<Course> getmCourses(){
        return mCourses;
    }
    public Course getCourse(int Crn){
        for (Course c : mCourses){
            if (c.getCRN()== Crn)
                return c;
        }
        return null;
    }
}