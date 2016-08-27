package mits.uwi.com.ourmobileenvironment.homepagefragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import com.google.api.client.auth.oauth2.TokenResponse;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.client.auth.oauth2.TokenRequest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.adapters.VideoListRecyclerAdapter;
import mits.uwi.com.ourmobileenvironment.DeveloperKey;
import mits.uwi.com.ourmobileenvironment.R;

/**
 * A simple {@link Fragment} subclass.
 *
 * Use the {@link HomeVideosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeVideosFragment
        extends Fragment {

    VideoListRecyclerAdapter adapter;
    RecyclerView videosListRecyclerView;
    VideoFragment playerFragment;

    public static HomeVideosFragment newInstance() {
        HomeVideosFragment fragment = new HomeVideosFragment();

        return fragment;
    }

    public HomeVideosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(
            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        RecyclerView.LayoutManager videoListLayoutManager;

        // Inflate the layout for this fragment
        View fragment_view = inflater.inflate(R.layout.fragment_home_videos, container, false);

        playerFragment = (VideoFragment)getChildFragmentManager().getFragments().get(0);

        videosListRecyclerView = (RecyclerView) fragment_view.findViewById(R.id.uwitv_videos_list);
        videosListRecyclerView.setHasFixedSize(true);

        videoListLayoutManager = new LinearLayoutManager(this.getActivity());

        videosListRecyclerView.setLayoutManager(videoListLayoutManager);

        new AsyncYouTubeQueryRunner().executeOnExecutor
                (AsyncTask.THREAD_POOL_EXECUTOR,
                getActivity());

        return fragment_view;

    }

    public YouTubeQueryResult[] uwiTVChannelRequest(){

        YouTube youtube;
        YouTubeQueryResult[] results = new YouTubeQueryResult[20];
        final String channelId = "UCN-DinGRVq5fDxa4byT8XwQ";

        HttpTransport httpTransport = new ApacheHttpTransport();

        JsonFactory jsonFactory = new JacksonFactory();

        HttpRequestInitializer httpRequestInitializer = new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) throws IOException {
            }
        };

        youtube = new YouTube.Builder(httpTransport, jsonFactory, httpRequestInitializer).build();
        try{

            SearchListResponse uwiTvChannelVideos = youtube.search()
                    .list("snippet")
                    .setChannelId(channelId)
                    .setOrder("date")
                    .setType("video")
                    .setMaxResults(Long.valueOf("20"))
                    .setKey(DeveloperKey.YOUTUBE_APIKEY)
                    .execute();

            List videos = uwiTvChannelVideos.getItems();
            for (int video=0; video < uwiTvChannelVideos.getItems().size(); video++){
                SearchResult video_item = (SearchResult)videos.get(video);
                SearchResultSnippet video_item_snippet = video_item.getSnippet();

                GenericUrl thumbnailUrl = new GenericUrl(
                        video_item_snippet.getThumbnails()
                                .getHigh()
                                .getUrl());

                HttpResponse thumbnailResponse =
                        httpTransport.createRequestFactory()
                                .buildGetRequest(thumbnailUrl)
                                .execute();

                Bitmap thumbnail = BitmapFactory.decodeStream(thumbnailResponse.getContent());

                results[video] = new YouTubeQueryResult(video_item.getId().getVideoId(),
                        video_item_snippet.getTitle(),
                        video_item_snippet.getDescription(),
                        thumbnail);

            }

        }
        catch (java.io.IOException exception) {
            // Return a single YouTubeQueryResult
            // with error as the
            // title, description, videoId
            for(int index=0; index < results.length; index++){
                if (results[index] == null) {
                    results[index] = new YouTubeQueryResult(
                            "error",
                            "error",
                            "error");
                    break;
                }
            }
        }
        return results;
    }


    public class AsyncYouTubeQueryRunner extends AsyncTask<Context, Void, YouTubeQueryResult[]> {
        @Override
        protected YouTubeQueryResult[] doInBackground(Context... params){
            return uwiTVChannelRequest();

        }

        @Override
        protected void onPostExecute(YouTubeQueryResult[] results){
            // Initializes the adapter with the results we got before
            adapter = new VideoListRecyclerAdapter(results, playerFragment.player);

            videosListRecyclerView.setAdapter(adapter);

            for(int result=0; result < results.length; result++){
                try {
                    if (results[result].title.equals("error") &&
                            results[result].description.equals("error") &&
                            results[result].videoId.equals("error")) {

                    }
                }
                catch (NullPointerException e){
                    Log.d("AsyncYouTubeQueryRunner", e.toString());
                    continue;
                }

            }
        }
    }

    public static final class VideoFragment
            extends YouTubePlayerSupportFragment
            implements YouTubePlayer.OnInitializedListener{

        private YouTubePlayer player;
        private String videoId;

        public static VideoFragment newInstance() { return new VideoFragment(); }

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            // Initializes the new player
            initialize(DeveloperKey.YOUTUBE_APIKEY, this);
            this.setVideoId("23DvON5nk6Y");
        }

        @Override
        public void onDestroy(){
            if (player != null){
                player.release();
            }
            super.onDestroy();
        }

        public void setVideoId(String videoId){
            if (videoId != null && !videoId.equals(this.videoId)){
                this.videoId = videoId;
                if(player!=null){
                    player.cueVideo(videoId);
                }
            }
        }

        public void pause(){
            if (player != null){
                player.pause();
            }
        }

        @Override
        public void onInitializationSuccess(
                YouTubePlayer.Provider provider,
                YouTubePlayer player,
                boolean restored){
            this.player = player;
            if (!restored && videoId != null) {
                player.cueVideo(videoId);
            }
        }

        @Override
        public void onInitializationFailure(
                YouTubePlayer.Provider provider,
                YouTubeInitializationResult result){
            this.player = null;
        }
    }


    public class YouTubeQueryResult {
        public String videoId;
        public String title;
        public String description;
        public Bitmap thumbnail;

        public YouTubeQueryResult (String videoId,
                                   String title,
                                   String description,
                                   Bitmap thumbnail){
            this.videoId = videoId;
            this.title = title;
            this.description = description;
            this.thumbnail = thumbnail;
        }

        public YouTubeQueryResult (String videoId,
                                   String title,
                                   String description){
            // Supply a stock image if
            // there is no thumbnail
            // available
            this.videoId = videoId;
            this.title = title;
            this.description = description;

        }

    }


}



