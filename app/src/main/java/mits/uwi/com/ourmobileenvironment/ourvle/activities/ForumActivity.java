package mits.uwi.com.ourmobileenvironment.ourvle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.models.CourseForum;
import mits.uwi.com.ourmobileenvironment.ourvle.fragments.ForumFragment;


public class ForumActivity extends AppCompatActivity implements ForumFragment.OnDiscussionSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        Toolbar toolbar = (Toolbar) findViewById(R.id.forum_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        int forumId = getIntent().getExtras().getInt("forumId");

        List<CourseForum> forums = CourseForum.find(CourseForum.class,"forumid = ?",forumId+"");
        CourseForum forum = forums.get(0);
        TextView title = (TextView) findViewById(R.id.toolbar_title);

        String courseName = forum.getCoursename();
        String forumName = forum.getName();

        String forumTitle = String.format(Locale.US,getString(R.string.forum_title),courseName,forumName);
        title.setText(forumTitle);
        title.setTextColor(getResources().getColor(R.color.white));

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, ForumFragment.newInstance(forumId))
                    .commit();
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDiscussionSelected(int discussionId) {

        Intent intent = new Intent(this,PostListActivity.class);

        intent.putExtra("discussionid",discussionId);

        startActivity(intent);

    }
}
