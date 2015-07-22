package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 17/06/2015.
 */
public class EateriesFragment extends AppCompatActivity {
    private List<Eateries_list> eateries;
    private RecyclerView rv;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_eateries, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_eateries);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }


    private void initializeData(){
        eateries = new ArrayList<>();
        eateries.add(new Eateries_list("KFC", "Ring Road, Chancellor Hall", "9am-12am", R.drawable.kfc));
        eateries.add(new Eateries_list("Juici", "Faculty of Science and Technology","7am-7pm", R.drawable.juici));
        eateries.add(new Eateries_list("Nardo's Snack Shop", "Humanities and Education", "6am-3am", R.drawable.nardo));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(eateries);
        rv.setAdapter(adapter);
    }
}