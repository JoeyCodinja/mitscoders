package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.database.DataSetObserver;
import android.support.annotation.DimenRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.lang.Float;


import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 17/06/2015.
 */
public class CampusInformationFragment extends Fragment {


//    Button mCampusInfoSnippetButton1, mCampusInfoSnippetButton2,
//            mCampusInfoSnippetButton3, mCampusInfoSnippetButton4,
//            mCampusInfoSnippetButton5, mCampusInfoSnippetButton6;
    ExpandableListView mCampusInfo_ExpandableList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_campus_info, container, false);

        mCampusInfo_ExpandableList = (ExpandableListView)v.findViewById(R.id.campus_info_expandable_list);
        mCampusInfo_ExpandableList.setAdapter( new CampusInfoListAdapter());



        return v;
    }

    public class CampusInfoListAdapter extends BaseExpandableListAdapter{
        private String[] groups = {getResources().getString(R.string.campusinfo_snippet_button1),
                                    getResources().getString(R.string.campusinfo_snippet_button2),
                                    getResources().getString(R.string.campusinfo_snippet_button3),
                                    getResources().getString(R.string.campusinfo_snippet_button4),
                                    getResources().getString(R.string.campusinfo_snippet_button5),
                                    getResources().getString(R.string.campusinfo_snippet_button6)
                                  };
        private String[] children ={getResources().getString(R.string.campusinfo_snippet1),
                getResources().getString(R.string.campusinfo_snippet2),
                getResources().getString(R.string.campusinfo_snippet3),
                getResources().getString(R.string.campusinfo_snippet4),
                getResources().getString(R.string.campusinfo_snippet5),
                getResources().getString(R.string.campusinfo_snippet6)};

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        public int getChildrenCount(int i){
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {

            return children[groupPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            TextView textView = new TextView(CampusInformationFragment.this.getActivity());
            textView.setText(getGroup(groupPosition).toString());
            textView.setTextSize(16f);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            return textView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            TextView textView = new TextView(CampusInformationFragment.this.getActivity());
            textView.setText(getChild(groupPosition, childPosition).toString());
            textView.setTextSize(14f);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            return textView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }


    }
}
