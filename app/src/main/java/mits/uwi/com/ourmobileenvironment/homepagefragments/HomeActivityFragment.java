package mits.uwi.com.ourmobileenvironment.homepagefragments;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import mits.uwi.com.ourmobileenvironment.BOSSActivity;
import mits.uwi.com.ourmobileenvironment.CampusInformationActivity;
import mits.uwi.com.ourmobileenvironment.OurVLEActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.SASActivity;
import mits.uwi.com.ourmobileenvironment.adapters.HomePageViewPagerAdapter;


/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {

    private ImageView mFloatingActionButton, mFloatingActionButton2, mFloatingActionButton3,
            mFloatingActionButton4, mFloatingActionButton5, mFloatingActionButton6;
    private Animator.AnimatorListener mFABAnimatorListener;
    public static Boolean mMenuExpanded = false;

    private ViewPager mHome_ViewPager;
    private Drawable[] mViewPagerTabIcons;
    private HomePageViewPagerAdapter adapter;
    private TabLayout tabs;

    private static final String TAG = "LandingFragment";

    public Boolean getmMenuExpanded() {
        return mMenuExpanded;
    }

    public static HomeActivityFragment newInstance(){
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

        mFloatingActionButton = (ImageView)v.findViewById(R.id.landing_pageFAB);
        mFloatingActionButton2 = (ImageView)v.findViewById(R.id.landing_pageFAB1);
        mFloatingActionButton2.setVisibility(View.INVISIBLE);
        mFloatingActionButton3 = (ImageView)v.findViewById(R.id.landing_pageFAB2);
        mFloatingActionButton3.setVisibility(View.INVISIBLE);
        mFloatingActionButton4 = (ImageView)v.findViewById(R.id.landing_pageFAB3);
        mFloatingActionButton4.setVisibility(View.INVISIBLE);
        mFloatingActionButton5 = (ImageView)v.findViewById(R.id.landing_pageFAB4);
        mFloatingActionButton5.setVisibility(View.INVISIBLE);
        mFloatingActionButton6 = (ImageView)v.findViewById(R.id.landing_pageFAB5);
        mFloatingActionButton6.setVisibility(View.INVISIBLE);

        mFABAnimatorListener = new Animator.AnimatorListener() {
            ImageView[] buttons = {mFloatingActionButton2,
                    mFloatingActionButton3, mFloatingActionButton4,
                    mFloatingActionButton5, mFloatingActionButton6};

            @Override
            public void onAnimationStart(Animator animation) {
                for (ImageView button: buttons){
                    if (button.getAlpha() == 0f)
                        button.setAlpha(1f);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mMenuExpanded){
                    for (ImageView button : buttons){
                        button.setAlpha(1f);
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, "Animation Cancel");
//                for (ImageView button: buttons){
//                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {}
        };


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mMenuExpanded) {
                    mFloatingActionButton2.setVisibility(View.VISIBLE);
                    mFloatingActionButton2.animate()
                            .translationXBy(-100f)
                            .translationYBy(-150f)
                            .setDuration(400)
                            .setListener(mFABAnimatorListener);

                    mFloatingActionButton3.setVisibility(View.VISIBLE);
                    mFloatingActionButton3.animate()
                            .translationXBy(-200f)
                            .translationYBy(-335f)
                            .setDuration(400)
                            .setListener(mFABAnimatorListener);

                    mFloatingActionButton4.setVisibility(View.VISIBLE);
                    mFloatingActionButton4.animate()
                            .translationXBy(-245f)
                            .translationYBy(-545f)
                            .setDuration(400)
                            .setListener(mFABAnimatorListener);

                    mFloatingActionButton5.setVisibility(View.VISIBLE);
                    mFloatingActionButton5.animate()
                            .translationXBy(-200f)
                            .translationYBy(-750f)
                            .setDuration(400)
                            .setListener(mFABAnimatorListener);

                    mFloatingActionButton6.setVisibility(View.VISIBLE);
                    mFloatingActionButton6.animate()
                            .translationXBy(-100f)
                            .translationYBy(-950f)
                            .setDuration(400)
                            .setListener(mFABAnimatorListener);

                    mMenuExpanded = true;
                } else {


                    mFloatingActionButton2.animate()
                            .translationXBy(100f)
                            .translationYBy(150f)
                            .setDuration(400)
                            .alpha(0)
                            .setDuration(3500)
                            .setListener(mFABAnimatorListener);

                    mFloatingActionButton3.animate()
                            .translationXBy(200f)
                            .translationYBy(335f)
                            .setDuration(400)
                            .alpha(0)
                            .setDuration(3000)
                            .setListener(mFABAnimatorListener);

                    mFloatingActionButton4.animate()
                            .translationXBy(245f)
                            .translationYBy(545f)
                            .setDuration(400)
                            .alpha(0)
                            .setDuration(2500)
                            .setListener(mFABAnimatorListener);

                    mFloatingActionButton5.animate()
                            .translationXBy(200f)
                            .translationYBy(750f)
                            .setDuration(400)
                            .alpha(0)
                            .setDuration(2000)
                            .setListener(mFABAnimatorListener);

                    mFloatingActionButton6.animate()
                            .translationXBy(100f)
                            .translationYBy(950f)
                            .setDuration(400)
                            .alpha(0)
                            .setDuration(1500)
                            .setListener(mFABAnimatorListener);

                    mMenuExpanded = false;
                }
            }
        });

        mFloatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CampusInformationActivity.class);
                Log.d(TAG, "Starting CampusInfo Activity");
                startActivity(i);
            }
        });

        mFloatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), CampusTransportationActivity.class);
//                startActivity(i);
            }
        });

        mFloatingActionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), OurVLEActivity.class);
                Log.d(TAG, "StartingOurVLEActivity");
                startActivity(i);
            }
        });

        mFloatingActionButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BOSSActivity.class);
                startActivity(i);
            }
        });

        mFloatingActionButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SASActivity.class);
                startActivity(i);
            }
        });

        mViewPagerTabIcons = new Drawable[]
                {ResourcesCompat.
                    getDrawable(getResources(),
                            R.drawable.home_selected, null),
                ResourcesCompat.
                        getDrawable(getResources(),
                                R.drawable.filmstrip, null)};


        adapter = new HomePageViewPagerAdapter(getActivity().getSupportFragmentManager(),
                mViewPagerTabIcons,
                new Fragment[]{new HomeNewsFragment(), new HomeVideosFragment() }
                );

        mHome_ViewPager = (ViewPager)v.findViewById(R.id.landing_viewpager);
        mHome_ViewPager.setAdapter(adapter);

        tabs = (TabLayout)v.findViewById(R.id.landing_tabs);
        tabs.setupWithViewPager(mHome_ViewPager);
        tabs.getTabAt(0).setIcon(R.drawable.home_selected);
        tabs.getTabAt(1).setIcon(R.drawable.filmstrip);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tab.setIcon(R.drawable.home_selected);
                } else if (tab.getPosition() == 1) {
                    tab.setIcon(R.drawable.filmstrip_selected);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tab.setIcon(R.drawable.ic_home_white_24dp);
                } else if (tab.getPosition() == 1) {
                    tab.setIcon(R.drawable.filmstrip);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        return v;

    }
}
