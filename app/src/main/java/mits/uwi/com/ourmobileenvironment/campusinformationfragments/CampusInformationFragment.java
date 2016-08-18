package mits.uwi.com.ourmobileenvironment.campusinformationfragments;


import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pkmmte.view.CircularImageView;

import java.util.jar.Attributes;

import mits.uwi.com.ourmobileenvironment.CampusInformationSubActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.adapters.CampusInfoViewPagerAdapter;


/**
 * Created by Danuel on 17/06/2015.
 */
public class CampusInformationFragment extends Fragment {

    public final static String TO_WHERE_INT =
            "com.ourmobileenvironment.campusinformationfragments.TO_WHERE";

    ViewPager  mCampusInfo_ViewPager;
    private Handler viewPagerSwipeHandler;
    CampusInfoViewPagerAdapter adapter;
    int [] campusInfoSub = {R.id.to_campus_facilities,
                            R.id.to_campus_housing,
                            R.id.to_faculties,
                            R.id.to_history,
                            R.id.to_campus_life,};

    String[] groups, children;

    Attributes indicators;

    Drawable indicatorSelected, indicatorUnselected;

    @Override
    @TargetApi(21)
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21){
            indicatorSelected = getResources().getDrawable(R.drawable.indicators_selected);
            indicatorUnselected = getResources().getDrawable(R.drawable.indicators);
        } else{
            indicatorSelected = getResources().getDrawable(R.drawable.indicators_selected, null);
            indicatorUnselected = getResources().getDrawable(R.drawable.indicators, null);
        }

        groups = new String[]{getResources().getString(R.string.campus_info_snippet_title1),
                getResources().getString(R.string.campus_info_snippet_title2),
                getResources().getString(R.string.campus_info_snippet_title3),
                getResources().getString(R.string.campus_info_snippet_title4),
                getResources().getString(R.string.campus_info_snippet_title5),
                getResources().getString(R.string.campus_info_snippet_title6)
        };

        children = new String[]{getResources().getString(R.string.campus_info_snippet_body1),
                getResources().getString(R.string.campus_info_snippet_body2),
                getResources().getString(R.string.campus_info_snippet_body3),
                getResources().getString(R.string.campus_info_snippet_body4),
                getResources().getString(R.string.campus_info_snippet_body5),
                getResources().getString(R.string.campus_info_snippet_body6)};
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_campus_info2, container, false);
        View parent = (View) container.getParent();

        // ViewPager Adapter setup

        adapter = new CampusInfoViewPagerAdapter(
                getActivity().getSupportFragmentManager(),
                groups,
                children,
                groups.length);

        mCampusInfo_ViewPager = (ViewPager)v.findViewById(R.id.campus_info_viewpager);

        mCampusInfo_ViewPager.setAdapter(adapter);

        indicators = new Attributes(adapter.getCount());

        mCampusInfo_ViewPager.addOnPageChangeListener(new CampusInfoViewPagerPageChangeListener());

        FrameLayout viewPagerParent = (FrameLayout)mCampusInfo_ViewPager.getParent();
        viewPagerParent.addView(createViewPagerIndicator(groups.length));

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
            final Button buttonInQuestion = (Button) v.findViewById(button_id);
            buttonInQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent toSubCampusInfo =
                            new Intent(getActivity().getApplicationContext(),
                                       CampusInformationSubActivity.class);
                    toSubCampusInfo.putExtra(CampusInformationFragment.TO_WHERE_INT,
                            buttonInQuestion.getId());
                    startActivity(toSubCampusInfo);
                }
            });
        }

        return v;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mCampusInfo_ViewPager.clearOnPageChangeListeners();
        viewPagerSwipeHandler.removeCallbacks(null);
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
        LinearLayout indicatorGroup = new LinearLayout(this.getActivity());
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
