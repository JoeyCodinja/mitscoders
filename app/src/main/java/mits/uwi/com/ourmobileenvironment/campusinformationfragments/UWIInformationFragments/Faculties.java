package mits.uwi.com.ourmobileenvironment.campusinformationfragments.UWIInformationFragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bluejamesbond.text.DocumentView;

import java.io.File;

import mits.uwi.com.ourmobileenvironment.CampusInformationSubActivity;
import mits.uwi.com.ourmobileenvironment.R;

public class Faculties extends Fragment {
    private ToSocialMediaIntents mListener;
    private DownloadHandbook facultyHandbooks;
    private String facultyChosen;
    private Drawable dPicture;
    private ImageView dean;
    private ActionBar toolbar;
    private DocumentView facultySnippet;
    private Resources drawableResources;

    public static Faculties newInstance(String faculty) {
        Faculties fragment = new Faculties();

        Bundle args = new Bundle();
        args.putString("facultyChosen", faculty);
        fragment.setArguments(args);

        return fragment;
    }

    public Faculties() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facultyChosen = getArguments().getString("facultyChosen");
        drawableResources = getResources();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_faculties_campusinfosub,
                                  container,
                                  false);
        ImageView twitter = (ImageView)v.findViewById(R.id.twitterButton);
        ImageView facebook = (ImageView)v.findViewById(R.id.facebookButton);
        ImageView instagram = (ImageView)v.findViewById(R.id.instagramButton);
        Button facultyHandbook = (Button)v.findViewById(R.id.dlProgramGuidelines);

        facultySnippet = (DocumentView)v.findViewById(R.id.faculty_snippet);
        dean = (ImageView) v.findViewById(R.id.dean_picture);

        toolbar = ((CampusInformationSubActivity)getActivity()).getToolbar();

        switch(facultyChosen){
            case "FST":
                v = layoutSciTech(v);
                String[] fst_info = getResources().getStringArray(R.array.fst_info);
                twitter.setTag(fst_info[2]);
                instagram.setTag(fst_info[0]);
                facebook.setTag(fst_info[1]);
                facultyHandbook.setTag(fst_info[4]);
                break;
            case "HUM":
                v = layoutHumEd(v);
                String[] humed_info = getResources().getStringArray(R.array.humed_info);
                twitter.setTag(humed_info[2]);
                instagram.setTag(humed_info[0]);
                facebook.setTag(humed_info[1]);
                facultyHandbook.setTag(humed_info[4]);
                break;
            case "LAW":
                v = layoutLaw(v);
                String[] law_info = getResources().getStringArray(R.array.law_info);
                twitter.setTag(law_info[2]);
                instagram.setTag(law_info[0]);
                facebook.setTag(law_info[1]);
                facultyHandbook.setTag(law_info[4]);
                break;
            case "MED":
                v = layoutMedSci(v);
                String[] medsci_info = getResources().getStringArray(R.array.medsci_info);
                twitter.setTag(medsci_info[2]);
                instagram.setTag(medsci_info[0]);
                facebook.setTag(medsci_info[1]);
                facultyHandbook.setTag(medsci_info[4]);
                break;
            case "SOC":
                v = layoutSoSci(v);
                String[] sosci_info = getResources().getStringArray(R.array.fst_info);
                twitter.setTag(sosci_info[2]);
                instagram.setTag(sosci_info[0]);
                facebook.setTag(sosci_info[1]);
                facultyHandbook.setTag(sosci_info[4]);
                break;
        }

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toTwitter(v);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toFacebook(v);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toInstagram(v);
            }
        });

        facultyHandbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facultyHandbooks.downloadFacultyHandbook((String)v.getTag(), facultyChosen);
            }
        });

        return v;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public View layoutSciTech(View v){
        toolbar.setTitle(R.string.fst_name);
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21)
            dPicture = drawableResources.getDrawable(R.drawable.faculties_fpas_dean);
        else
            dPicture = drawableResources.getDrawable(R.drawable.faculties_fpas_dean, null);
        dean.setImageDrawable(dPicture);
        facultySnippet.setText(getResources().getStringArray(R.array.fst_info)[3]);

        return v;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public View layoutMedSci(View v){
        toolbar.setTitle(R.string.medsci_name);
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21)
            dPicture = drawableResources.getDrawable(R.drawable.faculties_medsci_dean);
        else
            dPicture = drawableResources.getDrawable(R.drawable.faculties_medsci_dean, null);
        dean.setImageDrawable(dPicture);
        facultySnippet.setText(getResources().getStringArray(R.array.medsci_info)[3]);

        return v;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public View layoutLaw(View v){
        toolbar.setTitle(R.string.law_name);
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21)
            dPicture = drawableResources.getDrawable(R.drawable.faculties_law_dean);
        else
            dPicture = drawableResources.getDrawable(R.drawable.faculties_law_dean, null);
        dean.setImageDrawable(dPicture);
        facultySnippet.setText(getResources().getStringArray(R.array.law_info)[3]);
        return v;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public View layoutSoSci(View v){
        toolbar.setTitle(R.string.socsci_name);
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21)
            dPicture = drawableResources.getDrawable(R.drawable.faculties_sosci_dean);
        else
            dPicture = drawableResources.getDrawable(R.drawable.faculties_sosci_dean, null);
        dean.setImageDrawable(dPicture);
        facultySnippet.setText(getResources().getStringArray(R.array.sosci_info)[3]);

        return v;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public View layoutHumEd(View v){
        toolbar.setTitle(R.string.humed_name);
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21)
            dPicture = drawableResources.getDrawable(R.drawable.faculties_humed_dean);
        else
            dPicture = drawableResources.getDrawable(R.drawable.faculties_humed_dean, null);
        dean.setImageDrawable(dPicture);
        facultySnippet.setText(getResources().getStringArray(R.array.humed_info)[3]);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ToSocialMediaIntents) activity;
            facultyHandbooks = (DownloadHandbook) activity;
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


    public interface ToSocialMediaIntents{
        public void toTwitter(View v);

        public void toFacebook(View v);

        public void toInstagram(View v);
    }

    public interface DownloadHandbook{
        public boolean downloadFacultyHandbook(String uri, String faculty);

        public boolean fileAlreadyExists(String filename);
    }

}

