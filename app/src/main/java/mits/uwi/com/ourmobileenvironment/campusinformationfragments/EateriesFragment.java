package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.MenuFragment;
import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by Danuel on 17/06/2015.
 */
public class EateriesFragment extends Fragment implements View.OnClickListener{
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
        eateries.add(new Eateries_list("Kentucky Fried Chicken", "Ring Rd, Chancellor Hall", "Opens 9am-12am", R.drawable.kfc));
        eateries.add(new Eateries_list("Juici Patties", "Faculty of Science and Technology", "Opens 6am-7pm", R.drawable.juici));
        eateries.add(new Eateries_list("Yao Chinese Restaur6ant", "Students Union", "Opens 9am-10pm", R.drawable.yao));
        eateries.add(new Eateries_list("BeeHive", "Ring Rd, Humanities and Education", "Opens 7am-6pm", R.drawable.beehive));
        eateries.add(new Eateries_list("Nardo's Snack Shop", "Humanities and Education", "Opens 6am-3am", R.drawable.nardo));
    }

    public static List<Eateries_list> getEatList() {
        return eateries;
    }

    @Override
    public void onClick(View v) {

    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.EateriesViewHolder> {

        public class EateriesViewHolder extends RecyclerView.ViewHolder {

            public View view;
            CardView cv;
            TextView eateriesName;
            TextView eateriesLocation;
            TextView eateriesHours;
            ImageView eateriesPhoto;
            ImageView favoriteIcon;
            ImageView locationIcon;

            EateriesViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.cv);
                eateriesName = (TextView)itemView.findViewById(R.id.eateries_name);
                eateriesLocation = (TextView)itemView.findViewById(R.id.eateries_location);
                eateriesHours = (TextView)itemView.findViewById(R.id.eateries_hours);
                eateriesPhoto = (ImageView)itemView.findViewById(R.id.eateries_photo);
                favoriteIcon = (ImageView)itemView.findViewById(R.id.favorite_icon);
                locationIcon = (ImageView)itemView.findViewById(R.id.location_icon);

                favoriteIcon.setColorFilter(R.color.grey, PorterDuff.Mode.DST_OUT);
                locationIcon.setColorFilter(R.color.grey, PorterDuff.Mode.DST_OUT);

                cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View itemView) {
                        // item clicked
                        cv.setCardBackgroundColor(255);
                        showDialog();
                    }
                });
                favoriteIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View itemView) {
                        // item clicked
                        favoriteIcon.setBackgroundResource(R.color.red);
                        //favoriteIcon.setBackgroundResource(0);
                    }
                });
                locationIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View itemView) {
                        // item clicked
                        locationIcon.setBackgroundResource(R.color.red);
                        //locationIcon.setBackgroundResource(0);
                    }
                });
            }
        }

        List<Eateries_list> eateries;

        RVAdapter(List<Eateries_list> eateries){
            this.eateries = eateries;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public EateriesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_eateries, viewGroup, false);
            EateriesViewHolder pvh = new EateriesViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(EateriesViewHolder eateriesViewHolder, int i) {
            eateriesViewHolder.eateriesName.setText(eateries.get(i).name);
            eateriesViewHolder.eateriesLocation.setText(eateries.get(i).location);
            eateriesViewHolder.eateriesHours.setText(eateries.get(i).hours);
            eateriesViewHolder.eateriesPhoto.setImageResource(eateries.get(i).photoId);
        }

        @Override
        public int getItemCount() {
            return eateries.size();
        }
    }

    public void showDialog(){
        DialogFragment newFragment = new MenuFragment();
        newFragment.show(getChildFragmentManager(), "dialog");
    }
}
