package mits.uwi.com.ourmobileenvironment;

import android.app.Activity;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import mits.uwi.com.ourmobileenvironment.campusinformationfragments.ChooseFacultyFragment;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.UWIInformationFragments.*;
import mits.uwi.com.ourmobileenvironment.sas.unavailable.Fragment.UnavailableFragment;

public class CampusInformationSubActivity extends AppCompatActivity
        implements CampusLife.OnFragmentInteractionListener,
                    Faculties.OnFragmentInteractionListener,
                    History.OnFragmentInteractionListener,
                    Facilities.OnFragmentInteractionListener,
                    HousingAccomodation.OnFragmentInteractionListener,
                    Faculties.ToSocialMediaIntents,
                    Faculties.DownloadHandbook
                    {

    FragmentManager fm;
    Fragment fragment;
    ActionBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_information_sub);
        toolbar = getSupportActionBar();

        if (toolbar != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }


        Intent fromCampusActivityMain = getIntent();
        Integer toWhichCampusInformationSub = fromCampusActivityMain
                .getIntExtra(CampusInformationActivity.TO_WHERE_INT, 0);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.campus_sub_fragment);

        if (fragment == null){
            switch(toWhichCampusInformationSub){
                case R.id.to_campus_life:
                    fragment = new UnavailableFragment();
                    toolbar.setTitle(R.string.to_campus_life);
                    break;
                case R.id.to_campus_housing:
                    fragment = new UnavailableFragment();
                    toolbar.setTitle(R.string.to_campus_housing);
                    break;
                case R.id.to_campus_facilities:
                    fragment = new UnavailableFragment();
                    toolbar.setTitle(R.string.to_campus_facilities);
                    break;
                case R.id.to_faculties:
                    fragment = ChooseFacultyFragment.newInstance();
                    toolbar.setTitle(R.string.to_faculties);
                    break;
                case R.id.to_history:
                    fragment = new UnavailableFragment();
                    toolbar.setTitle(R.string.to_history);
                    break;
            }

            fm.beginTransaction()
                    .add(R.id.campus_sub_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_campus_information_sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
              Log.d("CampusSubInfoFragment", String.valueOf(this.fragment.getId()));
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getSupportFragmentManager();
        if (this.fragment instanceof ChooseFacultyFragment){
            toolbar.setTitle(R.string.to_faculties);
            fm.popBackStack();
            fm.beginTransaction()
                    .replace(R.id.campus_sub_fragment,
                             ChooseFacultyFragment.newInstance())
                    .commit();
        }
        else{
            fm.popBackStack();
        }
        super.onBackPressed();
    }

    public ActionBar getToolbar(){
        return toolbar;
    }

    @Override
    public void toTwitter(View v){
        String url = (String)v.getTag();
        if (url != null && !url.equals("")){
            Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
            startActivity(i);
        }
        else
            showURLErrorToast();
    }

    @Override
    public void toFacebook(final View v) {
        String url = (String)v.getTag();
        if (url != null && !url.equals("")){
            Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
            startActivity(i);
        }
        else
            showURLErrorToast();
    }

    @Override
    public void toInstagram(View v) {
        String url = (String) v.getTag();
        if (url != null && !url.equals("")) {
            Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
            startActivity(i);
        }
        else
            showURLErrorToast();
    }



    private void showURLErrorToast(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        R.string.sub_information_url_error,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean fileAlreadyExists(String filename){
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        for (File file: dir.listFiles()){
            if (file.getName() == filename)
                return true;
        }

        return false;
    }

    @Override
    public boolean downloadFacultyHandbook(String uri, String faculty){
        Uri handBookDL = Uri.parse(uri);

        DownloadManager.Request progGuidelinesReq =
                new DownloadManager.Request(handBookDL);

        String filename = faculty;
        // check to see if a file already exists
        // if it does .. don't go through with the download
        // if it doesn't ... go through with the download

        filename += "faculty_handbook.pdf ";


        if (fileAlreadyExists(filename))
            return false;


        progGuidelinesReq = progGuidelinesReq
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                        filename);
        progGuidelinesReq.setTitle(filename);
        progGuidelinesReq.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        progGuidelinesReq.allowScanningByMediaScanner();
        DownloadManager dlManager  = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        dlManager.enqueue(progGuidelinesReq);

        return true;

    }

}
