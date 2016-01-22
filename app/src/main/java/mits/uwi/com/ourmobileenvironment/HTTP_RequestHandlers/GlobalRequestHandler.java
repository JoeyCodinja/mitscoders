package mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers;

/**
 * Created by rox on 12/21/15.
 */

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mits.uwi.com.ourmobileenvironment.Transport.BusRoute;
import mits.uwi.com.ourmobileenvironment.Transport.BusScheduleFragment;
import mits.uwi.com.ourmobileenvironment.Transport.JUTCRoute;
import mits.uwi.com.ourmobileenvironment.Transport.JUTCRouteFragment;
import mits.uwi.com.ourmobileenvironment.Transport.TaxiService;
import mits.uwi.com.ourmobileenvironment.Transport.TaxiServiceFragment;
import mits.uwi.com.ourmobileenvironment.Transport.Transport;


public class GlobalRequestHandler {
    private static GlobalRequestHandler mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;
    private Context TransportContext;




    private GlobalRequestHandler(Context context) {
        mCtx = context.getApplicationContext();
        TransportContext=context;
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
            mInstance = new GlobalRequestHandler(context);
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

    public void getShuttleRoutes(ArrayList<BusRoute> BusRouteList,BusScheduleFragment busScheduleFragment )  {
        String url="http://192.168.1.104:5000/service/shuttle_routes";
        TransportListener transportListener=new TransportListener("Shuttlelist",BusRouteList,busScheduleFragment,mCtx,BusRoute.class);
        TransportErrorListener transportErrorListener= new TransportErrorListener(BusRoute.class,mCtx,busScheduleFragment,BusRouteList);
        TransportRequest transportRequest= new TransportRequest(url,transportListener,transportErrorListener,mCtx);
        mRequestQueue.add(transportRequest);
    }

    public void getTaxiServices( ArrayList<TaxiService> taxiServiceList,TaxiServiceFragment taxiServiceFragment){
        String url="http://192.168.1.104:5000/service/taxi_services";
        TransportListener transportListener=new TransportListener("TaxiList",taxiServiceList,taxiServiceFragment,mCtx,TaxiService.class);
        TransportErrorListener transportErrorListener=new TransportErrorListener(TaxiService.class,mCtx,taxiServiceFragment,taxiServiceList);
        TransportRequest transportRequest=new TransportRequest(url,transportListener,transportErrorListener,mCtx);
        mRequestQueue.add(transportRequest);

    }

    public void getJUTCRoutes(ArrayList<JUTCRoute> jutcRoutes,JUTCRouteFragment jutcRouteFragment){
        String url="http://192.168.1.104:5000/service/jutc_routes";
        TransportListener transportListener=new TransportListener("JUTCList",jutcRoutes,jutcRouteFragment,mCtx,JUTCRoute.class);
        TransportErrorListener transportErrorListener=new TransportErrorListener(JUTCRoute.class,mCtx,jutcRouteFragment,jutcRoutes);
        TransportRequest transportRequest=new TransportRequest(url,transportListener,transportErrorListener,mCtx);
        mRequestQueue.add(transportRequest);
    }
}
