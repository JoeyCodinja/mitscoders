
package mits.uwi.com.ourmobileenvironment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bluejamesbond.text.DocumentView;

import mits.uwi.com.ourmobileenvironment.campusinformationfragments.CampusInfoListFragment;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.EateriesFragment;


public class CampusInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_information);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.campusinfo_fragmentContainer);

        if (fragment == null){
            fragment = new CampusInfoListFragment();
            fm.beginTransaction()
                    .add(R.id.campusinfo_fragmentContainer, fragment)
                    .commit();
        }
        //Calls to this function reposition the overflow
        ToprightBar.setTopOverflow(this);
        }

    @Override
    public void onBackPressed() {
        DocumentView campus_info_heading = (DocumentView)findViewById(R.id.campus_info_heading_fragment);
        campus_info_heading.setVisibility(DocumentView.GONE);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_eateries, menu);
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
