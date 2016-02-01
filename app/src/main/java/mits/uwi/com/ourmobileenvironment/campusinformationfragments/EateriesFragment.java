package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    static List<Eateries_list> eateries_filtered;
    static List<Menu_Item> menu;
    static List<Menu_Item> j_menu;
    static List<Menu_Item> y_menu;
    static List<Menu_Item> b_menu;
    static List<Menu_Item> n_menu;
    String add;

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

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater menuInflater){
        menuInflater.inflate(R.menu.menu_eateries, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
    }



    public void initializeData() {
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
        eateries_filtered = new ArrayList<>();
        eateries = new ArrayList<>();
        eateries_filtered.add(new Eateries_list("Kentucky Fried Chicken", "Ring Rd, Chancellor Hall", "Opens 9am-12am", R.drawable.kfc, (ArrayList<Menu_Item>) menu, "Fast Food"));
        eateries_filtered.add(new Eateries_list("Juici Patties", "Faculty of Science and Technology", "Opens 6am-7pm", R.drawable.juici, (ArrayList<Menu_Item>) j_menu, "Fast Food"));
        eateries_filtered.add(new Eateries_list("Yao Chinese Restaurant", "Students Union", "Opens 9am-10pm", R.drawable.yao, (ArrayList<Menu_Item>) y_menu, "Restaurant"));
        eateries_filtered.add(new Eateries_list("BeeHive", "Ring Rd, Humanities and Education", "Opens 7am-6pm", R.drawable.beehive, (ArrayList<Menu_Item>) b_menu, "Stall"));
        eateries_filtered.add(new Eateries_list("Nardo's Snack Shop", "Humanities and Education", "Opens 6am-3am", R.drawable.nardo, (ArrayList<Menu_Item>) n_menu, "Restaurant"));

        add = "stall";

        for (int i = 0; i < eateries_filtered.size(); i++) {
            if (add == "fast") {
                if (eateries_filtered.get(i).catergory.equals("Fast Food")) {
                    eateries.add(eateries_filtered.get(i));
                }
            }
           else if(add == "res"){
               if(eateries_filtered.get(i).catergory.equals("Restaurant")){
                   eateries.add(eateries_filtered.get(i));
               }
           }
            else if(add == "caf"){
                if(eateries_filtered.get(i).catergory.equals("Cafeteria")){
                    eateries.add(eateries_filtered.get(i));
                }
            }
            else if(add == "stall"){
                if(eateries_filtered.get(i).catergory.equals("Stall")){
                    eateries.add(eateries_filtered.get(i));
                }
            }
            else
               eateries.add(eateries_filtered.get(i));
        }
    }



    public static List<Eateries_list> getEatList() {
        return eateries;
    }


    @Override
    public void onClick(View v) {

    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                        int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
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

            EateriesViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.taxi_service);
                eateriesName = (TextView)itemView.findViewById(R.id.eateries_name);
                eateriesLocation = (TextView)itemView.findViewById(R.id.eateries_location);
                eateriesHours = (TextView)itemView.findViewById(R.id.eateries_hours);
                eateriesPhoto = (ImageView)itemView.findViewById(R.id.eateries_photo);
                favoriteIcon = (CheckBox)itemView.findViewById(R.id.favorite_icon);
                locationIcon = (ImageView)itemView.findViewById(R.id.location_icon);


                locationIcon.setColorFilter(R.color.grey, PorterDuff.Mode.DST_OUT);
                cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View itemView) {
                        input = eateriesName.getText().toString();
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
//            eateriesViewHolder.eateriesPhoto.setImageResource(eateries.get(i).photoId);
            eateriesViewHolder.eateriesPhoto.setImageBitmap(
                        decodeSampledBitmapFromResource(getResources(), eateries.get(i).photoId, 100, 100));
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
