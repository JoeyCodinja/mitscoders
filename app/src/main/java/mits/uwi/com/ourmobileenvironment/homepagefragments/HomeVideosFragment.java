package mits.uwi.com.ourmobileenvironment.homepagefragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mits.uwi.com.ourmobileenvironment.R;


public class HomeVideosFragment extends Fragment {

    private static final String youtube_APIKEY = "AIzaSyACbaYTwu7uTaXXj9LGvRHW3iEFRr9RKHc";



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeVideosFragment.
     */
    public static HomeVideosFragment newInstance() {
        HomeVideosFragment fragment = new HomeVideosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeVideosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_videos, container, false);
    }

    private void checkYouTubeApi(){
        YoutubeInitializationResult errorReason =
                YoutubeApiServiceUtil.isYoutubeApiServiceAvailable(this);
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVER_DIALOG_REQUEST).show();
        } else if (errorReason != YoutubeInitializationREsult.SUCCESS) {
            String errorMessage =
                    String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == RECOVERY_DIALOG_REQUEST){
            //Recreate the activity if user performed a recovery action
            recreate();
        }
    }
}
