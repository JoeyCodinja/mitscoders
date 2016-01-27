package mits.uwi.com.ourmobileenvironment.adapters;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

import mits.uwi.com.ourmobileenvironment.campusinformationfragments.SnippetFragment;


/**
 * Created by Danuel on 26/07/2015.
 */
public class CampusInfoViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mGroups;
    private String[] mChildren;
    private int mNumOfTabs;
    private String[] mTabTitles = {"ACCREDITED", "PELICANS", "'GREEN' CAMPUS", "WHO WE SERVE", "MISSION", "VISION"};

    public CampusInfoViewPagerAdapter(FragmentManager fm, String titles[], String children[], int numOfTabs) {
        super(fm);
        this.mGroups = titles;
        this.mNumOfTabs = numOfTabs;
        this.mChildren = children;
    }

    @Override
    public Fragment getItem(int position) {
        String currentSnippetTitle = mGroups[position];
        String currentSnippetBody = mChildren[position];
        return SnippetFragment.newInstance(currentSnippetTitle, currentSnippetBody);
    }

    @Override
    public int getCount() {
        return mGroups.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }


}
