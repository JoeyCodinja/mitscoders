package mits.uwi.com.ourmobileenvironment.ourvle.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.models.Message;
import mits.uwi.com.ourmobileenvironment.ourvle.fragments.MessageListFragment;

public class MessageListActivity extends AppCompatActivity implements MessageListFragment.OnMessageSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        MessageListFragment fragment = MessageListFragment.newInstance();
        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).commit();
        }
    }

    @Override
    public void onMessageSelected(Message message) {

    }
}
