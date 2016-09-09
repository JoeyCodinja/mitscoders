package mits.uwi.com.ourmobileenvironment.homepagefragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.BOSSActivity;
import mits.uwi.com.ourmobileenvironment.EateriesActivity;
import mits.uwi.com.ourmobileenvironment.Transport.TransportActivity;
import mits.uwi.com.ourmobileenvironment.CampusInformationActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.adapters.HomePageViewPagerAdapter;
import mits.uwi.com.ourmobileenvironment.ourvle.activities.OurVLELoginActivity;
import mits.uwi.com.ourmobileenvironment.sas.SAS_Splash;
import mits.uwi.com.ourmobileenvironment.sas.unavailable.Activity.UnavailableActivity;

import static java.lang.Math.pow;


/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {

    private ArrayList<FloatingActionButton> mFABs = new ArrayList<>();
    public static Boolean mMenuExpanded = false;

    int[] fabIDs = {R.id.mainFab,
//            R.id.campus_info_fab,
            R.id.transport_fab,
            R.id.ourvle_fab,
            R.id.eateries_fab,
//            R.id.boss_fab,
            R.id.sas_fab};

    private ViewPager mHome_ViewPager;
    HomePageViewPagerAdapter adapter;
    TabLayout tabs;

    private static final String TAG = "LandingFragment";

    public static HomeActivityFragment newInstance() {
        HomeActivityFragment fragment = new HomeActivityFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_landing, container, false);
        final FrameLayout shade = (FrameLayout) v.findViewById(R.id.shade);
        final RelativeLayout fabHolder = (RelativeLayout) v.findViewById(fabIDs[1]).getParent();

        shade.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /* OnTouch-ing the shade remove the shade and remove
                   the FAB Buttons
                         */
                v.animate().alpha(0).setDuration(300);
                v.setVisibility(View.GONE);
                for (int fab = 1; fab < mFABs.size(); fab++){
                    mFABs.get(fab).animate()
                            .setDuration(2500 - (500 * (fab - 1)))
                            .alpha(0);
                    mFABs.get(fab).setVisibility(View.GONE);
                }
                mMenuExpanded = false;
                return true;
            }
        });

        for (int fab = 0; fab <= fabIDs.length - 1; fab++) {
            mFABs.add((FloatingActionButton) v.findViewById(fabIDs[fab]));

            if (fab > 0) {
                mFABs.get(fab).setAlpha((float) 0);
                mFABs.get(fab).setVisibility(View.GONE);
                mFABs.get(fab).setOnClickListener(new View.OnClickListener() {
                    Intent i;

                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
//                            case R.id.campus_info_fab:
//                                i = new Intent(getActivity(), CampusInformationActivity.class);
//                                startActivity(i);
//                                break;
                            case R.id.transport_fab:
                                i = new Intent(getActivity(), TransportActivity.class);
                                startActivity(i);
                                break;
                            case R.id.ourvle_fab:
                                i = new Intent(getActivity(), OurVLELoginActivity.class);
                                startActivity(i);
                                break;
                            case R.id.eateries_fab:
                                i = new Intent(getActivity(), EateriesActivity.class);
                                startActivity(i);
                                break;
                            case R.id.sas_fab:
                                i = new Intent(getActivity(), UnavailableActivity.class); //SAS_Splash.class);
                                startActivity(i);
                                break;
                        }
                    }
                });

            }
        }

        mFABs.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setClickable(false);
                if (!mMenuExpanded) {
                    shade.setVisibility(View.VISIBLE);
                    shade.animate().alpha(1).setDuration(150);
                    fabHolder.setVisibility(View.VISIBLE);
                    for (int fab = 1; fab <= mFABs.size() - 1; fab++) {
                        if (mFABs.get(fab).getAlpha() == 0){
                            mFABs.get(fab).setVisibility(View.VISIBLE);
                            mFABs.get(fab).animate()
                                    .setDuration(400)
                                    .alpha(1);

                        }
                        else {
                            mFABs.get(fab).setVisibility(View.VISIBLE);
                            mFABs.get(fab).animate()
                                    .setDuration(400)
                                    .alpha(1);
                        }
                    }
                    v.setClickable(true);
                    mMenuExpanded = true;
                } else {
                    shade.animate().alpha(0).setDuration(150);
                    for (int fab = 1; fab <= mFABs.size() - 1; fab++) {
                        mFABs.get(fab).animate()
                                .setDuration(2500 - (500 * (fab - 1)))
                                .alpha(0);
                        mFABs.get(fab).setVisibility(View.GONE);
                    }
                    shade.setVisibility(View.GONE);
                    fabHolder.setVisibility(View.GONE);
                    v.setClickable(true);
                    mMenuExpanded = false;
                }


            }
        });

        adapter = new HomePageViewPagerAdapter(getActivity().getSupportFragmentManager(),
                new Fragment[]{new HomeNewsFragment(), new HomeVideosFragment()}
        );

        mHome_ViewPager = (ViewPager) v.findViewById(R.id.landing_viewpager);
        mHome_ViewPager.setAdapter(adapter);


        tabs = (TabLayout) v.findViewById(R.id.landing_tabs);
        tabs.setupWithViewPager(mHome_ViewPager);
        tabs.getTabAt(0).setIcon(R.drawable.ic_home_white_24dp);
        tabs.getTabAt(1).setIcon(R.drawable.filmstrip_selected);

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tab.setIcon(R.drawable.ic_home_white_24dp);
                    mHome_ViewPager.setCurrentItem(0, true);
                } else if (tab.getPosition() == 1) {
                    tab.setIcon(R.drawable.filmstrip);
                    mHome_ViewPager.setCurrentItem(1, true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tab.setIcon(R.drawable.home_selected);
                } else if (tab.getPosition() == 1) {
                    tab.setIcon(R.drawable.filmstrip_selected);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        return v;
    }
}
