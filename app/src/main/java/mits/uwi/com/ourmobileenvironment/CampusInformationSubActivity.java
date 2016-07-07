package mits.uwi.com.ourmobileenvironment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import mits.uwi.com.ourmobileenvironment.campusinformationfragments.CampusInformationFragment;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.UWIInformationFragments.*;

public class CampusInformationSubActivity extends AppCompatActivity {

    FragmentManager fm;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_information_sub);

        Intent fromCampusActivityMain = getIntent();
        Integer toWhichCampusInformationSub = fromCampusActivityMain
                .getIntExtra(CampusInformationFragment.TO_WHERE_INT, 0);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.campus_sub_fragment);

        if (fragment == null){
            switch(toWhichCampusInformationSub){
                case R.id.to_campus_life:
                    fragment = new CampusLife();
                    break;
                case R.id.to_campus_housing:
                    fragment = new HousingAccomodation();
                    break;
                case R.id.to_campus_facilities:
                    fragment = new Facilities();
                    break;
                case R.id.to_faculties:
                    fragment = new Faculties();
                    break;
                case R.id.to_history:
                    fragment = new History();
                    break;
                case R.id.to_shrugs:
                    fragment = new Shrugs();
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
