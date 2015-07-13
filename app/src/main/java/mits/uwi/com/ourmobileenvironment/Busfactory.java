package mits.uwi.com.ourmobileenvironment;

import java.util.ArrayList;

/**
 * Created by rox on 7/9/15.
 */
public class Busfactory {
    private static ArrayList<BusRoute> busList=new ArrayList<>();

    public Busfactory(){


    }

    public static void populate(){
        busList.add(new BusRoute("Liguanea to campus","Departs at 9:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Hospital","Departs at 10:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Papine","Departs at 12:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Mona Road","Departs at 6:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to August Town","Departs at 8:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Hope Gradens", "Departs at 7:30", "Every hour", "Next departure 8:30"));
    }

    public static ArrayList<BusRoute> getBusList(){
        return busList;
    }
}
