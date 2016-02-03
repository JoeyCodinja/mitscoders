package mits.uwi.com.ourmobileenvironment.Transport;



import android.util.Log;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rox on 7/6/15.
 */
public class BusRoute extends Transport{
    private String OperationTimes,Route,Frequency;

    @Ignore
    private ArrayList<String> RouteDescription;

    private  String SerialisedDescription;

    public static List<BusRoute> getBusList() {

        return BusRoute.listAll(BusRoute.class);
    }



    public BusRoute(){}

    public BusRoute(String Frequency,String OperationTimes,String Route,ArrayList<String> RouteDescription){
        this.OperationTimes = OperationTimes;
        this.Route=Route;
        this.Frequency=Frequency;
        this.RouteDescription = RouteDescription;
    }


    public void SerialiseRouteDesc(){
        this.SerialisedDescription= android.text.TextUtils.join(",",RouteDescription);
    }

    public void DeserialiseRouteDesc(){
        String[] desc=android.text.TextUtils.split(SerialisedDescription,",");
        this.RouteDescription = new ArrayList<>(Arrays.asList(desc));
    }

    public void DeserialiseList(){
        DeserialiseRouteDesc();
    }

    public void SerialiseList(){
        SerialiseRouteDesc();
    }

    public String getOperationTimes() {
        return OperationTimes;
    }


    public String getRoute() {
        return Route;
    }



    public String getFrequency() {
        return Frequency;
    }


    public String getSerialisedDescription() {
        return SerialisedDescription;
    }


    public ArrayList<String> getRouteDescription() {
        return RouteDescription;
    }

    @Override
    public String getSearchField(){
        return getRoute();
    }



}
