package mits.uwi.com.ourmobileenvironment.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers.GlobalRequestHandler;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.EateriesMapActivity;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.MenuDataPasser;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.Restaurant;

/**
 * Created by Danuel on 18/8/2016.
 */
public class EateriesAdapter extends RecyclerView.Adapter<EateriesAdapter.EateriesViewHolder> {

    private ArrayList<Restaurant> eateries;
    private Context callingContext;
    EateriesViewHolder pvh;


    public EateriesAdapter(ArrayList<Restaurant> eateries, Context context){
        this.eateries = new ArrayList<>(eateries);
        callingContext = context;
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
        eateriesViewHolder.eateriesName.setText(
                eateries.get(i).getName());
        eateriesViewHolder.eateriesLocation.setText(
                eateries.get(i).getLocation());
        eateriesViewHolder.eateriesHours.setText(
                eateries.get(i).getBusinessHours());
        Restaurant restaurant=eateries.get(i);
        ImageLoader mImageLoader = GlobalRequestHandler
                .getInstance(callingContext).getImageLoader();
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
                        if(input.equals(eateries.get(i).getName()))
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
                        if(input.equals(eateries.get(i).getName()))
                            eateries.get(i).setFav();
                    }
                }
            });

            locationIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {
                    input = eateriesName.getText().toString();
                    double x,y;
                    Context context = itemView.getContext();
                    //MenuDataPasser.getInstance().setResName(input);
                    for (int i =0 ; i <eateries.size(); i++){
                        if(input.equals(eateries.get(i).getName())) {
                            x = eateries.get(i).getCoordx();
                            y = eateries.get(i).getCoordy();
                            Intent mapIntent = new Intent(context,
                                                          EateriesMapActivity.class);
                            mapIntent.putExtra("ICON",eateries.get(i).getPhoto());
                            mapIntent.putExtra("NAME",eateries.get(i).getName());
                            mapIntent.putExtra("LATITUDE", x);
                            mapIntent.putExtra("LONGTITUDE", y);
                            mapIntent.putExtra("HOURS",eateries.get(i).getBusinessHours());
                            context.startActivity(mapIntent);
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