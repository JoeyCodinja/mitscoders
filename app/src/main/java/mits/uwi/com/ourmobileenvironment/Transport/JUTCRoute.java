package mits.uwi.com.ourmobileenvironment.Transport;

import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rox on 1/12/16.
 */
public class JUTCRoute extends Transport {


    @Ignore
    private ArrayList<String> RouteList;
    private String Origin;
    private String Destination;
    private String SerialisedRoute;
    private String RouteNum;



    public ArrayList<String> getRouteList() {
        return RouteList;
    }
    public void setRouteList(ArrayList<String> routeList) {
        RouteList = routeList;
    }

    public String getRouteNum() {
        return RouteNum;
    }

    public String getOrigin() {
        return Origin;
    }

    public String getSerialisedRoute() {
        return SerialisedRoute;
    }

    public String getDestination() {

        return Destination;
    }

    public void SerialiseList(){
        SerialiseRoute();

    }

    public void DeserialiseList(){
        DeserialiseRoute();

    }

    public void SerialiseRoute(){
        this.SerialisedRoute= android.text.TextUtils.join(",",this.RouteList);
    }

    public void DeserialiseRoute(){
        String[] route=android.text.TextUtils.split(this.SerialisedRoute,",");
        this.RouteList =new ArrayList<>(Arrays.asList(route));

    }

    @Override
    public String getSearchField(){
        return getDestination()+","+getOrigin()+","+getRouteNum()+","+getSerialisedRoute();
    }



}
