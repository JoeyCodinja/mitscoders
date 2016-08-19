package mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers;

/**
 * Created by rox on 12/21/15.
 */
import com.android.volley.Request;
import com.android.volley.toolbox.ImageLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import mits.uwi.com.ourmobileenvironment.Transport.BusRoute;
import mits.uwi.com.ourmobileenvironment.Transport.BusScheduleFragment;
import mits.uwi.com.ourmobileenvironment.Transport.GuildBus;
import mits.uwi.com.ourmobileenvironment.Transport.GuildBusFragment;
import mits.uwi.com.ourmobileenvironment.Transport.JUTCRoute;
import mits.uwi.com.ourmobileenvironment.Transport.JUTCRouteFragment;
import mits.uwi.com.ourmobileenvironment.Transport.TaxiService;
import mits.uwi.com.ourmobileenvironment.Transport.TaxiServiceFragment;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.Restaurant;

import mits.uwi.com.ourmobileenvironment.EateriesActivity;


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
        String url="https://webservice-rox117.c9users.io/service/shuttle_routes";
        TransportListener transportListener=new TransportListener("Shuttlelist",BusRouteList,busScheduleFragment,mCtx,BusRoute.class);
        TransportErrorListener transportErrorListener= new TransportErrorListener(BusRoute.class,mCtx,busScheduleFragment,BusRouteList);
        TransportRequest transportRequest= new TransportRequest(url,transportListener,transportErrorListener,mCtx);
        mRequestQueue.add(transportRequest);
    }

    public void getTaxiServices( ArrayList<TaxiService> taxiServiceList,TaxiServiceFragment taxiServiceFragment){
        String url="https://webservice-rox117.c9users.io/service/taxi_services";
        TransportListener transportListener=new TransportListener("TaxiList",taxiServiceList,taxiServiceFragment,mCtx,TaxiService.class);
        TransportErrorListener transportErrorListener=new TransportErrorListener(TaxiService.class,mCtx,taxiServiceFragment,taxiServiceList);
        TransportRequest transportRequest=new TransportRequest(url,transportListener,transportErrorListener,mCtx);
        mRequestQueue.add(transportRequest);

    }

    public void getJUTCRoutes(ArrayList<JUTCRoute> jutcRoutes,JUTCRouteFragment jutcRouteFragment){
        String url="https://webservice-rox117.c9users.io/service/jutc_routes";
        TransportListener transportListener=new TransportListener("JUTCList",jutcRoutes,jutcRouteFragment,mCtx,JUTCRoute.class);
        TransportErrorListener transportErrorListener=new TransportErrorListener(JUTCRoute.class,mCtx,jutcRouteFragment,jutcRoutes);
        TransportRequest transportRequest=new TransportRequest(url,transportListener,transportErrorListener,mCtx);
        mRequestQueue.add(transportRequest);
    }


    public void getRestaurantList(ArrayList<Restaurant> RList, EateriesActivity eateriesActivity){
        String url="https://webservice-rox117.c9users.io/service//eateries";
        RestaurantListener restaurantListener = new RestaurantListener("RestaurantList", RList,
                                                                       eateriesActivity, mCtx,
                                                                       Restaurant.class);
        RestaurantErrorListener restaurantErrorListener =
                new RestaurantErrorListener(Restaurant.class, mCtx,
                                            eateriesActivity, RList);

        TransportRequest transportRequest = new TransportRequest(url, restaurantListener,
                                                                 restaurantErrorListener, mCtx);
        mRequestQueue.add(transportRequest);
    }

    public  void getGuildBusRoutes(ArrayList<GuildBus> GList,GuildBusFragment guildBusFragment){
        String url="";
        TransportListener transportListener= new TransportListener("GuildBusList",GList,guildBusFragment,mCtx,GuildBus.class);
        TransportErrorListener transportErrorListener=new TransportErrorListener(GuildBus.class,mCtx,guildBusFragment,GList);
        TransportRequest transportRequest=new TransportRequest(url,transportListener,transportErrorListener,mCtx);
        mRequestQueue.add(transportRequest);
    }
}
