package mits.uwi.com.ourmobileenvironment;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import mits.uwi.com.ourmobileenvironment.campusinformationfragments.ChooseFacultyFragment;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.UWIInformationFragments.*;
import mits.uwi.com.ourmobileenvironment.sas.unavailable.Fragment.UnavailableFragment;

public class CampusInformationSubActivity extends AppCompatActivity
        implements CampusLife.OnFragmentInteractionListener,
                    History.OnFragmentInteractionListener,
                    Facilities.OnFragmentInteractionListener,
                    HousingAccomodation.OnFragmentInteractionListener
                    {

    FragmentManager fm;
    Fragment fragment;
    ActionBar toolbar;
    DownloadClickedBroadcastReceiver dlBroadcastReceiever;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UWIMonaApplication application = (UWIMonaApplication) getApplication();
        application.screenViewHitAnalytics("Activity~CampusInformationSub");

        setContentView(R.layout.activity_campus_information_sub);
        toolbar = getSupportActionBar();

        if (toolbar != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }

        IntentFilter dlClickedFilter = new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        dlBroadcastReceiever = new DownloadClickedBroadcastReceiver();
        this.registerReceiver(dlBroadcastReceiever,
                dlClickedFilter);

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
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(dlBroadcastReceiever);
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
    public void onBackPressed(){
        FragmentManager fm = getSupportFragmentManager();
        boolean backToFacultyChoice = false;
        for (int fragments=0; fragments<fm.getBackStackEntryCount(); fragments++){
            FragmentManager.BackStackEntry backStackEntry = fm.getBackStackEntryAt(fragments);
            String entryName = backStackEntry.getName();
            if (entryName == "toFaculty"){
                backToFacultyChoice = true;
            }
        }
        if (backToFacultyChoice){
            getToolbar().setTitle(R.string.to_faculties);
            fm.popBackStack("facultyTo", 0);
        }
        else
            fm.popBackStack();

        super.onBackPressed();
    }

    public ActionBar getToolbar(){
        return toolbar;
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
    public void onFragmentInteraction(Uri uri) {

    }

    private class DownloadClickedBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(CampusInformationSubActivity.this,
                    "Handbook download complete",
                    Toast.LENGTH_LONG)
                    .show();
        }
    }
}
