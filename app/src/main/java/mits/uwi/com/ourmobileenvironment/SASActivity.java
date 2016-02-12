package mits.uwi.com.ourmobileenvironment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import mits.uwi.com.ourmobileenvironment.campusinformationfragments.EateriesFragment;
import mits.uwi.com.ourmobileenvironment.sas.classmap.activity.ClassMapActivity;
import mits.uwi.com.ourmobileenvironment.sas.course.AddDropCourseFragment;
import mits.uwi.com.ourmobileenvironment.sas.course.CourseListFragment;
import mits.uwi.com.ourmobileenvironment.sas.holds.HoldsFragment;
import mits.uwi.com.ourmobileenvironment.sas.requestoverride.RequestOverrideListFragment;
import mits.uwi.com.ourmobileenvironment.sas.timetable.Activity.TimeTableActivity;
import mits.uwi.com.ourmobileenvironment.sas.timetable.Fragments.TimeTableFragment;
import mits.uwi.com.ourmobileenvironment.sas.transcriptfragments.TranscriptFragment;


public class SASActivity extends AppCompatActivity {
    private ListView mNavList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ArrayAdapter<TextView> sAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    Toolbar toolbar;
    private FloatingActionButton fab;
    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mRadapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;

    String [] list = {"Home","Add/Drop","Timetable","Class Map","Transcript","Overrides","Holds","Exit"};
    int icons[] = {R.drawable.ic_home_black_24dp,
            R.drawable.ic_swap_vert_black_24dp,
            R.drawable.ic_event_black_24dp,
            R.drawable.ic_location,
            R.drawable.ic_receipt_black_24dp,
            R.drawable.ic_class_black_24dp,
            R.drawable.ic_lock_black_24dp,
            R.drawable.ic_exit_to_app_black_24dp};

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
                    //.addToBackStack(null)
                    .commit();
        }
        ToprightBar.setTopOverflow(this);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.sas_drawer_layout);
        mRecyclerView =(RecyclerView)findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRadapter = new SasAdapter(list,icons,"SAS");
        mRecyclerView.setAdapter(mRadapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mNavList = (ListView)findViewById(R.id.navList);
        //addDrawerItems();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Fab Pressed", Toast.LENGTH_SHORT).show();
            }
        });


        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,
                toolbar,
                android.R.string.ok,
                android.R.string.cancel){
            @Override
            public void onDrawerOpened(View drawerView) {
                fab.setVisibility(View.GONE);
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                fab.setVisibility(View.VISIBLE);
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        //mNavList.setOnItemClickListener(new DrawerItemClickListener());
        
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
                case 4:
                    fragment = new TranscriptFragment();
                    break;
                case 5:
                    fragment = new RequestOverrideListFragment();
                    break;
                case 6:
                    fragment = new HoldsFragment();
                    break;

            }
            if (position !=7) {
                if (position==0) {
                    fm.beginTransaction()
                            .replace(R.id.sas_fragmentContainer, fragment)
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .commit();
                }
                else {
                    if (position == 2) {
                        Intent i = new Intent(getApplicationContext(), TimeTableActivity.class);
                        startActivity(i);
                    }
                    else
                    if (position == 3) {
                        Intent i = new Intent(getApplicationContext(), ClassMapActivity.class);
                        startActivity(i);

                    } else {
                        fm.beginTransaction()
                                .replace(R.id.sas_fragmentContainer, fragment)
                                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                                .addToBackStack(null)
                                .commit();
                    }

                }
                mDrawerLayout.closeDrawer(mNavList);
            }
            else{

                finish();
            }
        }
    }
    private void addDrawerItems(){
        /*String [] list = {"Home","Add/Drop","Timetable","Class Map","Transcript","Overrides","Holds","Exit"};
        int icons[] = {R.drawable.ic_home_black_24dp,
        R.drawable.ic_swap_vert_black_24dp,
        R.drawable.ic_event_black_24dp,
        R.drawable.ic_location,
        R.drawable.ic_receipt_black_24dp,
        R.drawable.ic_class_black_24dp,
        R.drawable.ic_lock_black_24dp,
        R.drawable.ic_exit_to_app_black_24dp};*/
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
      /*  if (id==R.id.action_courseinfo){
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = new CourseInfoFragment();

            fm.beginTransaction()
                    .replace(R.id.sas_fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        if (id==R.id.classmap){
            Intent i = new Intent(this,ClassMapActivity.class );
                     //ClassMapActivity.class
           // i.putExtra(CourseInfoFragment.EXTRA_COURSE_ID, c.getCRN());
            startActivity(i);

        }*/

        return super.onOptionsItemSelected(item);
    }
    public class SasAdapter extends RecyclerView.Adapter<SasAdapter.ViewHolder>{

        private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
        // IF the view under inflation and population is header or Item
        private static final int TYPE_ITEM = 1;

        private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
        private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

        private String name;        //String Resource for header View Name
        View mView;

        // Creating a ViewHolder which extends the RecyclerView View Holder
        // ViewHolder are used to to store the inflated views in order to recycle them

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.sas_fragmentContainer);

        public class ViewHolder extends RecyclerView.ViewHolder {
            int Holderid;
            TextView textView;
            ImageView imageView;


            public ViewHolder(View itemView, int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
                super(itemView);
                mView = itemView;
                /*itemView.setClickable(true);
                itemView.setOnClickListener(this);*/
                // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

                if (ViewType == TYPE_ITEM) {
                    textView = (TextView) itemView.findViewById(R.id.nav_item); // Creating TextView object with the id of textView from item_row.xml
                    imageView = (ImageView) itemView.findViewById(R.id.nav_icon);// Creating ImageView object with the id of ImageView from item_row.xml
                    Holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
                } else {
/*                    Name = (TextView) itemView.findViewById(R.id.name);         // Creating Text View object from header.xml for name
                    email = (TextView) itemView.findViewById(R.id.email);       // Creating Text View object from header.xml for email
                    profile = (ImageView) itemView.findViewById(R.id.circleView);// Creating Image view object from header.xml for profile pic
                    Holderid = 0;                                                // Setting holder id = 0 as the object being populated are of type header view
                */}
            }

           /* @Override
            public void onClick(View view) {
                Toast.makeText(context, "The Item Clicked is: " + getPosition(), Toast.LENGTH_SHORT).show();
            }*/
        }


        SasAdapter(String Titles[],int Icons[],String Name){ // MyAdapter Constructor with titles and icons parameter
            // titles, icons, name, email, profile pic are passed from the main activity as we
            mNavTitles = Titles;                //have seen earlier
            mIcons = Icons;
            name = Name;
           // email = Email;
            //profile = Profile;                     //here we assign those passed values to the values we declared here
            //in adapter
        }

        //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
        //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
        // if the viewType is TYPE_HEADER
        // and pass it to the view holder

        @Override
        public SasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == TYPE_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_sas_sidebar,parent,false); //Inflating the layout
                ViewHolder vhItem = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int itemPosition = mRecyclerView.getChildAdapterPosition(view);
                        int position = itemPosition - 1;
                        switch (position) {
                            case 0:
                                fragment = new CourseListFragment();
                                break;
                            case 1:
                                fragment = new AddDropCourseFragment();
                                break;
                            case 4:
                                fragment = new TranscriptFragment();
                                break;
                            case 5:
                                fragment = new RequestOverrideListFragment();
                                break;
                            case 6:
                                fragment = new HoldsFragment();
                                break;
                        }
                        if (position != 7) {
                            if (position == 0) {
                                fm.beginTransaction()
                                        .replace(R.id.sas_fragmentContainer, fragment)
                                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                                        .commit();
                            } else {
                                if (position == 2) {
                                    Intent i = new Intent(getApplicationContext(), TimeTableActivity.class);
                                    startActivity(i);
                                } else if (position == 3) {
                                    Intent i = new Intent(getApplicationContext(), ClassMapActivity.class);
                                    startActivity(i);

                                } else {
                                    fm.beginTransaction()
                                            .replace(R.id.sas_fragmentContainer, fragment)
                                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                                            .addToBackStack(null)
                                            .commit();
                                }
                            }
                            mDrawerLayout.closeDrawers();//(mRecyclerView);
                        } else {

                            finish();
                           // Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_LONG).show();
                        }
                    }
                });

                return vhItem; // Returning the created object
                //inflate your layout and pass it to view holder

            } else if (viewType == TYPE_HEADER) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_sas_sideheader,parent,false); //Inflating the layout
                ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view
                return vhHeader; //returning the object created
            }
            return null;

        }

        //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
        // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
        // which view type is being created 1 for item row
        @Override
        public void onBindViewHolder(SasAdapter.ViewHolder holder, final int position) {
            if(holder.Holderid ==1) {                              // as the list view is going to be called after the header view so we decrement the
                // position by 1 and pass it to the holder while setting the text and image
                holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
                holder.imageView.setImageResource(mIcons[position - 1]);// Settimg the image with array of our icons

            }
            else{
/*
                holder.profile.setImageResource(profile);           // Similarly we set the resources for header view
                holder.Name.setText(name);
                holder.email.setText(email);*/
            }
        }

        // This method returns the number of items present in the list
        @Override
        public int getItemCount() {
            return mNavTitles.length+1; // the number of items in the list will be +1 the titles including the header view.
        }


        // Witht the following method we check what type of view is being passed
        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;

            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

    }

}
