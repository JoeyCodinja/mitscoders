package mits.uwi.com.ourmobileenvironment.campusinformationfragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.TextAlignment;




import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 17/06/2015.
 */
public class CampusInformationFragment extends Fragment {

    ExpandableListView mCampusInfo_ExpandableList;
    LayoutInflater inflater;

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
        mCampusInfo_ExpandableList.setGroupIndicator(null);

        return v;
    }

    public class CampusInfoListAdapter extends BaseExpandableListAdapter{
        private String[] groups = {getResources().getString(R.string.campus_info_snippet_title1),
                                    getResources().getString(R.string.campus_info_snippet_title2),
                                    getResources().getString(R.string.campus_info_snippet_title3),
                                    getResources().getString(R.string.campus_info_snippet_title4),
                                    getResources().getString(R.string.campus_info_snippet_title5),
                                    getResources().getString(R.string.campus_info_snippet_title6)
                                  };
        private String[] children ={getResources().getString(R.string.campus_info_snippet_body1),
                getResources().getString(R.string.campus_info_snippet_body2),
                getResources().getString(R.string.campus_info_snippet_body3),
                getResources().getString(R.string.campus_info_snippet_body4),
                getResources().getString(R.string.campus_info_snippet_body5),
                getResources().getString(R.string.campus_info_snippet_body6)};

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

            DocumentView textView = new DocumentView(CampusInformationFragment.this.getActivity(), DocumentView.PLAIN_TEXT);
            textView.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
            textView.setText(getChild(groupPosition, childPosition).toString());
            textView.getDocumentLayoutParams().setAntialias(true);
            textView.getDocumentLayoutParams().setInsetPaddingLeft(14.0f);
            textView.getDocumentLayoutParams().setInsetPaddingRight(14.0f);
            return textView;

        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }


    }
}
