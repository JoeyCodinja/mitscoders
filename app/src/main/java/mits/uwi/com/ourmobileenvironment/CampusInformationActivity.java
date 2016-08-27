
package mits.uwi.com.ourmobileenvironment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pkmmte.view.CircularImageView;

import mits.uwi.com.ourmobileenvironment.adapters.CampusInfoViewFlipperAdapter;
import mits.uwi.com.ourmobileenvironment.adapters.CampusInfoViewPagerAdapter;

import java.util.jar.Attributes;


public class CampusInformationActivity extends AppCompatActivity implements AppCompatCallback{

    public final static String TO_WHERE_INT =
            "com.ourmobileenvironment.campusinformationfragments.TO_WHERE";

    private Drawable indicatorSelected, indicatorUnselected;
    private Attributes indicators;

    private Handler viewPagerSwipeHandler;

    private String[] group_headings;
    private String[] group_bodies;

    private  ViewPager mCampusInfo_ViewPager;
    private AdapterViewFlipper mCampusInfoFlipper;

    private CampusInfoViewPagerAdapter adapter;
    private CampusInfoViewFlipperAdapter newAdapter;

    private int[] campusInfoSub = {R.id.to_campus_facilities,
                                   R.id.to_campus_housing,
                                   R.id.to_faculties,
                                   R.id.to_history,
                                   R.id.to_campus_life};

    @Override
    @TargetApi(21)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_campus_info2);
        getSupportActionBar().setTitle(R.string.title_activity_campus_information);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        group_headings = getResources().getStringArray(R.array.campus_info_snippet_titles);
        group_bodies = getResources().getStringArray(R.array.campus_info_snippet_body);

        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21){
            indicatorSelected = getResources().getDrawable(R.drawable.indicators_selected);
            indicatorUnselected = getResources().getDrawable(R.drawable.indicators);
        } else {
            indicatorSelected = getResources().getDrawable(R.drawable.indicators_selected, null);
            indicatorSelected = getResources().getDrawable(R.drawable.indicators, null);
        }

//        newAdapter = new CampusInfoViewFlipperAdapter(group_headings,
//                                                   group_bodies,
//                                                   this.getApplicationContext());

        adapter = new CampusInfoViewPagerAdapter(getSupportFragmentManager(),
                                                 group_headings,
                                                 group_bodies,
                                                 group_headings.length);

//        mCampusInfoFlipper = (AdapterViewFlipper)findViewById(R.id.campus_info_flipper);
//        mCampusInfoFlipper.setAdapter(newAdapter);

        mCampusInfo_ViewPager = (ViewPager)findViewById(R.id.campus_info_viewpager);
        mCampusInfo_ViewPager.setAdapter(adapter);


//        mCampusInfoFlipper = (AdapterViewFlipper) findViewById(R.id.campus_info_flipper);
//        mCampusInfoFlipper.setAdapter(newAdapter);
//        mCampusInfoFlipper.setAutoStart(true);
//        mCampusInfoFlipper.setOnTouchListener(new View.OnTouchListener(){
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                float initX = event.getX();
//                if (event.getAction() == MotionEvent.ACTION_MOVE){
//                    if (event.getHistorySize() > 0){
//                        int historySize = event.getHistorySize();
//                        int pointerCount = event.getPointerCount();
//                        for (int i=0; i< historySize; i++){
//                            float nextX = event.getHistoricalX(i);
//                            if (nextX > initX){
//                                // Swipe Right
//                                mCampusInfoFlipper.showNext();
//                                return true;
//                            }
//                            else if (nextX < initX){
//                                //Swipe Left
//                                mCampusInfoFlipper.showPrevious();
//                                return true;
//                            }
//                        }
//                    }
//                }
//
//                return false;
//            }
//        });

        indicators  = new Attributes(adapter.getCount());

        mCampusInfo_ViewPager.addOnPageChangeListener(new CampusInfoViewPagerPageChangeListener());

        FrameLayout viewPagerParent = (FrameLayout)mCampusInfo_ViewPager.getParent();
        viewPagerParent.addView(createViewPagerIndicator(group_headings.length));

        // Allows the View Pager to swipe automatically
        viewPagerSwipeHandler = new Handler();
        Runnable viewPagerSwipeRunnable = new Runnable() {
            @Override
            public void run() {
                if (mCampusInfo_ViewPager.getChildCount() == 0){
                    viewPagerSwipeHandler.removeCallbacks(this);
                    return;
                }
                mCampusInfo_ViewPager
                        .setCurrentItem(
                                (mCampusInfo_ViewPager.getCurrentItem() + 1) %
                                        mCampusInfo_ViewPager.getChildCount());
                viewPagerSwipeHandler.postDelayed(this, 10000);

            }
        };

        viewPagerSwipeHandler.postDelayed(viewPagerSwipeRunnable, 10000);

        // Sets up each button report to the SubInformation Activity when it is touched
        for ( int button_id: campusInfoSub){
            final Button buttonInQuestion = (Button) findViewById(button_id);
            buttonInQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent toSubCampusInfo =
                            new Intent(v.getContext(),
                                    CampusInformationSubActivity.class);
                    toSubCampusInfo.putExtra(CampusInformationActivity.TO_WHERE_INT,
                            buttonInQuestion.getId());
                    startActivity(toSubCampusInfo);
                }
            });
        }

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mCampusInfo_ViewPager.clearOnPageChangeListeners();
        viewPagerSwipeHandler.removeCallbacks(null);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eateries, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode){

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode){

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback){
        return null;
    }

    public void indicate(int index){
        ViewParent viewParent;

        viewParent = mCampusInfo_ViewPager.getParent();

        indicate(index, viewParent);
    }

    @TargetApi(21)
    public void indicate(int index, ViewParent viewParent){
        FrameLayout frame = (FrameLayout)viewParent;
        LinearLayout indicatorLayout = (LinearLayout)frame.getChildAt(1);
        View view =  indicatorLayout.getChildAt(index);
        view.setBackground(indicatorSelected);
        indicators.putValue(String.valueOf(view.getId()),
                String.valueOf(true));

        for(int other_views=0;
            other_views < frame.getChildCount()-1;
            other_views ++){
            if (other_views != index){
                view = indicatorLayout.getChildAt(other_views);
                if (Boolean.valueOf(indicators.getValue(String.valueOf(view.getId())))){
                    view.setBackground(indicatorUnselected);
                    indicators.putValue(String.valueOf(view.getId()),
                            String.valueOf(false));
                    break;
                }
            }
        }
    }

    @TargetApi(21)
    public View createViewPagerIndicator(int magnitude){
        LinearLayout indicatorGroup = new LinearLayout(this);
        ImageView[] indicatorSet = new ImageView[magnitude];

        indicatorGroup.setOrientation(LinearLayout.HORIZONTAL);
        FrameLayout.LayoutParams indicatorGroupLayoutParams =
                new FrameLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        indicatorGroup.setLayoutParams(indicatorGroupLayoutParams);
        indicatorGroupLayoutParams.gravity = Gravity.CENTER_VERTICAL | Gravity.BOTTOM;

        for (int view=0; view < magnitude; view++){
            LinearLayout.LayoutParams indicatorLayoutParams =
                    new LinearLayout.LayoutParams(
                            (int) getResources().getDimension(R.dimen.indicator_height),
                            (int) getResources().getDimension(R.dimen.indicator_width));
            indicatorLayoutParams.gravity= Gravity.CENTER;

            indicatorSet[view] = new CircularImageView(indicatorGroup.getContext());
            Drawable indicatorImage;

            int indicatorShapeID;

            if (view == 0){
                indicatorShapeID = R.drawable.indicators_selected;
                indicators.putValue(String.valueOf(indicatorSet[view].getId()),
                        String.valueOf(true));
            }
            else {
                indicatorShapeID = R.drawable.indicators;
                indicators.putValue(String.valueOf(indicatorSet[view].getId()),
                        String.valueOf(false));
            }

            if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21)
                // Deprecated;
                indicatorImage = getResources().getDrawable(indicatorShapeID);
            else
                indicatorImage = getResources().getDrawable(indicatorShapeID, null);

            indicatorSet[view].setBackground(indicatorImage);
            indicatorSet[view].setLayoutParams(indicatorLayoutParams);
            indicatorSet[view].setPadding(2, 0, 2, 0);
            indicatorGroup.addView(indicatorSet[view]);
        };

        return indicatorGroup;
    }

    public class CampusInfoViewPagerPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrollStateChanged(int state){
        }

        @Override
        public void onPageScrolled(int position,
                                   float positionOffset,
                                   int positionOffsetPixels){
        }

        @Override
        public void onPageSelected(int position){
            indicate(position);
        }

    }


}

