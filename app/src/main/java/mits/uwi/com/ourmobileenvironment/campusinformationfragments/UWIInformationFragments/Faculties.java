package mits.uwi.com.ourmobileenvironment.campusinformationfragments.UWIInformationFragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.bluejamesbond.text.DocumentView;

import java.util.Map;
import java.util.jar.Attributes;

import mits.uwi.com.ourmobileenvironment.R;

public class Faculties extends Fragment {
    private OnFragmentInteractionListener mListener;
    private int facultySelected;
    ImageView dean;
    ExpandableListView expandableListView;
    DocumentView facultySnippet;

    public static Faculties newInstance(int faculty) {
        Fragment f = new Faculties();
        Bundle params = new Bundle();
        params.putInt("facultySelected",
                      faculty);
        f.setArguments(params);
        return new Faculties();
    }

    public Faculties() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facultySelected = savedInstanceState.getInt("facultySelected");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_campus_sub_faculty,
                                  container,
                                  false);
        dean = (ImageView) v.findViewById(R.id.dean);
        expandableListView = (ExpandableListView) v.findViewById(R.id.faculty_information);
        facultySnippet = (DocumentView) v.findViewById(R.id.faculty_snippet);

        switch (facultySelected){
            case R.id.fst_faculty_chosen:
                v = fstLayout(v);
                break;
            case R.id.humed_faculty_chosen:
                v = humedLayout(v);
                break;
            case R.id.law_faculty_chosen:
                v = lawLayout(v);
                break;
            case R.id.medsci_faculty_chosen:
                v = medsciLayout(v);
                break;
            case R.id.socsci_faculty_chosen:
                v = sosciLayout(v);
                break;
        }

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @TargetApi(21)
    private View lawLayout(View v){
        Drawable deanPicture;
        ImageView dean;

        if (Build.VERSION.SDK_INT > 16 && Build.VERSION.SDK_INT < 21){
            deanPicture = getResources().getDrawable(R.drawable.faculties_law_dean);
        }
        else{
            deanPicture =getResources().getDrawable(R.drawable.faculties_law_dean, null);
        }

        dean = (ImageView)v.findViewById(R.id.dean);
        dean.setImageDrawable(deanPicture);

        return v;
    }

    @TargetApi(21)
    private View sosciLayout(View v){
        Drawable deanPicture;
        if (Build.VERSION.SDK_INT > 16 && Build.VERSION.SDK_INT < 21){
            deanPicture = getResources().getDrawable(R.drawable.faculties_sosci_dean);
        }
        else{
            deanPicture =getResources().getDrawable(R.drawable.faculties_sosci_dean, null);
        }
        dean.setImageDrawable(deanPicture);
        return v;
    }

    @TargetApi(21)
    private View humedLayout(View v){
        Drawable deanPicture;

        if (Build.VERSION.SDK_INT > 16 && Build.VERSION.SDK_INT < 21){
            deanPicture = getResources().getDrawable(R.drawable.faculties_humed_dean);
        }
        else{
            deanPicture =getResources().getDrawable(R.drawable.faculties_humed_dean, null);
        }
        dean.setImageDrawable(deanPicture);


        return v;
    }

    @TargetApi(21)
    private View fstLayout(View v){
        Drawable deanPicture;
        if (Build.VERSION.SDK_INT > 16 && Build.VERSION.SDK_INT < 21){
            deanPicture = getResources().getDrawable(R.drawable.faculties_fpas_dean);
        }
        else{
            deanPicture =getResources().getDrawable(R.drawable.faculties_fpas_dean, null);
        }
        dean.setImageDrawable(deanPicture);
        return v;
    }

    @TargetApi(21)
    private View medsciLayout(View v){
        Drawable deanPicture;

        if (Build.VERSION.SDK_INT > 16 && Build.VERSION.SDK_INT < 21){
            deanPicture = getResources().getDrawable(R.drawable.faculties_medsci_dean);
        }
        else{
            deanPicture =getResources().getDrawable(R.drawable.faculties_medsci_dean, null);
        }

        dean.setImageDrawable(deanPicture);
        return v;
    }

    class FacultyInformationList implements ExpandableListAdapter {
        String[] informationGroups;
        FacultyInformation[] groupsInfo;

        public FacultyInformationList(String[] groups, FacultyInformation[] groupsInfo){
            informationGroups = groups;
            this.groupsInfo = groupsInfo;
        }

        public class FacultyInformation {
            String[] formLinks;
            String[] documentLinks;
            Attributes socialMediaLinks;
            String facultyHistorySnippet;

            public FacultyInformation(String facultyHistorySnippet,
                                      String[] formLinks,
                                      String[] documentLinks,
                                      Attributes socialMediaLinks){
                this.facultyHistorySnippet = facultyHistorySnippet;
                this.formLinks = formLinks;
                this.documentLinks = documentLinks;
                this.socialMediaLinks = socialMediaLinks;
            }

            public FacultyInformation(String[] resourceLinks,
                                      String[] formLinks,
                                      String[] documentLinks,
                                      String[] socialMediaLinks){
                this.formLinks = resourceLinks;
            }

        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getGroupCount() {
            return informationGroups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 0;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition,
                                 boolean isExpanded,
                                 View convertView,
                                 ViewGroup parent) {
            return null;
        }

        @Override
        public View getChildView(int groupPosition,
                                 int childPosition,
                                 boolean isLastChild,
                                 View convertView,
                                 ViewGroup parent) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void onGroupExpanded(int groupPosition) {

        }

        @Override
        public void onGroupCollapsed(int groupPosition) {

        }

        @Override
        public long getCombinedChildId(long groupId, long childId) {
            return 0;
        }

        @Override
        public long getCombinedGroupId(long groupId) {
            return 0;
        }
    }


}

