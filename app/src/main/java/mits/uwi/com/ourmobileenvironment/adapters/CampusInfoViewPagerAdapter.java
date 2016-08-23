package mits.uwi.com.ourmobileenvironment.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterViewFlipper;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;

import mits.uwi.com.ourmobileenvironment.campusinformationfragments.SnippetFragment;


/**
 * Created by Danuel on 26/07/2015.
 */


public class CampusInfoViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mGroups;
    private String[] mChildren;
    private int mNumOfTabs;

    public CampusInfoViewPagerAdapter(FragmentManager fm,
                                      String titles[],
                                      String children[],
                                      int numOfTabs) {
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




}
