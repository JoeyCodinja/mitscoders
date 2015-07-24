package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by Danuel on 17/06/2015.
 */
public class EateriesFragment extends Fragment {
    public EateriesFragment() {
        // Required empty public constructor
    }
    private static List<Eateries_list> eateries;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.activity_eateries, container, false);
        RecyclerView recList =(RecyclerView)v.findViewById(R.id.rv);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        initializeData();
        RVAdapter eateriesadp =new RVAdapter(EateriesFragment.getEatList());
        recList.setAdapter(eateriesadp);

        return  v;
    }

    public void initializeData(){
        eateries = new ArrayList<>();
        eateries.add(new Eateries_list("Kentucky Fried Chicken", "Ring Road, Chancellor Hall", "9am-12am", R.drawable.kfc));
        eateries.add(new Eateries_list("Juici Patties", "Faculty of Science and Technology", "7am-7pm", R.drawable.juici));
        eateries.add(new Eateries_list("Nardo's Snack Shop", "Humanities and Education", "6am-3am", R.drawable.nardo));
    }

    public static List<Eateries_list> getEatList() {
        return eateries;
    }

}
