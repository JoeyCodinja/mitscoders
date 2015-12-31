package mits.uwi.com.ourmobileenvironment.Transport;


import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by rox on 7/6/15.
 */
public class BusRoute {
    private String OperationTimes,Route,Frequency;
    private JSONArray RouteDescription;

    public static ArrayList<BusRoute> getBusList() {
//        populate();
        return busList;
    }

    private static ArrayList<BusRoute> busList=new ArrayList<>();

    public BusRoute(){}

    public BusRoute(String Frequency,String OperationTimes,String Route,JSONArray RouteDescription){
        this.OperationTimes = OperationTimes;
        this.Route=Route;
        this.Frequency=Frequency;
        this.RouteDescription = RouteDescription;
    }

    public String getOperationTimes() {
        return OperationTimes;
    }

    public void setOperationTimes(String operationTimes) {
        OperationTimes = operationTimes;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String frequency) {
        Frequency = frequency;
    }

    public String getRouteDescription() {

        return RouteDescription.toString();
    }

    public void setRouteDescription(JSONArray routeDescription) {
        RouteDescription = routeDescription;
    }

//    public static void populate(){
//        busList.clear();
//        busList.add(new BusRoute("Liguanea to campus","Departs at 9:30","Every hour","Next departure 8:30"));
//        busList.add(new BusRoute("Liguanea to Hospital","Departs at 10:30","Every hour","Next departure 8:30"));
//        busList.add(new BusRoute("Liguanea to Papine","Departs at 12:30","Every hour","Next departure 8:30"));
//        busList.add(new BusRoute("Liguanea to Mona Road","Departs at 6:30","Every hour","Next departure 8:30"));
//        busList.add(new BusRoute("Liguanea to August Town","Departs at 8:30","Every hour","Next departure 8:30"));
//        busList.add(new BusRoute("Liguanea to Hope Gradens", "Departs at 7:30", "Every hour", "Next departure 8:30"));
//    }
}
