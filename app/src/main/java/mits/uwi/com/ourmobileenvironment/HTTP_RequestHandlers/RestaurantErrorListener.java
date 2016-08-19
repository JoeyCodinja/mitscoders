package mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.EateriesActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.adapters.EateriesAdapter;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.Restaurant;

/**
 * Created by rox on 2/14/16.
 */
public class RestaurantErrorListener implements Response.ErrorListener {


    private Class<Restaurant> restaurantClass;
    private Toast internetConnection;
    private ArrayList<Restaurant> restaurants;
    private EateriesActivity eateriesActivity;

    public RestaurantErrorListener(Class<Restaurant> restaurantClass, Context mCtx,
                                   EateriesActivity eateriesActivity,
                                   ArrayList<Restaurant> restaurants){
        this.restaurantClass = restaurantClass;
        this.eateriesActivity = eateriesActivity;
        internetConnection = Toast.makeText(mCtx, "Please Check Internet Connection",
                                            Toast.LENGTH_SHORT);
        this.restaurants = restaurants;
    }

    @Override
    public void onErrorResponse(VolleyError error){
        EateriesAdapter adapter;

        List<Restaurant> objlist = Restaurant.listAll(restaurantClass);

       adapter = eateriesActivity.getAdapter();


        if (objlist.isEmpty()){
            internetConnection.show();
        }
        else {
            restaurants.clear();
            for (Restaurant transport:objlist){
                restaurants.add(transport);
                adapter.Add(transport);

            }

            eateriesActivity.refreshView();
            eateriesActivity.findViewById(R.id.progress_bar).setVisibility(View.GONE);

        }
    }
}
