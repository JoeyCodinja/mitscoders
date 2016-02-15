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


import java.lang.reflect.Array;
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

    private ArrayList<FloatingActionButton> mFABs = new ArrayList<>();
    private Animator.AnimatorListener mFABAnimatorListener;
    public static Boolean mMenuExpanded = false;
    private Animation fadeIn, fadeOut;
    private float screenHeight, screenWidth;

    int[] fabIDs = {R.id.mainFab,
            R.id.campus_info_fab,
            R.id.transport_fab,
            R.id.ourvle_fab,
//            R.id.boss_fab,
            R.id.sas_fab};

    float[][] fabCoords = new float[fabIDs.length][2];

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
                            case R.id.campus_info_fab:
                                i = new Intent(getActivity(), CampusInformationActivity.class);
                                startActivity(i);
                                break;
                            case R.id.transport_fab:
                                i = new Intent(getActivity(), TransportActivity.class);
                                startActivity(i);
                                break;
                            case R.id.ourvle_fab:
                                i = new Intent(getActivity(), OurVLELoginActivity.class);
                                startActivity(i);
                                break;
//                            case R.id.boss_fab:
//                                i = new Intent(getActivity(), BOSSActivity.class);
//                                startActivity(i);
//                                break;
                            case R.id.sas_fab:
                                i = new Intent(getActivity(), SAS_Splash.class);
                                startActivity(i);
                                break;
                        }
                    }
                });
                fabCoords[fab][0] = mFABs.get(fab).getX();
                fabCoords[fab][1] = mFABs.get(fab).getY();
            }
        }

        mFABAnimatorListener = new Animator.AnimatorListener() {
            List<FloatingActionButton> buttons = mFABs.subList(1, mFABs.size());

            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "Animation Started");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "Animation Ended");
            }

            @Override
            public void onAnimationCancel(Animator animation) { Log.d(TAG, "Animation cancelled"); }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, "Animation Repeated");
            }
        };

        mFABs.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFABs.get(0).setClickable(false);

                FrameLayout shade = (FrameLayout)((FrameLayout)v.getParent().getParent()).getChildAt(1);
                shade.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });

                int height = ((FrameLayout)v.getParent().getParent()).getHeight();
                int width = ((FrameLayout)v.getParent().getParent()).getWidth();

                if (!mMenuExpanded) {
                    shade.setVisibility(View.VISIBLE);
                    shade.animate().alpha(1).setDuration(150);
                    for (int fab = 1; fab <= mFABs.size() - 1; fab++) {
                        if (mFABs.get(fab).getAlpha() == 0){
                            mFABs.get(fab).setVisibility(View.VISIBLE);
                            mFABs.get(fab).animate()
                                    .setDuration(400)
                                    .alpha(1)
//                                    .translationXBy( (float) -( (-35 * pow(fab,2))+(210*fab)-75))
//                                    .translationYBy( (float) -( ( 150.83 * pow(fab, 1.1533f) ) ))
                                    .setListener(mFABAnimatorListener);
                        }
                        else {
                            mFABs.get(fab).setVisibility(View.VISIBLE);
                            mFABs.get(fab).animate()
                                    .setDuration(400)
                                    .alpha(1)
//                                    .translationXBy( (float) -( (-35 * pow(fab, 2))+(210 * fab)-75))
//                                    .translationYBy( (float) -((150.83 * pow(fab, 1.1533f))))
                                    .setListener(mFABAnimatorListener);
                        }
                    }
                    mFABs.get(0).setClickable(true);
                    mMenuExpanded = true;
                } else {
                    shade.animate().alpha(0).setDuration(150);
                    for (int fab = 1; fab <= mFABs.size() - 1; fab++) {
                        mFABs.get(fab).animate()
                                .setDuration(2500 - (500 * (fab - 1)))
//                                .translationXBy( (float) (-35 * (pow(fab, 2))) + (210 * fab) -75 )
//                                .translationYBy( (float) ( 150.83 * (pow(fab, 1.1533f))) )
                                .alpha(0)
                                .setListener(mFABAnimatorListener);
                        mFABs.get(fab).setVisibility(View.GONE);
                    }
                    shade.setVisibility(View.GONE);
                    mFABs.get(0).setClickable(true);
                    mMenuExpanded = false;
                }


            }
        });


        adapter = new HomePageViewPagerAdapter(getActivity().getSupportFragmentManager(),
                new Fragment[]{new HomeNewsFragment(), new HomeVideosFragment()}
        );

        mHome_ViewPager = (ViewPager) v.findViewById(R.id.landing_viewpager);
        mHome_ViewPager.setAdapter(adapter);
        mHome_ViewPager.setScrollBarSize(10);


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
