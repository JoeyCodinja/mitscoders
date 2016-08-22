package mits.uwi.com.ourmobileenvironment.Transport;

import com.orm.dsl.Ignore;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rox on 6/14/2016.
 */
public class GuildBus extends  Transport {

    @Ignore
    private ArrayList<String> routelist;
    private String cost;
    private String departure_time;
    private String route;
    private String SerialisedRoute;
    private String routetitle;


    public String getRoutetitle() {
        return routetitle;
    }

    public void setRoute_name(String route_name) {
        this.routetitle = route_name;
    }

    public ArrayList<String> getRoutelist() {
        return routelist;
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
        return getRoutetitle();
    }

    public void SerialiseRoute(){
        this.SerialisedRoute= android.text.TextUtils.join(",",this.routelist);
    }

    public void DeserialiseRoute(){
        String[] route=android.text.TextUtils.split(this.SerialisedRoute,",");
        this.routelist =new ArrayList<>(Arrays.asList(route));

    }



}
