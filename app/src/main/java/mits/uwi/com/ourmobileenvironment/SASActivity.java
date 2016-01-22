package mits.uwi.com.ourmobileenvironment;


import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import mits.uwi.com.ourmobileenvironment.sasfragments.coursefragments.AddDropCourseFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.coursefragments.CourseInfoFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.coursefragments.CourseListFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.holdfragments.HoldsFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.requestoverridefragments.RequestOverrideListFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.timetablefragments.TimeTableFragment;
import mits.uwi.com.ourmobileenvironment.sasfragments.transcriptfragments.TranscriptFragment;


public class SASActivity extends AppCompatActivity {
    private ListView mNavList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ArrayAdapter<TextView> sAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sas);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.sas_fragmentContainer);
        if (fragment == null){
            fragment = new CourseListFragment();//CourseFragment();
            fm.beginTransaction()
                    .add(R.id.sas_fragmentContainer, fragment)
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .addToBackStack(null)
                    .commit();
        }
        ToprightBar.setTopOverflow(this);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.sas_drawer_layout);
        mNavList = (ListView)findViewById(R.id.navList);
        addDrawerItems();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,
                toolbar,
                android.R.string.ok,
                android.R.string.cancel){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavList.setOnItemClickListener(new DrawerItemClickListener());
        
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.sas_fragmentContainer);

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(SASActivity.this,((TextView)view).getText(),Toast.LENGTH_LONG).show();
            switch(position) {
                case 0:
                    fragment = new CourseListFragment();//CourseFragment();
                    break;
                case 1:
                    fragment = new AddDropCourseFragment();
                    break;
                case 2:
                    fragment = new TimeTableFragment();
                    break;
                case 3:

                    fragment = new TranscriptFragment();
                    break;
                case 4:

                    fragment = new RequestOverrideListFragment();
                    break;
                case 5:
                    fragment = new HoldsFragment();
                    break;

            }
            if (position !=6) {
                fm.beginTransaction()
                        .replace(R.id.sas_fragmentContainer, fragment)
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .addToBackStack(null)
                        .commit();
                mDrawerLayout.closeDrawer(mNavList);
            }
            else{

                finish();
            }

        }

    }

    private void addDrawerItems(){
        /*TextView adddrop,timetable,transcript,override,holds;
        adddrop = new TextView(this);
        timetable = new TextView(this);
        transcript =  new TextView(this);
        override =  new TextView(this);
        holds = new TextView(this);

        adddrop.setText("Add/Drop");
        timetable.setText("Timetable");
        transcript.setText("transcript");
        override.setText("Overrides");
        holds.setText("Holds");

        TextView[] list2 = {adddrop,timetable,transcript,override,holds};


        sAdapter = new ArrayAdapter<TextView>(this,android.R.layout.simple_list_item_1,list2);*/
        String [] list = {"Home","Add/Drop","Timetable","Transcript","Overrides","Holds","Exit"};
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
        if (id==R.id.action_courseinfo){
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = new CourseInfoFragment();

            fm.beginTransaction()
                    .replace(R.id.sas_fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        }



        return super.onOptionsItemSelected(item);
    }


}
