package mits.uwi.com.ourmobileenvironment;


import java.util.ArrayList;

/**
 * Created by rox on 7/6/15.
 */
public class BusRoute {
    private String Departure,Route,Frequency,NextDeparture;

    public static ArrayList<BusRoute> getBusList() {
        populate();
        return busList;
    }

    private static ArrayList<BusRoute> busList=new ArrayList<>();

    public BusRoute(){}

    public BusRoute(String Route,String Departure,String Frequency,String NextDeparture ){
        this.Departure=Departure;
        this.Route=Route;
        this.Frequency=Frequency;
        this.NextDeparture=NextDeparture;
    }

    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        Departure = departure;
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

    public String getNextDeparture() {
        return NextDeparture;
    }

    public void setNextDeparture(String nextDeparture) {
        NextDeparture = nextDeparture;
    }

    public static void populate(){
        busList.clear();
        busList.add(new BusRoute("Liguanea to campus","Departs at 9:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Hospital","Departs at 10:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Papine","Departs at 12:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Mona Road","Departs at 6:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to August Town","Departs at 8:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Hope Gradens", "Departs at 7:30", "Every hour", "Next departure 8:30"));
    }
}
