package mits.uwi.com.ourmobileenvironment.faculty;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

/**
 * Created by Danuel on 14/10/2016.
 */
public class FacultyDownloader {
    private DownloadManager manager;

    public FacultyDownloader(@NonNull DownloadManager manager){
        this.manager = manager;
    }

    public long queueDownload(DownloadManager.Request request){
        return manager.enqueue(request);
    }

    public DownloadManager.Request createRequest(Uri uri, String filename){
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(getFacultyTitle(filename.substring(0,3)));

        return request;
    }

    private String getFacultyTitle(String faculty){
        String dlTitle = "Faculty Handbook";
        switch(faculty){
            case "SOC":
                dlTitle += " (Social Sciences)";
                break;
            case "MED":
                dlTitle += " (Medical Sciences)";
                break;
            case "FST":
                dlTitle += " (Science and Technology)";
                break;
            case "LAW":
                dlTitle += " (Law)";
                break;
            case "HUM":
                dlTitle += " (Humanities and Education)";
                break;
            default:
                break;
        }
        return dlTitle;
    }
}
