package mits.uwi.com.ourmobileenvironment.adapters;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by Danuel on 11/8/2015.
 */
public class HomePageViewPagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] mTabBodies;

    @Override
    public Fragment getItem(int position) { return mTabBodies[position]; }

    public HomePageViewPagerAdapter(FragmentManager fm, Drawable[]tabheadings, Fragment[]tabbodies){
        super(fm);
        this.mTabBodies = tabbodies;
    }

    @Override
    public int getCount() {
        return mTabBodies.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // The Icons are set in the HomeActivityFragment
        return new String();
    }

}
