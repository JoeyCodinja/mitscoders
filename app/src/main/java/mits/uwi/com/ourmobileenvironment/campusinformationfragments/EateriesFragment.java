package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers.GlobalRequestHandler;
import mits.uwi.com.ourmobileenvironment.R;


/**
 * Created by Danuel on 17/06/2015.
 */
public class EateriesFragment extends Fragment {


    private ArrayList<Restaurant> restaurants=new ArrayList<>();
    private EateriesAdapter eateriesAdapter;
    private EateriesFragment instance;



    public EateriesFragment() {
        // Required empty public constructor
    }


    public EateriesAdapter getAdap() {
        return eateriesAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        loadRestaurant();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =inflater.inflate(R.layout.activity_eateries, container, false);
        RecyclerView recList =(RecyclerView)v.findViewById(R.id.rv);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        this.eateriesAdapter =new EateriesAdapter(restaurants);
        recList.setAdapter(eateriesAdapter);
        setRetainInstance(true);

        return  v;
    }

    public void refreshView(){
        eateriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater menuInflater){
        menuInflater.inflate(R.menu.menu_eateries, menu);
        menu.clear();
        final MenuItem item = menu.findItem(R.id.action_search);
    }


    public void loadRestaurant(){
        GlobalRequestHandler.getInstance(this.getActivity()).getRestaurantList(restaurants, this);
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

    public class EateriesAdapter extends RecyclerView.Adapter<EateriesAdapter.EateriesViewHolder> {

        private ArrayList<Restaurant> eateries;
        EateriesViewHolder pvh;


        EateriesAdapter(ArrayList<Restaurant> eateries){
            this.eateries = new ArrayList<>(eateries);
        }

        public void Add(Restaurant restaurant){
            this.eateries.add(restaurant);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public EateriesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_eateries, viewGroup, false);
            pvh = new EateriesViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(EateriesViewHolder eateriesViewHolder, int i) {
            eateriesViewHolder.eateriesName.setText(eateries.get(i).name);
            eateriesViewHolder.eateriesLocation.setText(eateries.get(i).location);
            eateriesViewHolder.eateriesHours.setText(eateries.get(i).businessHours);
            Restaurant restaurant=eateries.get(i);
            ImageLoader mImageLoader = GlobalRequestHandler.getInstance(getActivity()).getImageLoader();
            String url = restaurant.getImgurl();
            mImageLoader.get(url, ImageLoader.getImageListener(eateriesViewHolder.eateriesPhoto,
                    R.drawable.fork48, android.R.drawable
                            .ic_dialog_alert));
            eateriesViewHolder.eateriesPhoto.setImageUrl(url, mImageLoader);
        }

        @Override
        public int getItemCount() {
            return eateries.size();
        }

        public final int getID(int i){
            return i;
        }

        public class EateriesViewHolder extends RecyclerView.ViewHolder {

            public View view;
            String input;
            CardView cv;
            TextView eateriesName;
            TextView eateriesLocation;
            TextView eateriesHours;
            public NetworkImageView eateriesPhoto;
            CheckBox favoriteIcon;
            ImageView locationIcon;



            public NetworkImageView getEateriesPhoto() {
                return eateriesPhoto;
            }

            EateriesViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.taxi_service);
                eateriesName = (TextView)itemView.findViewById(R.id.eateries_name);
                eateriesLocation = (TextView)itemView.findViewById(R.id.eateries_location);
                eateriesHours = (TextView)itemView.findViewById(R.id.eateries_hours);
                eateriesPhoto = (NetworkImageView)itemView.findViewById(R.id.eateries_photo);
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
                        input = eateriesName.getText().toString();
                        double x,y;

                        //MenuDataPasser.getInstance().setResName(input);
                        for (int i =0 ; i <eateries.size(); i++){
                            if(input.equals(eateries.get(i).name)) {
                                x = eateries.get(i).getCoordx();
                                y = eateries.get(i).getCoordy();
                                Toast.makeText(getActivity().getApplicationContext(),""+x+","+y, Toast.LENGTH_SHORT).show();
                                Intent mapIntent = new Intent(getActivity().getApplicationContext(), EateriesMapActivity.class);
                                mapIntent.putExtra("ICON",eateries.get(i).getPhoto());
                                mapIntent.putExtra("NAME",eateries.get(i).getName());
                                mapIntent.putExtra("LATITUDE", x);
                                mapIntent.putExtra("LONGTITUDE", y);
                                mapIntent.putExtra("HOURS",eateries.get(i).getBusinessHours());
                                startActivity(mapIntent);
                            }
                        }
                        // item clicked
                        //locationIcon.setBackgroundResource(R.color.red);
                        //locationIcon.setBackgroundResource(0);
                    }
                });
            }

        }
    }


}
