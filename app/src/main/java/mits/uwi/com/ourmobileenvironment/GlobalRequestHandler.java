package mits.uwi.com.ourmobileenvironment;

/**
 * Created by rox on 12/21/15.
 */

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.Transport.BusRoute;


public class GlobalRequestHandler {
    private static GlobalRequestHandler mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;
    private JSONArray shuttleRoutes;
    ArrayList<BusRoute> ShuttleList= new ArrayList<>();



    private GlobalRequestHandler(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized GlobalRequestHandler getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new GlobalRequestHandler(context.getApplicationContext());
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void getShuttleRoutes(final ArrayList<BusRoute> BusRouteList)  {
        String url="http://10.0.2.2:5000/service/shuttle_routes";
        JsonObjectRequest shuttleRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response)  {
                        try {
                            shuttleRoutes=response.getJSONArray("Shuttlelist");
                            JSONObject TempRouteList;
                            for (int i =0; i<shuttleRoutes.length();i++){
                                TempRouteList=shuttleRoutes.getJSONObject(i);
                                ShuttleList.add(new BusRoute(
                                        TempRouteList.getString("frequency"),
                                        TempRouteList.getString("operationTimes"),
                                        TempRouteList.getString("route"),
                                        TempRouteList.getJSONArray("routeDescrpition")
                                ));
                                BusRouteList.clear();
                                BusRouteList.addAll(ShuttleList);



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
                mInstance.addToRequestQueue(shuttleRequest);



    }
}

