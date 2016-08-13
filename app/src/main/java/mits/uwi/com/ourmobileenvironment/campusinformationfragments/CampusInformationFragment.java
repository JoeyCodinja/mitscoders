package mits.uwi.com.ourmobileenvironment.campusinformationfragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.support.design.widget.TabLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bluejamesbond.text.DocumentView;
import com.pkmmte.view.CircularImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import mits.uwi.com.ourmobileenvironment.CampusInformationSubActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.adapters.CampusInfoViewPagerAdapter;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.UWIInformationFragments.*;


/**
 * Created by Danuel on 17/06/2015.
 */
public class CampusInformationFragment extends Fragment {

    public final static String TO_WHERE_INT =
            "com.ourmobileenvironment.campusinformationfragments.TO_WHERE";

    ExpandableListView mCampusInfo_ExpandableList;
    ViewPager  mCampusInfo_ViewPager;
    private Handler viewPagerSwipeHandler;
    private Runnable viewPagerSwipeRunnable;
    CampusInfoViewPagerAdapter adapter;
    LayoutInflater inflater;
    TabLayout tabs;
    int [] campusInfoSub = {R.id.to_campus_facilities,
                            R.id.to_campus_housing,
                            R.id.to_faculties,
                            R.id.to_history,
                            R.id.to_campus_life,
                            R.id.to_shrugs};

    ArrayList<Object> indicators = new ArrayList<Object>();

    final int active = Color.argb(255,81,81,77);
    final int inactive = Color.argb(255,203,202,200);

    Button toCampusLife, toFaculties, toHistory,
            toCampusHousing, toCampusFacilites, toUnknown;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String[] groups = {getResources().getString(R.string.campus_info_snippet_title1),
                getResources().getString(R.string.campus_info_snippet_title2),
                getResources().getString(R.string.campus_info_snippet_title3),
                getResources().getString(R.string.campus_info_snippet_title4),
                getResources().getString(R.string.campus_info_snippet_title5),
                getResources().getString(R.string.campus_info_snippet_title6)
        };
        String[] children = {getResources().getString(R.string.campus_info_snippet_body1),
                getResources().getString(R.string.campus_info_snippet_body2),
                getResources().getString(R.string.campus_info_snippet_body3),
                getResources().getString(R.string.campus_info_snippet_body4),
                getResources().getString(R.string.campus_info_snippet_body5),
                getResources().getString(R.string.campus_info_snippet_body6)};

        View v = inflater.inflate(R.layout.fragment_campus_info2, container, false);

        View parent = (View) container.getParent();

        DocumentView campusHeading = (DocumentView)parent.
                findViewById(R.id.campus_info_heading_fragment);
//
        FragmentManager fm = this.getActivity().getSupportFragmentManager();

        adapter = new CampusInfoViewPagerAdapter(getActivity().getSupportFragmentManager(),
                groups, children, groups.length);

        mCampusInfo_ViewPager = (ViewPager)v.findViewById(R.id.campus_info_viewpager);

        mCampusInfo_ViewPager.setAdapter(adapter);

        mCampusInfo_ViewPager.addView(createViewPagerIndicator(groups.length));

        // Allows the View Pager to swipe automatically
        viewPagerSwipeHandler = new Handler();
        viewPagerSwipeRunnable = new Runnable() {
            @Override
            public void run() {
                mCampusInfo_ViewPager
                        .setCurrentItem((mCampusInfo_ViewPager.getCurrentItem() + 1) %
                                groups.length );
                indicate(mCampusInfo_ViewPager.getCurrentItem() + 1 ,
                        mCampusInfo_ViewPager.getParent());
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
                            new Intent(getActivity(),
                                       CampusInformationSubActivity.class);
                    toSubCampusInfo.putExtra(CampusInformationFragment.TO_WHERE_INT,
                            buttonInQuestion.getId());
                    startActivity(toSubCampusInfo);
                }
            });
        }

        return v;
    }


    public void indicate(int index, ViewParent viewParent){
        FrameLayout frame = (FrameLayout)viewParent;
        View view = frame.getChildAt(index);
        view.setBackgroundColor(active);

        for(int other_views=0;
            other_views < frame.getChildCount()-1;
            other_views ++){
            if (other_views == index)
                continue;
            else{
                view = frame.getChildAt(other_views);
                if (view.getSolidColor() == active)
                    view.setBackgroundColor(inactive);

            }
        }

    }

    public View createViewPagerIndicator(int magnitude){
        LinearLayout indicatorGroup = new LinearLayout(this.getActivity());
        indicatorGroup.setId(R.id.indicatorGroup);
        indicatorGroup.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams indicatorGroupLayoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        indicatorGroupLayoutParams.gravity =
                Gravity.BOTTOM | Gravity.CENTER_VERTICAL;

        indicatorGroup.setLayoutParams(indicatorGroupLayoutParams);

        for (int view=0; view < magnitude; view++){
            CircularImageView indicator = new CircularImageView(indicatorGroup.getContext());
            if (view == 0){
                indicator.setBackgroundColor(active);
            }
            else indicator.setBackgroundColor(inactive);
            indicator.setMaxHeight(20);
            indicator.setMaxWidth(20);
            indicator.addShadow();
            indicators.add(indicator.getId());
            indicatorGroup.addView(indicator);
        };

        return indicatorGroup;
    }
}
