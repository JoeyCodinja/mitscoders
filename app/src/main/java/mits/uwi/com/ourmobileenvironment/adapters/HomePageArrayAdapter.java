package mits.uwi.com.ourmobileenvironment.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 31/7/2015.
 */
public class HomePageArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public HomePageArrayAdapter(Context context, String[] values){
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.nav_drawer_list_item, parent, false);

        ImageView nav_image = (ImageView)rowView.findViewById(R.id.nav_list_image);
        TextView nav_title = (TextView)rowView.findViewById(R.id.nav_list_title);
        nav_title.setText(values[position]);
        switch(values[position]){
            case "E-mail":
                //Email
                nav_image.setImageResource(R.drawable.ic_email_grey_24px);
                break;
            case "Notifications":
                //Notifications
                nav_image.setImageResource(R.drawable.ic_notifications_grey_24px);
                break;
            case "Directory":
                //Directory
                nav_image.setImageResource(R.drawable.ic_contact_phone_grey_24px);
                break;
            case "Teaching Evaluations":
                break;
            case "Wi-Fi Finder":
                //Wi-Fi Finder
                nav_image.setImageResource(R.drawable.ic_wifi_grey_24px);
                break;
            case "Emergency Services":
                //Emergency Services
                nav_image.setImageResource(R.drawable.ic_announcement_grey_24px);
                break;
            case "Student Administrative Services":
                nav_image.setImageResource(R.drawable.sas);
                break;
            case "Our Virtual Learning Environment (OurVLE)":
                nav_image.setImageResource(R.drawable.ourvle);
                break;
            case "Transportation":
                nav_image.setImageResource(R.drawable.transportation);
                break;
            case "Campus Information":
                nav_image.setImageResource(R.drawable.campusinfo);
                break;
            case "Eateries":
                nav_image.setImageResource(R.drawable.eateries);
                break;
        }

        return rowView;
    }
}
