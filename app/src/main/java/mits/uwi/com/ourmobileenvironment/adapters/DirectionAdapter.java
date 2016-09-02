package mits.uwi.com.ourmobileenvironment.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.jar.Attributes;
import java.util.zip.Inflater;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 27/8/2016.
 */
public class DirectionAdapter implements ListAdapter{
    ArrayList<ArrayList<String>> locations;

    public DirectionAdapter(ArrayList<ArrayList<String>> locations){
        this.locations = locations;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        if (areAllItemsEnabled()){
            return true;
        }
        else
            return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public Object getItem(int position) {
        return locations.get(position);
    }


    public String getItemLocationName(int position){
        ArrayList<String>item = (ArrayList<String>)getItem(position);
        return item.get(0);
    }

    public String getItemLocationCoords(int position){
        ArrayList<String> item = (ArrayList<String>)getItem(position);
        return item.get(1);
    }

    public String getItemLocationCategory(int position){
        ArrayList<String>item = (ArrayList<String>)getItem(position);
        return item.get(2);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listView = inflater.inflate(R.layout.directions_item, parent, false);
        TextView locationName =
                (TextView)listView.findViewById(R.id.locationName);
        locationName.setText(getItemLocationName(position));

        return listView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }
}
