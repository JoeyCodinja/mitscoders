package mits.uwi.com.ourmobileenvironment;

/**
 * Created by rox on 7/6/15.
 */
public class BusRoute {
    private String Departure,Route,Frequency,NextDeparture;

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
}
