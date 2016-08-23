package mits.uwi.com.ourmobileenvironment.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.youtube.player.YouTubePlayer;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.homepagefragments.HomeVideosFragment.YouTubeQueryResult;

/**
 * Created by Danuel on 11/8/2016.
 */
public class VideoListRecyclerAdapter extends
        RecyclerView.Adapter<VideoListRecyclerAdapter.ViewHolder> {
    YouTubeQueryResult[] videos;
    YouTubePlayer player;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView videoTitle;
        TextView videoDescription;
        ImageView videoThumbnail;

        public ViewHolder(View v) {
            // Used to provide a reference to the views within the view_holder
            super(v);
            videoTitle = (TextView) v.findViewById(R.id.video_item_title);
            videoDescription = (TextView) v.findViewById(R.id.video_item_description);
            videoThumbnail = (ImageView) v.findViewById(R.id.video_item_thumbnail);
        }

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Adapter Constructor
    // Accepts the data set
    // That is the YouTubeQueryResult0
    public VideoListRecyclerAdapter(YouTubeQueryResult[] result,
                                    YouTubePlayer player) {
        this.videos = result;
        this.player = player;
    }

    @Override
    public VideoListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        //TODO: Build video list item layout
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.video_list_item,
                        parent,
                        false);

        v.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ViewGroup parent = (ViewGroup) v.getParent();
                int viewIndex = parent.indexOfChild(v);
                player.pause();
                player.cueVideo(videos[viewIndex].videoId);

            }
        });

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Replaces information within the
        // ViewHolder with information from
        // our data set
        try {
            holder.videoTitle.setText(this.videos[position].title);
            holder.videoDescription.setText(this.videos[position].description);
            if (this.videos[position].thumbnail == null){
                Bitmap standardThumbnail =
                        BitmapFactory.decodeResource(holder.videoTitle.getResources(),
                                                     R.drawable.uwi_coat_of_arms_48);
                holder.videoThumbnail.setImageBitmap(standardThumbnail);
            }
            holder.videoThumbnail.setImageBitmap(this.videos[position].thumbnail);
        } catch (NullPointerException e){
            // If there is nothing
            // else in the list
            // we shouldn't show the
            // view holder
            View parent = (View) holder.videoTitle.getParent();
            parent.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return videos.length;
    }

    public String getItemVideoId(int position){
        return videos[position].videoId;
    }
}




