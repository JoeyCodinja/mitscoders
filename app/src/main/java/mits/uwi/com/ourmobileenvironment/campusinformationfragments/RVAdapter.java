package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.EateriesViewHolder> {

    public static class EateriesViewHolder extends RecyclerView.ViewHolder {

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
                    /*FragmentManager fm = getActivity().getSupportFragmentManager();
                    android.support.v4.app.Fragment fragment;
                    fragment = new EateriesFragment();
                    fm.beginTransaction().replace(R.id.campusinfo_fragmentContainer, fragment)
                            .addToBackStack(null)
                            .commit();*/
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