
package mits.uwi.com.ourmobileenvironment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import mits.uwi.com.ourmobileenvironment.campusinformationfragments.CampusInfoListFragment;


public class CampusInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        FragmentManager fm=getSupportFragmentManager();
        Fragment dfragment=fm.findFragmentById(R.id.directoryfragmentcontainer);
        if (dfragment==null){
            dfragment=new DirectoryFragment();
            fm.beginTransaction().add(R.id.directoryfragmentcontainer, dfragment).commit();
        }
        //Calls to this function reposition the overflow
        ToprightBar.setTopOverflow(this);



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
