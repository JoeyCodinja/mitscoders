package mits.uwi.com.ourmobileenvironment.sasfragments;

import java.util.Date;

/**
 * Created by User on 1/15/2016.
 */
public class TimeTableEvent {
    private long mID;
    private Date startTime, endTime;
    private String description, location;
    private int day;

    public TimeTableEvent( ) {
    }

    public long getmID() {
        return mID;
    }

    public void setmID(long mID) {
        this.mID = mID;
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
        return "TimeTableEventFragment{" +
                "mID=" + mID +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
