package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;



/**
 * Created by Danuel on 26/07/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] groups;
    private String[] children;
    private int numOfTabs;
    private String[] tabTitles = {"ACCREDITED", "PELICANS", "'GREEN' CAMPUS", "WHO WE SERVE", "MISSION", "VISION"};

    public ViewPagerAdapter(FragmentManager fm, String mTitles[], String mChildren[], int mNumOfTabs) {
        super(fm);
        this.groups = mTitles;
        this.numOfTabs = mNumOfTabs;
        this.children = mChildren;
    }

    @Override
    public Fragment getItem(int position) {
        String currentSnippetTitle = groups[position];
        String currentSnippetBody = children[position];
        return SnippetFragment.newInstance(currentSnippetTitle, currentSnippetBody);
    }

    @Override
    public int getCount() {
        return groups.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}
