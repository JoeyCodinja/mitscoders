package mits.uwi.com.ourmobileenvironment.ourvle.classes.tasks;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.ourvle.classes.models.MoodleCourse;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.models.SiteInfo;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.moodle.MoodleRestCourse;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 7/6/15.
 */
public class CourseTask {

    String token;
    String error;

    /**
     *
     * @param token
     */
    public CourseTask(String token) {
        this.token = token;
    }

    /**
     * Sync all the courses. investigate usefulness of this for OurVLE Admin
     *
     * @return syncStatus
     *
     */
    public Boolean syncAllCourses() {
        MoodleRestCourse moodleRestCourse = new MoodleRestCourse(token);
        ArrayList<MoodleCourse> mCourses = moodleRestCourse.getAllCourses();

        /** Error checking **/
        // Some network or encoding issue.
        if (mCourses.size() == 0) {
            error = "Network issue!";
            return false;
        }

        // Add siteid to all courses and update
        MoodleCourse course;
        List<MoodleCourse> dbCourses;
        for (int i = 0; i < mCourses.size(); i++) {
            course = mCourses.get(i);

            // Update or save in database
            dbCourses = MoodleCourse.find(MoodleCourse.class,
                    "courseid = ?", course.getCourseid() + "");
            if (dbCourses != null && dbCourses.size() > 0) {
                // Set app specific fields explicitly
                course.setId(dbCourses.get(0).getId());
            }
            course.save();
        }

        return true;
    }

    /**
     * Sync all courses of logged in user.
     *
     * @return syncStatus
     */
    public Boolean syncUserCourses() {
        SiteInfo site = SiteInfo.listAll(SiteInfo.class).get(0);

        if (site == null)
            return false;

        int userid = site.getUserid();

        MoodleRestCourse moodleRestCourse = new MoodleRestCourse(token);
        ArrayList<MoodleCourse> mCourses = moodleRestCourse.getEnrolledCourses(userid + "");

        /** Error checking **/
        // Some network or encoding issue.
        if (mCourses == null)
            return false;

        // Moodle exception
        if (mCourses.size() == 1 && mCourses.get(0).getCourseid() == 0)
            return false;

        MoodleCourse course;
        List<MoodleCourse> dbCourses;
        for (int i = 0; i < mCourses.size(); i++) {
            course = mCourses.get(i);

            // Update or save in database
            dbCourses = MoodleCourse.find(MoodleCourse.class,
                    "courseid = ?", course.getCourseid() + "");
            if (dbCourses.size() > 0) {
                course.setId(dbCourses.get(0).getId());
            }
            course.save();
        }

        return true;
    }

    /**
     * Error message from the last failed sync operation.
     *
     * @return error
     */
    public String getError() {
        return error;
    }
}
