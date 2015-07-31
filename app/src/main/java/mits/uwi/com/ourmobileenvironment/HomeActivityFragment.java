package mits.uwi.com.ourmobileenvironment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {

    private Button mToSASActivity, mToBOSSActivity, mToOURLVEActivity, mToCampusInformationActivity;
    private ImageView mFloatingActionButton, mFloatingActionButton2, mFloatingActionButton3,
            mFloatingActionButton4, mFloatingActionButton5, mFloatingActionButton6;

    public static Boolean mMenuExpanded = false;

    private static final String TAG = "LandingFragment";

    public Boolean getmMenuExpanded() {
        return mMenuExpanded;
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

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMenuExpanded == false) {
                    Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.translate_fab);
                    mFloatingActionButton2.setVisibility(View.VISIBLE);
                    mFloatingActionButton2.startAnimation(animation);

                    Animation animation1 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.translate_fab2);
                    mFloatingActionButton3.setVisibility(View.VISIBLE);
                    mFloatingActionButton3.startAnimation(animation1);

                    Animation animation2 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.translate_fab3);
                    mFloatingActionButton4.setVisibility(View.VISIBLE);
                    mFloatingActionButton4.startAnimation(animation2);

                    Animation animation3 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.translate_fab4);
                    mFloatingActionButton5.setVisibility(View.VISIBLE);
                    mFloatingActionButton5.startAnimation(animation3);

                    Animation animation4 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.translate_fab5);
                    mFloatingActionButton6.setVisibility(View.VISIBLE);
                    mFloatingActionButton6.startAnimation(animation4);

                    mMenuExpanded = true;
                } else {
                    Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rvrs_translate_fab);
                    mFloatingActionButton2.startAnimation(animation);

                    Animation animation1 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rvrs_translate_fab2);
                    mFloatingActionButton3.startAnimation(animation1);

                    Animation animation2 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rvrs_translate_fab3);
                    mFloatingActionButton4.startAnimation(animation2);

                    Animation animation3 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rvrs_translate_fab4);
                    mFloatingActionButton5.startAnimation(animation3);

                    Animation animation4 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rvrs_translate_fab5);
                    mFloatingActionButton6.startAnimation(animation4);

                    mFloatingActionButton2.setVisibility(View.GONE);
                    mFloatingActionButton3.setVisibility(View.GONE);
                    mFloatingActionButton4.setVisibility(View.GONE);
                    mFloatingActionButton5.setVisibility(View.GONE);
                    mFloatingActionButton6.setVisibility(View.GONE);

                    mMenuExpanded = false;
                }
            }
        });

        mToSASActivity = (Button)v.findViewById(R.id.to_sas_activity_button);
        mToSASActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SASActivity.class);
                Log.d(TAG, "Starting SASActivity");
                startActivity(i);
            }
        });

        mToBOSSActivity = (Button)v.findViewById(R.id.to_boss_activity_button);
        mToBOSSActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BOSSActivity.class);
                Log.d(TAG, "Starting BOSSActivity");
                startActivity(i);
            }
        });

        mToOURLVEActivity = (Button)v.findViewById(R.id.to_ourvle_activity_button);
        mToOURLVEActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), OurVLEActivity.class);
                Log.d(TAG, "StartingOurVLEActivity");
                startActivity(i);
            }
        });

        mToCampusInformationActivity = (Button)v.findViewById(R.id.to_campus_information_activity_button);
        mToCampusInformationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), CampusInformationActivity.class);
                Log.d(TAG, "Starting CampusInfo Activity");
                startActivity(i);
            }
        });


        return v;

    }
}
