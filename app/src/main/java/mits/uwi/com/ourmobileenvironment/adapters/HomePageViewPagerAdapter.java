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

    private Drawable[] mTabHeadings;
    private Fragment[] mTabBodies;

    @Override
    public Fragment getItem(int position) { return mTabBodies[position]; }

    public HomePageViewPagerAdapter(FragmentManager fm, Drawable[]tabheadings, Fragment[]tabbodies){
        super(fm);
        this.mTabHeadings = tabheadings;
        this.mTabBodies = tabbodies;
    }

    @Override
    public int getCount() {
        return mTabBodies.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String string = "" ;
//        Drawable image = mTabHeadings[position];
//        image.setBounds(0, 0, image.getIntrinsicWidth()
//                , image.getIntrinsicHeight());
//        SpannableString string = new SpannableString(" ");
//        ImageSpan headingImageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        string.setSpan(headingImageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return string;
    }

}
