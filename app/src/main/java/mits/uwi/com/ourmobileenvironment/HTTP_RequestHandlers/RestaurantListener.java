package mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.Transport.TransportFragment;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.EateriesFragment;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.Restaurant;

/**
 * Created by rox on 2/14/16.
 */
public class RestaurantListener implements Response.Listener<JSONObject> {
    private String listname;
    private JSONArray restuarantList;
    private Toast internalError;
    private Restaurant currentRes;
    private ArrayList resList;
    private Class<Restaurant> restaurantClass;
    private EateriesFragment eateriesFragment;
    private ImageLoader imageLoader;



    public RestaurantListener(String listname,ArrayList<Restaurant> resList,EateriesFragment eateriesFragment,Context resctx,Class<Restaurant> restaurantClass){
        this.listname=listname;
        this.eateriesFragment=eateriesFragment;
        this.internalError=Toast.makeText(resctx, "Internal error occured please restart application", Toast.LENGTH_SHORT);
        this.resList=resList;
        this.restaurantClass=restaurantClass;
        this.imageLoader=imageLoader;
    }

    @Override
    public void onResponse(JSONObject response){
        Gson gson=new Gson();
        String status;
        EateriesFragment.EateriesAdapter adapter=eateriesFragment.getAdap();
        try {
            restuarantList=response.getJSONArray(listname);
            status=response.getString("Status");
//            if (status.equals("200")){
//                Deleteall();
//            }
            for (int i=0; i<restuarantList.length();i++){
                currentRes =gson.fromJson(restuarantList.getJSONObject(i).toString(),restaurantClass );
                adapter.Add(currentRes);
                if (status.equals("200")){
                    currentRes.save();
                    Log.d("table not found",currentRes.find(restaurantClass,"name = ?",currentRes.getName()).toString());
                }
            }
            eateriesFragment.refreshView();
            eateriesFragment.getActivity().findViewById(R.id.progress_bar).setVisibility(View.GONE);

        }
        catch (JSONException e){
            Log.d("Restaurant exception",e.toString());
            internalError.show();
        }


    }

    public  void Deleteall(){

        try {
            Restaurant.deleteAll(restaurantClass);
        }

        catch (SQLiteException e){

        }
    }

}
