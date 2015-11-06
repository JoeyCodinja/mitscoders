package mits.uwi.com.ourmobileenvironment.ourvlefragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 17/06/2015.
 */
public class CourseListFragment extends ListFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        ArrayList<String> mDummyCourses = new ArrayList<String>();
        mDummyCourses.add("COMP1220 - Computing & Society");
        mDummyCourses.add("COMP1161 - Introduction to Object Oriented Programming");
        mDummyCourses.add("COMP1127 - Introduction to Programming(I)");
        mDummyCourses.add("COMP1126 - Introduction to Programming(II)");

        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        mDummyCourses);
        setListAdapter(adapter);
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // On clicking a course represented in this list, the user should be
        // to the fragment containing the information within the course container

        //TODO 1 Pass arguments with info identifying the course and sending
        //TODO 2 user data to validate their course permissions
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment fragment = new CourseContainerFragment();
        fm.beginTransaction()
                .replace(R.id.ourvle_fragmentContainer, fragment)
                .commit();

    }
}
