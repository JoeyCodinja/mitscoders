package mits.uwi.com.ourmobileenvironment.ourvle.classes.moodle;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 7/6/15.
 */
public class MoodleFunctions {
    public static final String SERVICE_MOODLE_MOBILE = "moodle_mobile_app";
    final public static String API_HOST = "http://ourvle.mona.uwi.edu/";

    public static final String RESPONSE_FORMAT = "json";

    // Function names for getting contents
    public static final String FUNCTION_GET_ALL_COURSES = "moodle_course_get_courses";// core_course_get_courses
    public static final String FUNCTION_GET_ENROLLED_COURSES = "moodle_enrol_get_users_courses";// core_enrol_get_users_courses
    public static final String FUNCTION_GET_COURSE_CONTENTS = "core_course_get_contents";// no_alternatives
    public static final String FUNCTION_GET_FORUMS = "mod_forum_get_forums_by_courses";
    public static final String FUNCTION_GET_DISCUSSIONS = "mod_forum_get_forum_discussions";
    public static final String FUNCTION_GET_POSTS = "mod_forum_get_forum_discussion_posts";
    public static final String FUNCTION_GET_SITE_INFO = "moodle_webservice_get_siteinfo";// core_webservice_get_site_info()
    public static final String FUNCTION_GET_CONTACTS = "core_message_get_contacts";// no_alternatives
    public static final String FUNCTION_CREATE_CONTACTS = "core_message_create_contacts";// no_alternatives
    public static final String FUNCTION_DELETE_CONTACTS = "core_message_delete_contacts";// no_alternatives
    public static final String FUNCTION_GET_EVENTS = "core_calendar_get_calendar_events";// no_alternatives
    public static final String FUNCTION_SEND_MESSAGE = "moodle_message_send_instantmessages";// core_message_send_instant_messages
    public static final String FUNCTION_GET_MESSAGES = "[mobile_core_message_get_messages";
    public static final String FUNCTION_GET_USERS_FROM_COURSE = "moodle_user_get_users_by_courseid";// core_enrol_get_enrolled_users()
    public static final String FUNCTION_GET_COURSE_ASSIGNMENTS = "mod_assign_get_assignments";

}
