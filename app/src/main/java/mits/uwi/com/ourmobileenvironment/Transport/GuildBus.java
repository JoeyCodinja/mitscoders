package mits.uwi.com.ourmobileenvironment.Transport;

import com.orm.dsl.Ignore;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rox on 6/14/2016.
 */
public class GuildBus extends  Transport {
    @Ignore
    private ArrayList<String> RouteList;
    private String cost;
    private String departure_time;
    private String route;
    private String SerialisedRoute;
    private String route_name;


    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }




    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }



    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void SerialiseList(){
        SerialiseRoute();

    }

    public void DeserialiseList(){
        DeserialiseRoute();

    }

    @Override
    public String getSearchField(){
        return getRoute_name();
    }

    public void SerialiseRoute(){
        this.SerialisedRoute= android.text.TextUtils.join(",",this.RouteList);
    }

    public void DeserialiseRoute(){
        String[] route=android.text.TextUtils.split(this.SerialisedRoute,",");
        this.RouteList =new ArrayList<>(Arrays.asList(route));

    }



}
