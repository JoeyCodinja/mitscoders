package mits.uwi.com.ourmobileenvironment.ourvle.classes.moodle;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 7/6/15.
 */
public class Token {

    String token;

    String error = "";
    String stacktrace;
    String debuginfo;
    String reproductionlink;

    /**
     * MoodleToken value
     *
     * Returns null if there was an error.
     *
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * Error value
     *
     * Returns null if no errors found
     *
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     * Stacktrace value
     *
     * Returns null if no errors found
     *
     * @return
     */
    public String getStacktrace() {
        return stacktrace;
    }

    /**
     * Debug info, if enabled by administrator
     *
     * Returns null if not found
     *
     * @return
     */
    public String getDebuginfo() {
        return debuginfo;
    }

    /**
     * Reproduction link
     *
     * Returns null if not found
     *
     * @return
     */
    public String getReproductionlink() {
        return reproductionlink;
    }

    /**
     * Set error message <br/>
     * <br/>
     * Particularly useful for network failure errors
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Appends to the existing error messages<br/>
     * <br/>
     * Particularly useful for network failure errors
     */
    public void appenedError(String error) {
        this.error += error + "\n";
    }
}
