package mits.uwi.com.ourmobileenvironment.campusinformationfragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.support.design.widget.TabLayout;
import android.widget.FrameLayout;

import com.bluejamesbond.text.DocumentView;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.adapters.CampusInfoViewPagerAdapter;


/**
 * Created by Danuel on 17/06/2015.
 */
public class CampusInformationFragment extends Fragment {

    ExpandableListView mCampusInfo_ExpandableList;
    ViewPager  mCampusInfo_ViewPager;
    CampusInfoViewPagerAdapter adapter;
    LayoutInflater inflater;
    TabLayout tabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] groups = {getResources().getString(R.string.campus_info_snippet_title1),
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

        View v = inflater.inflate(R.layout.activity_campus_information, container, false);

        FrameLayout innerFragment = (FrameLayout) v.findViewById(R.id.campusinfo_fragmentContainer);

        View v2 = inflater.inflate(R.layout.fragment_campus_info, innerFragment, false);

        innerFragment.addView(v2);

        FragmentManager fm = this.getActivity().getSupportFragmentManager();

        adapter = new CampusInfoViewPagerAdapter(getActivity().getSupportFragmentManager(), groups, children, groups.length);

        mCampusInfo_ViewPager = (ViewPager)v.findViewById(R.id.campus_info_viewpager);
        mCampusInfo_ViewPager.setAdapter(adapter);

        tabs = (TabLayout)v.findViewById(R.id.campusinfo_tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setupWithViewPager(mCampusInfo_ViewPager);

        return v;
    }


}
