package mits.uwi.com.ourmobileenvironment;

import android.app.Activity;
import android.content.Intent;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import mits.uwi.com.ourmobileenvironment.campusinformationfragments.CampusInformationFragment;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.UWIInformationFragments.*;

public class CampusInformationSubActivity extends Activity {

    FragmentManager fm;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_information_sub);

        Intent fromCampusActivityMain = getIntent();
        Integer toWhichCampusInformationSub = fromCampusActivityMain
                .getIntExtra(CampusInformationFragment.TO_WHERE_INT, 0);

        fm = getFragmentManager();
        fragment = fm.findFragmentById(R.id.campus_sub_fragment);

        if (fragment == null){
            switch(toWhichCampusInformationSub){
                case R.id.to_campus_life:
                    fragment = CampusLife.newInstance();
                    break;
                case R.id.to_campus_housing:
                    fragment = HousingAccomodation.newInstance();
                    break;
                case R.id.to_campus_facilities:
                    fragment = Facilities.newInstance();
                    break;
                case R.id.to_faculties:
                    fragment = Faculties.newInstance();
                    break;
                case R.id.to_history:
                    fragment = History.newInstance();
                    break;
            }

            fm.beginTransaction()
                    .add(R.id.campus_sub_fragment, fragment)
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
