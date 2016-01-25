package mits.uwi.com.ourmobileenvironment.ourvle.classes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.models.ForumDiscussion;


/**
 * @author Javon Davis
 *         Created by Javon Davis on 7/22/15.
 */
public class DiscussionsListAdapter extends RecyclerView.Adapter<DiscussionsListAdapter.ViewHolder> {

    private List<ForumDiscussion> mDiscussions;

    public DiscussionsListAdapter(List<ForumDiscussion> discussions)
    {
        mDiscussions = discussions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_discussion, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ForumDiscussion discussion = mDiscussions.get(position);
        String title = discussion.getName().trim();
        String author = discussion.getFirstuserfullname().trim();

        holder.author.setText(author);
        holder.discussionTitle.setText(title);

    }

    @Override
    public int getItemCount() {
        return mDiscussions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView discussionTitle;
        TextView author;
        public ViewHolder(View v) {
            super(v);
            discussionTitle = (TextView) v.findViewById(R.id.discussion_title);
            author = (TextView) v.findViewById(R.id.author);
        }

    }
}
