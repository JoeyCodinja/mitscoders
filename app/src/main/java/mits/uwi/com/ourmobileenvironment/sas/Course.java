package mits.uwi.com.ourmobileenvironment.sas;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by User on 9/17/2015.
 */
public class Course implements Parcelable{

    private int CRN,CourseCode,Capacity,Section_Actual, Section_Remaining;
    private String Title,Subj,Section,Time, Instructor, Date, Location,Type;
    private char Campus, Days;
    private double Credits;
    private String Attribute, Description, Level;
    private static ArrayList<Course> mCourses =new ArrayList<>();
    public Course ( int crn, String subj,int courseCode,  String title, String descr, String level,String type)
    {
        this.CRN = crn;
        this.Subj = subj;
        this.CourseCode = courseCode;
        this.Title = title;
        this.Description = descr;
        this.Level = level;
        this.Type = type;
    }

    protected Course(Parcel in) {
        CRN = in.readInt();
        CourseCode = in.readInt();
        Capacity = in.readInt();
        Section_Actual = in.readInt();
        Section_Remaining = in.readInt();
        Title = in.readString();
        Subj = in.readString();
        Section = in.readString();
        Time = in.readString();
        Instructor = in.readString();
        Date = in.readString();
        Location = in.readString();
        Type = in.readString();
        Credits = in.readDouble();
        Attribute = in.readString();
        Description = in.readString();
        Level = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    @Override
    public String toString() {
        return  Title+"\n"+Subj+" "+CourseCode+"\n"+Type/*"Course{" +
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
                ", Attribute='" + Attribute + '\'' +",Type='"+Type+'\''+
                '}'*/;
    }

    public static ArrayList<Course> getmCourses() {
        populate();
        return mCourses;
    }

    public static void populate()
    {
        mCourses.add(new Course(54231,"COMP",1126,"Introduction to Computing I","The introductoion to Computing Course is...","Undergraduate","Lecture"));
        mCourses.add(new Course(123156,"COMP",1127,"Introduction to Computing II","The introductoion to Computing Course is...","Undergraduate","Lab"));
        mCourses.add(new Course(678995,"COMP",3161,"Database Management Systems","The introductoion to Computing Course is...","Undergraduate","Lecture"));
        mCourses.add(new Course(78654,"INFO",2410,"Mathematics and Statistics for IT","The introductoion to Computing Course is...","Undergraduate","Lecture"));
        mCourses.add(new Course(792165,"COMP",2190,"Net-Centric Computing","This is a core course in the BSc Computer Science curriculum.It is the updated version of CS20A. Its primary focus is on the method of assessing time complexity of an algorithm, and on several algorithms that efficiently solve common problems across a wide range of domains. Hand in hand with the discussion of these algorithms, goes a discussion of the data structures that support them. Therefore each topic in this course is usually present as a family if problems, the types of algorithmc solutions available, the...\"\n" +
                "        ","Undergraduate","Tutorial"));
        mCourses.add(new Course(22376,"COMP",3160,"Artificial Intelligence","The introductoion to Computing Course is...","Undergraduate","Lecture"));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(CRN);
        dest.writeInt(CourseCode);
        dest.writeInt(Capacity);
        dest.writeInt(Section_Actual);
        dest.writeInt(Section_Remaining);
        dest.writeString(Title);
        dest.writeString(Subj);
        dest.writeString(Section);
        dest.writeString(Time);
        dest.writeString(Instructor);
        dest.writeString(Date);
        dest.writeString(Location);
        dest.writeString(Type);
        dest.writeDouble(Credits);
        dest.writeString(Attribute);
        dest.writeString(Description);
        dest.writeString(Level);
    }
}
