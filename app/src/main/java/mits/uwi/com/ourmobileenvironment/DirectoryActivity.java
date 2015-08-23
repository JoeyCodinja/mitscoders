package mits.uwi.com.ourmobileenvironment;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class DirectoryActivity extends AppCompatActivity {
    SearchView searchView;
    DirectoryFragment dfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        FragmentManager fm=getSupportFragmentManager();
        dfragment=new DirectoryFragment();
        fm.beginTransaction().add(R.id.directoryfragmentcontainer, dfragment).commit();
        ToprightBar.setTopOverflow(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_directory, menu);
        SearchManager searchManager=(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =(SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<DirectoryEntry> dlist = new ArrayList();
                for (DirectoryEntry dentry :dfragment.getmDirectories()) {
                    if (dentry.getName().toLowerCase().contains(query.toLowerCase())) {
                        dlist.add(dentry);


                    }
                }
                if (dlist.size()>0) {
                    Log.d("directory test", "" + dfragment.setmDirectories(dlist));
                    dfragment.getDirectoryAdapter().notifyDataSetChanged();
                    searchView.setQuery("", false);
                    searchView.clearFocus();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                dfragment.reset();
                dfragment.getDirectoryAdapter().notifyDataSetChanged();
                return false;
            }
        });
        searchView.clearFocus();
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
