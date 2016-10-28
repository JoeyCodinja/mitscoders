package mits.uwi.com.ourmobileenvironment.adapters;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.Attributes;
import java.util.zip.Inflater;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 27/8/2016.
 */
public class DirectionAdapter implements ListAdapter{
    ArrayList<ArrayList<String>> locations;

    public DirectionAdapter(Context ctx) throws IOException, XmlPullParserException {
        this.locations = buildLocations(ctx);
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

    public int getItemIndexFromLocationName(String locationName){
        int cnt = 0;
        for (ArrayList<String> location: locations){
            if (location.indexOf(locationName) > -1){
                return cnt;
            }
        }
        return -1;
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

    private ArrayList<ArrayList<String>> buildLocations(Context context)
            throws XmlPullParserException, IOException {
        XmlResourceParser tbt_locations =
                context.getResources().getXml(R.xml.campus_directions_locations);
        locations = new ArrayList<>();
        ArrayList<String> locationNames = new ArrayList<>();
        ArrayList<String> locationCoords = new ArrayList<>();
        ArrayList<String> locationCategory = new ArrayList<>();

        while (tbt_locations.getEventType() != XmlResourceParser.END_DOCUMENT){
            String tag_name = tbt_locations.getName();
            if (tbt_locations.getEventType() == XmlResourceParser.START_TAG){
                switch (tag_name) {
                    case "Name":
                        locationNames.add(tbt_locations.getAttributeValue(0));
                        break;
                    case "Category":
                        locationCategory.add(tbt_locations.getAttributeValue(0));
                        break;
                    case "Coords":
                        String coordinates;
                        coordinates = tbt_locations.getAttributeValue(0) +
                                ',' +
                                tbt_locations.getAttributeValue(1);
                        locationCoords.add(coordinates);
                        break;
                }
            }
            tbt_locations.next();
        }
        tbt_locations.close();


        for(String location: locationNames){
            int index = locationNames.indexOf(location);

            ArrayList<String> locationInfo =
                    new ArrayList<>(
                            Arrays.asList(location,
                                    locationCoords.get(index),
                                    locationCategory.get(index)));
            locations.add(locationInfo);
        }

        return locations;
    }
}
