package mits.uwi.com.ourmobileenvironment.sas;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by peoplesoft on 2/10/2016.
 */
public class MapItemList {
    private ArrayList<MapItem> mItems;
    private static MapItemList sMapList;
    private Context mAppContext;

    private MapItemList (Context appContext) {
        mAppContext = appContext;
        mItems = new ArrayList<MapItem>();
        mItems.add(new MapItem('C',"Seminar Room 5 (SR5)",18.00708286,-76.74722103 ));
        mItems.add(new MapItem('C',"Seminar Room 6 (SR6)",18.00713045,-76.74717661));
        mItems.add(new MapItem('C',"Seminar Room 8 (SR)",18.00716852,-76.74713378));
        mItems.add(new MapItem('C',"Pathology Seminar Room",18.01193775,-76.74432483));
        mItems.add(new MapItem('C',"Law Seminar Room 2",18.00802592,-76.74854837));
    }

    public static MapItemList get (Context c){
        if (sMapList == null){
            sMapList = new MapItemList(c.getApplicationContext());
        }
        return sMapList;
    }
    public ArrayList<MapItem> getmMapItems(){
        return mItems;
    }
   /* public MapItem getItem(int Crn){
        for (Course c : mItems){
            if (c.getCRN()== Crn)
                return c;
        }
        return null;
    }*/
}
