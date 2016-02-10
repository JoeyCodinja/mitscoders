package mits.uwi.com.ourmobileenvironment.sas.sas_database;

import com.orm.SugarRecord;

import java.util.Date;

/**
 *
 */

public class TimeTable extends SugarRecord{
    private Date startTime, endTime,SemesterBegin,SemesterEnd;
    private String description, location;
    private int day;

    public TimeTable(){

    }

    public TimeTable(Date startTime, Date endTime, Date semesterBegin, Date semesterEnd, String description, String location, int day) {
        this.startTime = startTime;
        this.endTime = endTime;
        SemesterBegin = semesterBegin;
        SemesterEnd = semesterEnd;
        this.description = description;
        this.location = location;
        this.day = day;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getSemesterBegin() {
        return SemesterBegin;
    }

    public void setSemesterBegin(Date semesterBegin) {
        SemesterBegin = semesterBegin;
    }

    public Date getSemesterEnd() {
        return SemesterEnd;
    }

    public void setSemesterEnd(Date semesterEnd) {
        SemesterEnd = semesterEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", SemesterBegin=" + SemesterBegin +
                ", SemesterEnd=" + SemesterEnd +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", day=" + day +
                '}';
    }
}
