package mits.uwi.com.ourmobileenvironment.homepagefragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;



public class HomeVideosFragment extends Fragment {

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private static final String youtube_APIKEY = "AIzaSyACbaYTwu7uTaXXj9LGvRHW3iEFRr9RKHc";

    private static final List<String> playlists = new ArrayList<String>();


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
        YouTubeInitializationResult errorReason =
                YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getActivity());
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(getActivity(), RECOVERY_DIALOG_REQUEST).show();
        } else if (errorReason != YouTubeInitializationResult.SUCCESS) {
            String errorMessage =
                    String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == RECOVERY_DIALOG_REQUEST){
            //Recreate the activity if user performed a recovery action
            getActivity().recreate();
        }
    }

    private void populatePlaylistList(){

        // Instantiate the Request Queue

    }

    private void requestPlaylist(String url, String nextPageToekn){

        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        String ytGetPlaylistEndPoint = "https://www.googleapis.com/youtube/v3/playlists";

        JsonObjectRequest playlistRequest = new JsonObjectRequest(Request.Method.GET,
                ytGetPlaylistEndPoint,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HomeVideosFragment", "Yer Request Done Goofed!!");
            }
        });

        queue.add(playlistRequest);
    }
}
