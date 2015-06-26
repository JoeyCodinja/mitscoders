package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 26/06/2015.
 */
public class CampusInfoListFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> mCampusInfosList = new ArrayList<String>();
        mCampusInfosList.add("Campus Eateries");
        mCampusInfosList.add("Campus Listings");
        mCampusInfosList.add("Campus Information");

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                        mCampusInfosList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment fragment;
        if (position == 0){
            fragment = new EateriesFragment();
            fm.beginTransaction().replace(R.id.campusinfo_fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        if (position == 1){
            fragment = new CampusListingsFragment();
            fm.beginTransaction().replace(R.id.campusinfo_fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        if (position == 2){
            fragment = new CampusInformationFragment();
            fm.beginTransaction().replace(R.id.campusinfo_fragmentContainer,fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
