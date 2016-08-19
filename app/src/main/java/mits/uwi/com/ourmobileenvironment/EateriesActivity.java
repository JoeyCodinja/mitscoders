package mits.uwi.com.ourmobileenvironment;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers.GlobalRequestHandler;
import mits.uwi.com.ourmobileenvironment.adapters.EateriesAdapter;

import mits.uwi.com.ourmobileenvironment.campusinformationfragments.Restaurant;

public class EateriesActivity extends Activity implements AppCompatCallback {

    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private EateriesAdapter eateriesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate appCompatDelegate = AppCompatDelegate.create(this, this);
        appCompatDelegate.onCreate(savedInstanceState);
        appCompatDelegate.setContentView(R.layout.activity_eateries);

        Toolbar toolbar = (Toolbar) findViewById(R.id.eateries_toolbar);
        toolbar.setTitle(R.string.title_activity_eateries);
        appCompatDelegate.setSupportActionBar(toolbar);


        loadRestaurant();

        RecyclerView recList = (RecyclerView)findViewById(R.id.rv);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        this.eateriesAdapter =new EateriesAdapter(restaurants,this);
        recList.setAdapter(eateriesAdapter);

        findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);

        // This call should retain the
        // instance of the fragment
        // even when the fragment
        // has been detached
        // --> setRetainInstance(true);
        // TODO: Research; Relevant? Alternative action within activity
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode){

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode){

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }


    public void loadRestaurant(){
        GlobalRequestHandler.getInstance(this)
                .getRestaurantList(restaurants, this);
    }

    public EateriesAdapter getAdapter(){
        return this.eateriesAdapter;
    }

    public void refreshView(){
        eateriesAdapter.notifyDataSetChanged();
    }



}
