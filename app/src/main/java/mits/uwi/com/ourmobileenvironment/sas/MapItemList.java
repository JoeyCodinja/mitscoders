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
        mItems.add(new MapItem('C',"Seminar Room 5 (SR5)",-76.74722103 ,18.00708286));
        mItems.add(new MapItem('C',"Seminar Room 6 (SR6)",-76.74717661,18.00713045));
        mItems.add(new MapItem('C',"Seminar Room 8 (SR)",-76.74713378,18.00716852));
        mItems.add(new MapItem('C',"Seminar Room 10 (SR10)",-76.74809032,18.00648165));
        mItems.add(new MapItem('C'," Seminar Room 11 (SR11)",-76.74813791,18.00632461));
        mItems.add(new MapItem('C',"Seminar Room 12 (SR12)", -76.7480998,18.00636268));
        mItems.add(new MapItem('C',"Seminar Room 16 (SR16)",-76.74792852,18.00631826));
        mItems.add(new MapItem('C'," Law Seminar Room 1",-76.74856503,18.00807351));
        mItems.add(new MapItem('C'," Confucius Multipurpose Room",-76.74639496,18.00234853));
        mItems.add(new MapItem('C',"Pathology Seminar Room",-76.74432483,18.01193775));
        mItems.add(new MapItem('C',"Law Seminar Room 2",-76.74854837,18.00802592));
        mItems.add(new MapItem('C',"C10",-76.74945217,18.00399511));
        mItems.add(new MapItem('C',"Chem/Phys",-76.74910318,18.00435362));
        mItems.add(new MapItem('C',"Chemistry Lecture Theatre 5 (C5)",-76.75005497,18.00442659));
        mItems.add(new MapItem('C'," Chemistry Lecture Theatre 2 (C2)",-76.74985192,18.00424892));
        mItems.add(new MapItem('C',"Chemistry Lecture Theatre 3 (C3)",-76.74974722,18.00437583));
        mItems.add(new MapItem('C',"Science Lecture Theatre	",-76.74995661	,18.00517215));
        mItems.add(new MapItem('C',"Pre-Clinical Lecture Theatre",-76.74969963,18.00517215));
        mItems.add(new MapItem('C',"Mathematics Lecture Theatre",-76.74956004,18.00485806));
        mItems.add(new MapItem('C'," Mathematics Lecture Theatre 2 (MLT2)",	-76.74945217,18.004912));
        mItems.add(new MapItem('C'," Mathematics Lecture Theatre 3 (MLT3)",-76.74936016,18.00497228));
        mItems.add(new MapItem('C',"Physics A Lecture Theatre",-76.74884303,18.00488662));
        mItems.add(new MapItem('C',"Physics C Lecture Theatre",-76.7489382,18.0050167));
        mItems.add(new MapItem('C',"Physics B Lecture Theatre",-76.74903656,18.00516898));
        mItems.add(new MapItem('C',"Biology Lecture Theatre",-76.75043568,18.00631429));
        mItems.add(new MapItem('C',"Room 3",-76.74585998,18.0058043));
        mItems.add(new MapItem('C',"Neville Hall Lecture Theatre (N1)",-76.74606223,18.00485251));
        mItems.add(new MapItem('C',"N4",-76.74611934,18.00486441));
        mItems.add(new MapItem('C',"New Education Lecture Theatre (NELT)",-76.7467023,18.00568294));
        mItems.add(new MapItem('C',"Interfaculty Lecture Theatre (IFLT)",-76.74911904,18.00549259));
        mItems.add(new MapItem('C',"SLT",-76.74575528,18.0052713));
        mItems.add(new MapItem('C',"Social Sciences Lecture Theatre (SSLT)",-76.74739235,18.00663552));
        mItems.add(new MapItem('C',"Main Medical Lecture Theatre",-76.74548878,18.01200121));
        mItems.add(new MapItem('C',"Medical 2",-76.74521118,18.01211621));
        mItems.add(new MapItem('C',"UWISON Room 4",-76.74419594,18.00379603));
        mItems.add(new MapItem('C',"UWISON Room 3",-76.74431095,18.00388328));
        mItems.add(new MapItem('C',"NLT 1",-76.74441802,18.00403398));
        mItems.add(new MapItem('C',"NLT 2",	-76.74459648,18.00385552));
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
