package mits.uwi.com.ourmobileenvironment;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import mits.uwi.com.ourmobileenvironment.sasfragments.CourseFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.CourseInfoFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.holdfragments.HoldsFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments.TimeTableFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.transcriptfragments.TranscriptFragment;


public class SASActivity extends AppCompatActivity {
    private ListView mNavList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sas);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.sas_fragmentContainer);

        if (fragment == null){
            fragment = new CourseFragment();
            fm.beginTransaction()
                    .add(R.id.sas_fragmentContainer, fragment)
                    .commit();
        }
        ToprightBar.setTopOverflow(this);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.sas_drawer_layout);
        mNavList = (ListView)findViewById(R.id.navList);
        addDrawerItems();
        
    }

    private void addDrawerItems(){
        String [] list = {"Add/Drop","Timetable","Transcript","Overrides","Holds"};
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        mNavList.setAdapter(mAdapter);
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sas, menu);
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
        if (id==R.id.action_timetable){
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = new TimeTableFragment();

            fm.beginTransaction()
                    .replace(R.id.sas_fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        }

        if (id==R.id.action_transcript){
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = new TranscriptFragment();

            fm.beginTransaction()
                    .replace(R.id.sas_fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        }

        if (id==R.id.action_courseinfo){
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = new CourseInfoFragment();

            fm.beginTransaction()
                    .replace(R.id.sas_fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        if (id==R.id.hold){
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = new HoldsFragment();

            fm.beginTransaction()
                    .replace(R.id.sas_fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }


}
