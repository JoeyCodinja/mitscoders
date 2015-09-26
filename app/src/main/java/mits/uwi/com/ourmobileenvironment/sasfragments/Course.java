package mits.uwi.com.ourmobileenvironment.sasfragments;


import java.util.ArrayList;

/**
 * Created by User on 9/17/2015.
 */
public class Course {

    private int CRN,CourseCode,Capacity,Section_Actual, Section_Remaining;
    private String Title,Subj,Section,Time, Instructor, Date, Location;
    private char Campus, Days;
    private double Credits;
    private String Attribute;
    private static ArrayList<Course> mCourses =new ArrayList<>();
    public Course ( int crn, String subj,int courseCode,  String title)
    {
        this.CRN = crn;
        this.Subj = subj;
        this.CourseCode = courseCode;
        this.Title = title;

    }

    public int getCRN() {
        return CRN;
    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    public int getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(int courseCode) {
        CourseCode = courseCode;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public int getSection_Actual() {
        return Section_Actual;
    }

    public void setSection_Actual(int section_Actual) {
        Section_Actual = section_Actual;
    }

    public int getSection_Remaining() {
        return Section_Remaining;
    }

    public void setSection_Remaining(int section_Remaining) {
        Section_Remaining = section_Remaining;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubj() {
        return Subj;
    }

    public void setSubj(String subj) {
        Subj = subj;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public char getCampus() {
        return Campus;
    }

    public void setCampus(char campus) {
        Campus = campus;
    }

    public char getDays() {
        return Days;
    }

    public void setDays(char days) {
        Days = days;
    }

    public double getCredits() {
        return Credits;
    }

    public void setCredits(double credits) {
        Credits = credits;
    }

    public String getAttribute() {
        return Attribute;
    }

    public void setAttribute(String attribute) {
        Attribute = attribute;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CRN=" + CRN +
                ", CourseCode=" + CourseCode +
                ", Capacity=" + Capacity +
                ", Section_Actual=" + Section_Actual +
                ", Section_Remaining=" + Section_Remaining +
                ", Title='" + Title + '\'' +
                ", Subj='" + Subj + '\'' +
                ", Section='" + Section + '\'' +
                ", Time='" + Time + '\'' +
                ", Instructor='" + Instructor + '\'' +
                ", Date='" + Date + '\'' +
                ", Location='" + Location + '\'' +
                ", Campus=" + Campus +
                ", Days=" + Days +
                ", Credits=" + Credits +
                ", Attribute='" + Attribute + '\'' +
                '}';
    }

    public static ArrayList<Course> getmCourses() {
        populate();
        return mCourses;
    }

    public static void populate()
    {
        mCourses.add(new Course(54231,"Computing",1126,"Student Services"));
        mCourses.add(new Course(123156,"Computing",1127,"Student Services"));
        mCourses.add(new Course(678995,"Computing",3161,"Student Services"));
        mCourses.add(new Course(78654,"Computing",2410,"Student Services"));
        mCourses.add(new Course(792165,"Computing",2190,"Student Services"));
        mCourses.add(new Course(22376,"Computing",3160,"Student Services"));
    }
}
