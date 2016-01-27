package mits.uwi.com.ourmobileenvironment.ourvle.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import mits.uwi.com.ourmobileenvironment.ourvle.classes.adapters.HomeOptionsAdapter;


/**
 * A fragment representing a list of options for the home screen.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnOptionSelectedListener}
 * interface.
 * @author Javon Davis
 */
public class OptionListFragment extends ListFragment {

    private OnOptionSelectedListener mListener;
    HomeOptionsAdapter adapter;

    public static OptionListFragment newInstance() {
        return new OptionListFragment();
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OptionListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeOptionsAdapter(getActivity());
        setListAdapter(adapter);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnOptionSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnOptionSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onOptionSelected(position);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = getListView();
        listView.setDividerHeight(3);
    }

    public interface OnOptionSelectedListener {
        void onOptionSelected(int id);
    }

}
