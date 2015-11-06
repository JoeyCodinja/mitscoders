package mits.uwi.com.ourmobileenvironment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import mits.uwi.com.ourmobileenvironment.adapters.DirectoryAdapter;


public class CampusDirectoryActivity extends AppCompatActivity {

    private RecyclerView mDirectoryRecyclerView;
    private RecyclerView.Adapter mDirectoryAdapter;
    private RecyclerView.LayoutManager mDirectoryLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_directory);

        mDirectoryRecyclerView = (RecyclerView) findViewById(R.id.directory_view);

        mDirectoryRecyclerView.setHasFixedSize(true);

        mDirectoryLayoutManager = new GridLayoutManager(this, 1);

        mDirectoryRecyclerView.setLayoutManager(mDirectoryLayoutManager);

        mDirectoryAdapter = new DirectoryAdapter();
        mDirectoryRecyclerView.setAdapter(mDirectoryAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_campus_directory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.search_directory){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
