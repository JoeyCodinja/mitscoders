package mits.uwi.com.ourmobileenvironment.sas;

/**
 * Created by User on 1/18/2016.
 */
public class CourseOverride {
    private int mId;
    private Course mcourse;
    private String Status ;
    private String Note;

    public CourseOverride(int mId, Course mcourse, String status) {
        this.mId = mId;
        this.mcourse = mcourse;
        Status = status;
        Note ="";
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public Course getMcourse() {
        return mcourse;
    }

    public void setMcourse(Course mcourse) {
        this.mcourse = mcourse;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    @java.lang.Override
    public String toString() {
        return mcourse.getSubj()+ " "+mcourse.getCourseCode()+"\n"
                + mcourse.getTitle() +" "+Status + "\nNote to Lecturer\n"+
                Note;
    }
}
