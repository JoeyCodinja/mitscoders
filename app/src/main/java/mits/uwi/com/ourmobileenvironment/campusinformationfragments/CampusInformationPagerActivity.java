package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.CampusInformationActivity;
import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 22/07/2015.
 */
public class CampusInformationPagerActivity extends FragmentActivity {

    private ViewPager mCampusInfoViewPager;
    private ArrayList<String> mSnippets = new ArrayList<String>();
    private ArrayList<String> mSnippetTitles = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCampusInfoViewPager = new ViewPager(this);
        mCampusInfoViewPager.setId(R.id.campusinfo_view_pager);
        setContentView(mCampusInfoViewPager);

        mSnippetTitles.add(getResources().getString(R.string.campus_info_snippet_title1));
        mSnippetTitles.add(getResources().getString(R.string.campus_info_snippet_title2));
        mSnippetTitles.add(getResources().getString(R.string.campus_info_snippet_title3));
        mSnippetTitles.add(getResources().getString(R.string.campus_info_snippet_title4));
        mSnippetTitles.add(getResources().getString(R.string.campus_info_snippet_title5));
        mSnippetTitles.add(getResources().getString(R.string.campus_info_snippet_title6));

        mSnippets.add(getResources().getString(R.string.campus_info_snippet_body1));
        mSnippets.add(getResources().getString(R.string.campus_info_snippet_body2));
        mSnippets.add(getResources().getString(R.string.campus_info_snippet_body3));
        mSnippets.add(getResources().getString(R.string.campus_info_snippet_body4));
        mSnippets.add(getResources().getString(R.string.campus_info_snippet_body5));
        mSnippets.add(getResources().getString(R.string.campus_info_snippet_body6));

        FragmentManager fm = getSupportFragmentManager();
        mCampusInfoViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return mSnippets.size();
            }

            @Override
            public Fragment getItem(int position) {
                String currentSnippetBody = mSnippets.get(position);
                String currentSnippetTitle = mSnippetTitles.get(position);
                return SnippetFragment.newInstance(currentSnippetTitle, currentSnippetBody);
            }
        });
    }
}