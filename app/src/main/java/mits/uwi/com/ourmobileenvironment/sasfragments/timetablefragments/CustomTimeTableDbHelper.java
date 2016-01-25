package mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import mits.uwi.com.ourmobileenvironment.sasfragments.TimeTableEvent;

/**
 * Created by User on 1/15/2016.
 */
public class CustomTimeTableDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "customtimetable.sqlite";
    private static final int VERSION = 1;
    private static final String TABLE_TIMETABLE = "timetable";
    private static final String COLUMN_EVENT_ID = "_id";
    private static final String COLUMN_START_TIME = "start_time";
    private static final String COLUMN_END_TIME = "end_time";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DAY = "day";

    public CustomTimeTableDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the "run" table
        db.execSQL("create table timetable (" +
                "_id integer primary key autoincrement, start_time integer, end_time integer, day integer, " +
                "description varchar(200),location varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertEvent(TimeTableEvent event) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_START_TIME, event.getStartTime().getTime());
        cv.put(COLUMN_END_TIME, event.getEndTime().getTime());
        cv.put(COLUMN_DAY, event.getDay());
        cv.put(COLUMN_DESCRIPTION, event.getDescription());
        cv.put(COLUMN_LOCATION, event.getLocation());
        return getWritableDatabase().insert(TABLE_TIMETABLE, null, cv);
    }

    public EventCursor queryTimetable() {
        Cursor wrapped = getReadableDatabase().query(TABLE_TIMETABLE, null, null, null, null, null, null);
        return new EventCursor(wrapped);
    }

    public static class EventCursor extends CursorWrapper {
        public EventCursor(Cursor c) {
            super(c);
        }

        public TimeTableEvent getTimeTableEvent() {
            if (isBeforeFirst() || isAfterLast())
                return null;
            TimeTableEvent event = new TimeTableEvent();
            long EventId = getLong(getColumnIndex(COLUMN_EVENT_ID));
            event.setmID(EventId);
            long startTime = getLong(getColumnIndex(COLUMN_START_TIME));
            event.setStartTime(new Date(startTime));
            long endTime = getLong(getColumnIndex(COLUMN_END_TIME));
            event.setStartTime(new Date(endTime));
            int day = getInt(getColumnIndex(COLUMN_DAY));
            event.setDay(day);
            String descr = getString(getColumnIndex(COLUMN_DESCRIPTION));
            event.setDescription(descr);
            String location = getString(getColumnIndex(COLUMN_LOCATION));
            event.setLocation(location);
            return event;


        }
    }
}
