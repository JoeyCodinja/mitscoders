package mits.uwi.com.ourmobileenvironment.homepagefragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.BOSSActivity;
import mits.uwi.com.ourmobileenvironment.Transport.TransportActivity;
import mits.uwi.com.ourmobileenvironment.CampusInformationActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.adapters.HomePageViewPagerAdapter;
import mits.uwi.com.ourmobileenvironment.ourvle.activities.OurVLELoginActivity;
import mits.uwi.com.ourmobileenvironment.sas.SAS_Splash;

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
                                i = new Intent(getActivity(), TransportActivity.class);
                                startActivity(i);
                                break;
                            case R.id.landing_pageFAB3:
                                i = new Intent(getActivity(), OurVLELoginActivity.class);
                                startActivity(i);
                                break;
                            case R.id.landing_pageFAB4:
                                i = new Intent(getActivity(), BOSSActivity.class);
                                startActivity(i);
                                break;
                            case R.id.landing_pageFAB5:
                                i = new Intent(getActivity(), SAS_Splash.class);
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
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

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
//                        mFABs.get(fab).animate()
//                                .setDuration(400)
//                                .translationXBy((float) -((-35 * pow(fab, 2)) + (210 * fab) - 75))
//                                .translationYBy((float) -((150.83 * pow(fab, 1.1533f))))
//                                .setListener(mFABAnimatorListener);

                        ObjectAnimator fanOutX = ObjectAnimator.ofFloat(mFABs.get(fab),
                                "x",
                                mFABs.get(fab).getX(),
                                (float) (mFABs.get(fab).getX() -
                                        (-30 * (pow(fab, 2)) + (160 * fab) - 45)));
                        fanOutX.setDuration(400);

                        ObjectAnimator fanOutY = ObjectAnimator.ofFloat(mFABs.get(fab),
                                "y",
                                mFABs.get(fab).getY(),
                                (float) (mFABs.get(fab).getY() -
                                        (195 * fab)));
                        fanOutY.setDuration(400);

                        AnimatorSet fanOut = new AnimatorSet();
                        fanOut.play(fanOutX).with(fanOutY);
                        fanOut.addListener(mFABAnimatorListener);
                        fanOut.start();

                    }
                    mMenuExpanded = true;
                } else {
                    for (int fab = 1; fab <= mFABs.size() - 1; fab++) {
//                        mFABs.get(fab).animate()
//                                .setDuration(3500 - (500 * (fab - 1)))
//                                .translationXBy((float) (-35 * (pow(fab, 2))) + (210 * fab) - 75)
//                                .translationYBy((float) (150.83 * (pow(fab, 1.1533f))))
//                                .alpha(0)
//                                .setListener(mFABAnimatorListener);

                        ObjectAnimator fanInX = ObjectAnimator.ofFloat(mFABs.get(fab),
                                "x",
                                mFABs.get(fab).getX(),
                                (float) (mFABs.get(fab).getX() +
                                        (-30 * (pow(fab, 2)) + (160 * fab) - 45)));
                        fanInX.setDuration(3500 - (500 * (fab - 1)));

                        ObjectAnimator fanInY = ObjectAnimator.ofFloat(mFABs.get(fab),
                                "y",
                                mFABs.get(fab).getY(),
                                (float) (mFABs.get(fab).getY() +
                                        (195 * fab)));
                        fanInY.setDuration(3500 - (500 * (fab - 1)));

                        ObjectAnimator fanInAlpha = ObjectAnimator.ofFloat(mFABs.get(fab),
                                "alpha",
                                mFABs.get(fab).getAlpha(),
                                0);
                        fanInAlpha.setDuration(3500 - (500 * (fab-1)));

                        AnimatorSet fanIn = new AnimatorSet();
                        fanIn.play(fanInX).with(fanInY);
                        fanIn.addListener(mFABAnimatorListener);
                        fanIn.start();


                    }
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
