package mits.uwi.com.ourmobileenvironment.homepagefragments;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.BOSSActivity;
import mits.uwi.com.ourmobileenvironment.BusScheduleActivity;
import mits.uwi.com.ourmobileenvironment.CampusInformationActivity;
import mits.uwi.com.ourmobileenvironment.OurVLEActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.SASActivity;
import mits.uwi.com.ourmobileenvironment.adapters.HomePageViewPagerAdapter;

import static java.lang.Math.pow;


/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {

    private ImageView mFloatingActionButton, mFloatingActionButton2, mFloatingActionButton3,
            mFloatingActionButton4, mFloatingActionButton5, mFloatingActionButton6;
    private ArrayList<ImageView> mFABs = new ArrayList<ImageView>();
    private Animator.AnimatorListener mFABAnimatorListener;
    private View.OnClickListener FABClickListener;
    public static Boolean mMenuExpanded = false;

    int[] fabIDs = {R.id.landing_pageFAB,
            R.id.landing_pageFAB1,
            R.id.landing_pageFAB2,
            R.id.landing_pageFAB3,
            R.id.landing_pageFAB4,
            R.id.landing_pageFAB5};

    private ViewPager mHome_ViewPager;
    private Drawable[] mViewPagerTabIcons;
    private HomePageViewPagerAdapter adapter;
    private TabLayout tabs;

    private static final String TAG = "LandingFragment";

    public Boolean getmMenuExpanded() {
        return mMenuExpanded;
    }

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

        for (int fab = 0; fab <= fabIDs.length - 1; fab++) {
            mFABs.add((ImageView) v.findViewById(fabIDs[fab]));
            if (fab > 0) {
                mFABs.get(fab).setVisibility(View.INVISIBLE);
                mFABs.get(fab).setOnClickListener(new View.OnClickListener() {
                    Intent i;

                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.landing_pageFAB1:
                                i = new Intent(getActivity(), CampusInformationActivity.class);
                                startActivity(i);
                                break;
                            case R.id.landing_pageFAB2:
                                i = new Intent(getActivity(), BusScheduleActivity.class);
                                startActivity(i);
                                break;
                            case R.id.landing_pageFAB3:
                                i = new Intent(getActivity(), OurVLEActivity.class);
                                startActivity(i);
                                break;
                            case R.id.landing_pageFAB4:
                                i = new Intent(getActivity(), BOSSActivity.class);
                                startActivity(i);
                                break;
                            case R.id.landing_pageFAB5:
                                i = new Intent(getActivity(), SASActivity.class);
                                startActivity(i);
                                break;
                        }
                    }
                });

            }
        }

        mFABAnimatorListener = new Animator.AnimatorListener() {
            List<ImageView> buttons = mFABs.subList(1, mFABs.size());

            @Override
            public void onAnimationStart(Animator animation) {
                for (ImageView button : buttons) {
                    if (button.getAlpha() == 0f)
                        button.setAlpha(1f);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mMenuExpanded) {
                    for (ImageView button : buttons) {
                        button.setAlpha(1f);
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO: Images return to base state; mMenu Expanded = False
                mMenuExpanded = false;
                Log.d(TAG, "Animation Cancel");
                for (ImageView button : buttons) {
                    button.clearAnimation();
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        };


        mFABs.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mMenuExpanded) {
                    for (int fab = 1; fab <= mFABs.size() - 1; fab++) {
                        mFABs.get(fab).setVisibility(View.VISIBLE);
                        mFABs.get(fab).animate()
                                .setDuration(400)
                                .translationXBy((float) -((-35 * pow(fab, 2)) + (210 * fab) - 75))
                                .translationYBy((float) -((150.83 * pow(fab, 1.1533f))))
                                .setListener(mFABAnimatorListener);
                        v.getParent().getParent().bringChildToFront(mFABs.get(fab));
                    }
                    mMenuExpanded = true;
                } else {
                    for (int fab = 1; fab <= mFABs.size() - 1; fab++) {
                        mFABs.get(fab).animate()
                                .setDuration(3500 - (500 * (fab - 1)))
                                .translationXBy((float) (-35 * (pow(fab, 2))) + (210 * fab) - 75)
                                .translationYBy((float) (150.83 * (pow(fab, 1.1533f))))
                                .alpha(0)
                                .setListener(mFABAnimatorListener);
                    }
                    mMenuExpanded = false;
                }


            }
        });


//        FABClickListener = new View.OnClickListener() {
//            Intent i;
//            @Override
//            public void onClick(View v) {
//                switch(v.getId()){
//                    case R.id.landing_pageFAB1:
//                        i = new Intent(getActivity(), CampusInformationActivity.class);
//                        startActivity(i);
//                        break;
//                    case R.id.landing_pageFAB2:
////                        i = new Intent(getActivity(), CampusTransportationActivity.class);
////                        startActivity(i);
//                        break;
//                    case R.id.landing_pageFAB3:
//                        i = new Intent(getActivity(), OurVLEActivity.class);
//                        startActivity(i);
//                        break;
//                    case R.id.landing_pageFAB4:
//                        i = new Intent(getActivity(), BOSSActivity.class);
//                        startActivity(i);
//                        break;
//                    case R.id.landing_pageFAB5:
//                        i = new Intent(getActivity(), SASActivity.class);
//                        startActivity(i);
//                        break;
//                }
//            }
//        };


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
