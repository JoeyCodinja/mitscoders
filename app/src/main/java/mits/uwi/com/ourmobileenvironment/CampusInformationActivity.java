
package mits.uwi.com.ourmobileenvironment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
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


public class CampusInformationActivity extends AppCompatActivity{

    public final static String TO_WHERE_INT =
            "com.ourmobileenvironment.campusinformationfragments.TO_WHERE";

    private Drawable indicatorSelected, indicatorUnselected;

    private Handler viewPagerSwipeHandler;

    private String[] group_headings;
    private String[] group_bodies;

    private ViewPager mCampusInfo_ViewPager;
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

        UWIMonaApplication application = (UWIMonaApplication) getApplication();
        application.screenViewHitAnalytics("Activity~CampusInformation");

        setContentView(R.layout.fragment_campus_info2);
        getSupportActionBar().setTitle(R.string.title_activity_campus_information);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        group_headings = getResources().getStringArray(R.array.campus_info_snippet_titles);
        group_bodies = getResources().getStringArray(R.array.campus_info_snippet_body);

        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21){
            indicatorSelected = getResources().getDrawable(R.drawable.selected_circle);
            indicatorUnselected = getResources().getDrawable(R.drawable.unselected_circle);
        } else {
            indicatorSelected = getResources().getDrawable(R.drawable.selected_circle, null);
            indicatorUnselected = getResources().getDrawable(R.drawable.unselected_circle, null);
        }

        adapter = new CampusInfoViewPagerAdapter(getSupportFragmentManager(),
                                                 group_headings,
                                                 group_bodies,
                                                 group_headings.length);
        mCampusInfo_ViewPager = (ViewPager)findViewById(R.id.campus_info_viewpager);
        mCampusInfo_ViewPager.setAdapter(adapter);
        mCampusInfo_ViewPager.addOnPageChangeListener(new CampusInfoViewPagerPageChangeListener());

        FrameLayout viewPagerParent = (FrameLayout)mCampusInfo_ViewPager.getParent();
        viewPagerParent.addView(createViewPagerIndicator(group_headings.length), 1);

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
                                        adapter.getCount());
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

    public void indicate(int index){
        ViewParent viewParent;

        viewParent = mCampusInfo_ViewPager.getParent();

        indicate(index, viewParent);
    }

    @TargetApi(21)
    public void indicate(int index, ViewParent viewParent){
        FrameLayout frame = (FrameLayout)viewParent;
        LinearLayout indicatorLayout = (LinearLayout)frame.getChildAt(1);
        ImageView view =  (ImageView)indicatorLayout.getChildAt(index);
        view.setImageDrawable(indicatorSelected);
        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        view.setTag(String.valueOf(true));

        for(int other_views=0;
            other_views < indicatorLayout.getChildCount();
            other_views ++){
            if (other_views != index){
                view = (ImageView)indicatorLayout.getChildAt(other_views);
                if (Boolean.valueOf((String)view.getTag())){
                    view.setImageDrawable(indicatorUnselected);
                    view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    view.setTag(String.valueOf(false));
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
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                                       20f,
                                                       getResources().getDisplayMetrics()),
                        Gravity.CENTER|Gravity.BOTTOM);
        indicatorGroup.setLayoutParams(indicatorGroupLayoutParams);

        for (int view=0; view < magnitude; view++){
            LinearLayout.LayoutParams indicatorLayoutParams =
                    new LinearLayout.LayoutParams(
                            (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                                           20f,
                                                           getResources().getDisplayMetrics()),
                            (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                                           20f,
                                                           getResources().getDisplayMetrics()),
                            1.0f);

            indicatorSet[view] = new ImageView(getApplicationContext());
            Drawable indicatorImage;


            if (view == 0){
                indicatorImage = indicatorSelected;
                indicatorSet[view].setTag(String.valueOf(true));
            }
            else {
                indicatorImage = indicatorUnselected;
                indicatorSet[view].setTag(String.valueOf(false));
            }
            System.out.print("indicatorId " + String.valueOf(indicatorSet[view].getId()));

            indicatorSet[view].setImageDrawable(indicatorImage);
            indicatorSet[view].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            indicatorSet[view].setLayoutParams(indicatorLayoutParams);
            indicatorGroup.addView(indicatorSet[view]);
        }
        return indicatorGroup;
    }

    public class CampusInfoViewPagerPageChangeListener
            implements ViewPager.OnPageChangeListener{

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

