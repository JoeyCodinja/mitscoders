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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by Danuel on 17/06/2015.
 */
public class EateriesFragment extends Fragment implements View.OnClickListener{

    public EateriesFragment() {
        // Required empty public constructor
    }
    static List<Eateries_list> eateries;
    static List<Menu_Item> menu;
    static List<Menu_Item> j_menu;
    static List<Menu_Item> y_menu;
    static List<Menu_Item> b_menu;
    static List<Menu_Item> n_menu;

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
            menu = new ArrayList<>();
            menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo", "Meal Deal", "2pc Chicken 1 Reg fries 1 500ml Soda", "$ 100 JMD"));
            menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo", "Meal Deal", "2pc Chicken 1 Reg fries 1 500ml Soda", "$ 100 JMD"));
            menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo", "Meal Deal", "2pc Chicken 1 Reg fries 1 500ml Soda", "$ 100 JMD"));
            menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo", "Meal Deal", "2pc Chicken 1 Reg fries 1 500ml Soda", "$ 100 JMD"));
            j_menu = new ArrayList<>();
            j_menu.add(new Menu_Item("Juici Patties", "Classic Combo", "Patties", "2pc Patty 1 500ml Soda", "$ 150 JMD"));
            j_menu.add(new Menu_Item("Juici Patties", "Classic Combo", "Patties", "2pc Patty 1 500ml Soda", "$ 150 JMD"));
            j_menu.add(new Menu_Item("Juici Patties", "Classic Combo", "Patties", "2pc Patty 1 500ml Soda", "$ 150 JMD"));
            j_menu.add(new Menu_Item("Juici Patties", "Classic Combo", "Patties", "2pc Patty 1 500ml Soda", "$ 150 JMD"));
            y_menu = new ArrayList<>();
            y_menu.add(new Menu_Item("y", "Classic Combo", "Chinese", "2pc Chinese 1 500ml Soda", "$ 150 JMD"));
            y_menu.add(new Menu_Item("y", "Classic Combo", "Chinese", "2pc Chinese 1 500ml Soda", "$ 150 JMD"));
            y_menu.add(new Menu_Item("y", "Classic Combo", "Chinese", "2pc Chinese 1 500ml Soda", "$ 150 JMD"));
            y_menu.add(new Menu_Item("y", "Classic Combo", "Chinese", "2pc Chinese 1 500ml Soda", "$ 150 JMD"));
            b_menu = new ArrayList<>();
            b_menu.add(new Menu_Item("y", "Classic Combo", "Sweet and Sour", "2pc Sweet and Sour 1 500ml Soda", "$ 150 JMD"));
            b_menu.add(new Menu_Item("y", "Classic Combo", "Sweet and Sour", "2pc Sweet and Sour 1 500ml Soda", "$ 150 JMD"));
            b_menu.add(new Menu_Item("y", "Classic Combo", "Sweet and Sour", "2pc Sweet and Sour 1 500ml Soda", "$ 150 JMD"));
            b_menu.add(new Menu_Item("y", "Classic Combo", "Sweet and Sour", "2pc Sweet and Sour 1 500ml Soda", "$ 150 JMD"));
            n_menu = new ArrayList<>();
            n_menu.add(new Menu_Item("y", "Classic Combo", "Corndog", "2pc Corndog 1 500ml Soda", "$ 150 JMD"));
            n_menu.add(new Menu_Item("y", "Classic Combo", "Corndog", "2pc Corndog 1 500ml Soda", "$ 150 JMD"));
            n_menu.add(new Menu_Item("y", "Classic Combo", "Corndog", "2pc Corndog 1 500ml Soda", "$ 150 JMD"));
            n_menu.add(new Menu_Item("y", "Classic Combo", "Corndog", "2pc Corndog 1 500ml Soda", "$ 150 JMD"));
            eateries = new ArrayList<>();
            eateries.add(new Eateries_list("Kentucky Fried Chicken", "Ring Rd, Chancellor Hall", "Opens 9am-12am", R.drawable.kfc, (ArrayList<Menu_Item>) menu));
            eateries.add(new Eateries_list("Juici Patties", "Faculty of Science and Technology", "Opens 6am-7pm", R.drawable.juici,(ArrayList<Menu_Item>) j_menu));
            eateries.add(new Eateries_list("Yao Chinese Restaurant", "Students Union", "Opens 9am-10pm", R.drawable.yao, (ArrayList<Menu_Item>) y_menu));
            eateries.add(new Eateries_list("BeeHive", "Ring Rd, Humanities and Education", "Opens 7am-6pm", R.drawable.beehive, (ArrayList<Menu_Item>) b_menu));
            eateries.add(new Eateries_list("Nardo's Snack Shop", "Humanities and Education", "Opens 6am-3am", R.drawable.nardo, (ArrayList<Menu_Item>) n_menu));
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
            String input;
            CardView cv;
            TextView eateriesName;
            TextView eateriesLocation;
            TextView eateriesHours;
            ImageView eateriesPhoto;
            CheckBox favoriteIcon;
            ImageView locationIcon;
            //ImageView clickedIcon;

            EateriesViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.cv);
                eateriesName = (TextView)itemView.findViewById(R.id.eateries_name);
                eateriesLocation = (TextView)itemView.findViewById(R.id.eateries_location);
                eateriesHours = (TextView)itemView.findViewById(R.id.eateries_hours);
                 eateriesPhoto = (ImageView)itemView.findViewById(R.id.eateries_photo);
                favoriteIcon = (CheckBox)itemView.findViewById(R.id.favorite_icon);
                locationIcon = (ImageView)itemView.findViewById(R.id.location_icon);

                //favoriteIcon.setColorFilter(R.color.grey, PorterDuff.Mode.DST_OUT);
                locationIcon.setColorFilter(R.color.grey, PorterDuff.Mode.DST_OUT);
                cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View itemView) {
                        // item clicked
                        //cv.setCardBackgroundColor(255);
                        //id = getResources().getIdentifier(String.valueOf(eateriesPhoto), "id", null);
                       //id = getResources().getIdentifier(String.valueOf((ImageView)itemView.findViewById(R.id.eateries_photo)), "id", null);
                        input = eateriesName.getText().toString();
                        //MenuDataPasser.getInstance().setResName(input);
                        for (int i =0 ; i <eateries.size(); i++){
                            if(input.equals(eateries.get(i).name))
                                MenuDataPasser.getInstance().setResName(eateries.get(i));
                        }
                        //showDialog();
                    }
                });

                favoriteIcon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                        input = eateriesName.getText().toString();
                        //MenuDataPasser.getInstance().setResName(input);
                        for (int i =0 ; i <eateries.size(); i++){
                            if(input.equals(eateries.get(i).name))
                                eateries.get(i).setFav();
                        }
                    }
                });

                locationIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View itemView) {
                            // item clicked
                            //locationIcon.setBackgroundResource(R.color.red);
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
           // MenuDataPasser.getInstance().setResName(eateries.get(i));
           //MenuDataPasser.getInstance().setResPhoto(eateries.get(i).photoId);
        }

        @Override
        public int getItemCount() {
            return eateries.size();
        }

        public final int getID(int i){
            return i;
        }
    }

    public void showDialog(){
        DialogFragment newFragment = new MenuFragment();
        newFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        newFragment.show(getChildFragmentManager(), "dialog");
    }
}
