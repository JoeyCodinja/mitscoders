/**
 *
 */
package mits.uwi.com.ourmobileenvironment.ourvle.fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.adapters.CourseContentAdapter;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.helpers.RecyclerItemClickListener;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.models.CourseModule;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.models.CourseSection;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.models.ModuleContent;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.models.SiteInfo;
import mits.uwi.com.ourmobileenvironment.ourvle.classes.tasks.CourseContentsTask;

/**
 * @author Javon Davis
 */
public class CourseContentsFragment extends Fragment {

    private Listener mListener;
    private Activity mActivity;
    private int _id;
    private ProgressBar progressBar;
    private TextView emptyView;
    private Menu menu;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private final ArrayList<CourseModule> moduleList = new ArrayList<>();
    private ArrayList<CourseModule> originalModuleList = new ArrayList<>(); //used in search
    private String token;


    public static CourseContentsFragment newInstance(int courseId) {
        final CourseContentsFragment f = new CourseContentsFragment();
        f.set_id(courseId);
        return f;
    }



    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        try {
            mListener = (Listener) activity;
        } catch (final ClassCastException e) {
            mListener = new Listener() {

                @Override
                public void onCourseModuleSelected(final CourseModule module) {
                    Toast.makeText(mActivity,
                                   "Selected " + module.getName(), Toast.LENGTH_LONG)
                         .show();
                }
            };
        }

    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);

        List<CourseSection> sections = CourseSection.find(CourseSection.class,
                "courseid = ?", get_id() + "");

        // Add modules to sections
        List<CourseModule> dbModules;
        List<ModuleContent> dbContents;
        for (int i = 0; i < sections.size(); i++) {
            dbModules = CourseModule.find(CourseModule.class, "parentid = ?",
                    sections.get(i).getId() + "");

            // Set module contents to modules
            for (int j = 0; j < dbModules.size(); j++) {
                dbContents = ModuleContent.find(
                        ModuleContent.class, "parentid = ?", dbModules
                                .get(j).getId() + "");
                dbModules.get(j).setContents(dbContents);
            }

            sections.get(i).setModules(dbModules);
        }

        ArrayList<CourseModule> modules;
        for (CourseSection section: sections) {
            modules = section.getModules();
            if (modules.size() > 0) {

                // Add modules
                for (CourseModule module:modules) {
                    moduleList.add(module);
                }
            }
        }
       // mEmptyListString = getString(R.string.no_course_contents);
        originalModuleList.addAll(moduleList);
        setHasOptionsMenu(true);

    }

    @Override
    public void onStart() {
        super.onStart();

        //addSearchOption();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_course_contents, menu);
        this.menu = menu;
        addSearchOption();
        setRetainInstance(true);
    }

    public void addSearchOption()
    {
        //add search button to menu
        MenuItem item = menu.add("Search");

        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        SearchView searchView = new SearchView(getActivity());

        searchView.setOnQueryTextListener(new SearchListener());
        item.setActionView(searchView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.synchronize:
                new CourseContentsSyncTask(token,get_id()).execute();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course_contents,
                container, false);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.contentList);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        emptyView = (TextView) view.findViewById(R.id.emptyText);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CourseContentAdapter(getActivity(),moduleList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mListener.onCourseModuleSelected(moduleList.get(position));
            }
        }));

        token = SiteInfo.listAll(SiteInfo.class).get(0).getToken();

        if(moduleList.isEmpty())
            new CourseContentsSyncTask(token,get_id()).execute();

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    /* ======================== Private CLasses ====================== */
    private class SearchListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextChange(String query)
        {
            List<CourseModule> filteredModules = new ArrayList<>();

            for (CourseModule courseModule : originalModuleList)
                if (courseModule.getName().toLowerCase().contains(query.toLowerCase()))
                    filteredModules.add(courseModule);



            moduleList.clear();


            moduleList.addAll(filteredModules);

            mAdapter.notifyDataSetChanged();

            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String s) {

            return false;
        }


    }


    private class CourseContentsSyncTask extends AsyncTask<String, Integer, Boolean> {
        CourseContentsTask contentsTask;
        int courseid;
        Boolean syncStatus;

        public CourseContentsSyncTask(String token, int courseid) {
            contentsTask = new CourseContentsTask(token);
            this.courseid = courseid;
        }

        @Override
        protected void onPreExecute() {
            emptyView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Boolean doInBackground(String... params) {
            Log.d("Background execute", "course contents");
            CourseModule.deleteAll(CourseModule.class);
            syncStatus = contentsTask.syncCourseContents(courseid);
            ArrayList<CourseSection> sections = contentsTask.getCourseContents(courseid);

            if (sections == null)
                return false;

            // To avoid duplicates in listing
            moduleList.clear();

            //CourseSection section;
            ArrayList<CourseModule> modules;
            for (CourseSection section:sections) {
                modules = section.getModules();
                if (modules.size() > 0) {

                    // Add modules
                    for (CourseModule module:modules) {
                        moduleList.add(module);
                    }
                }
            }

            return syncStatus;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mAdapter.notifyDataSetChanged();
            originalModuleList.addAll(moduleList);
            progressBar.setVisibility(View.GONE);
            if(mAdapter.getItemCount()==0)
            {
                mRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            }
            else
                mRecyclerView.setVisibility(View.VISIBLE);
        }

    }



    /* ========================== Interfaces ======================= */

    public interface Listener {
        void onCourseModuleSelected(CourseModule module);
    }
}
