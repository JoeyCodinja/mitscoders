package mits.uwi.com.ourmobileenvironment.ourvle.classes.models;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 7/6/15.
 */
public class SiteInfo extends SugarRecord{

    String sitename= "";
    String username= "";
    String firstname= "";
    String lastname= "";
    String fullname= "";
    int userid;
    String siteurl= "";
    String userpictureurl= "";
    @Ignore
    ArrayList<MoodleFunction> functions = new ArrayList<>();
    int downloadfiles =0;
    int uploadfiles =0;
    String release= "";
    String version= "";

    // Errors. Not to be stored in sql db.
    @Ignore
    String exception= "";
    @Ignore
    String errorcode= "";
    @Ignore
    String message = "";
    @Ignore
    String debuginfo= "";

    /*
     * SiteInfo is basically an account Token is needed for an account to get
     * new info from Moodle site
     */
    String token = "";

    public SiteInfo() {
    }

    public SiteInfo(String token) {
        this.token = token;
    }

    /**
     * Set token associated with this account
     */
    public void setToken(String token) {
        this.token = token;
    }

    public String getSitename() {
        return sitename;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public int getUserid() {
        return userid;
    }

    public String getSiteurl() {
        return siteurl;
    }

    /**
     * User profile picture. <br/>
     * Warning: This url is the public URL that only works when forcelogin is
     * set to NO and guestaccess is set to YES. In order to retrieve user
     * profile pictures independently of the Moodle config, replace
     * "pluginfile.php" by "webservice/pluginfile.php?token=WSTOKEN&file=" Of
     * course the user can only see profile picture depending on his/her
     * permissions. Moreover it is recommended to use HTTPS too.
     */
    public String getUserpictureurl() {
        return userpictureurl;
    }

    /**
     * functions that are available
     *
     * @return
     */
    public ArrayList<MoodleFunction> getFunctions() {
        return functions;
    }

    /**
     * 1 if users are allowed to download files, 0 if not (Optional)
     */
    public int getDownloadfiles() {
        return downloadfiles;
    }

    /**
     * 1 if users are allowed to upload files, 0 if not (Optional)
     */
    public int getUploadfiles() {
        return uploadfiles;
    }

    /**
     * Moodle release number (Optional)
     */
    public String getRelease() {
        return release;
    }

    /**
     * Moodle version number (Optional)
     */
    public String getVersion() {
        return version;
    }


    /**
     * Get token associated with this account
     */
    public String getToken() {
        return token;
    }

    /**
     * Exception occurred while retrieving
     *
     * @return
     */
    public String getException() {
        return exception;
    }

    /**
     * Errorcode of error occurred while retrieving
     *
     * @return
     */
    public String getErrorcode() {
        return errorcode;
    }

    /**
     * Message of error occurred while retrieving
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Debug info on the error occurred
     *
     * @return
     */
    public String getDebuginfo() {
        return debuginfo;
    }

    /**
     * Set error message <br/>
     * <br/>
     * Particularly useful for network failure errors
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Appends to the existing error messages<br/>
     * <br/>
     * Particularly useful for network failure errors
     */
    public void appenedMessage(String message) {
        this.message += message + "\n";
    }
}
