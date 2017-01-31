package mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers;

/**
 * Created by rox on 12/21/15.
 */
import com.android.volley.Request;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.ImageLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import mits.uwi.com.ourmobileenvironment.DeveloperKey;
import mits.uwi.com.ourmobileenvironment.EateriesActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.Transport.BusRoute;
import mits.uwi.com.ourmobileenvironment.Transport.BusScheduleFragment;
import mits.uwi.com.ourmobileenvironment.Transport.GuildBus;
import mits.uwi.com.ourmobileenvironment.Transport.GuildBusFragment;
import mits.uwi.com.ourmobileenvironment.Transport.JUTCRoute;
import mits.uwi.com.ourmobileenvironment.Transport.JUTCRouteFragment;
import mits.uwi.com.ourmobileenvironment.Transport.TaxiService;
import mits.uwi.com.ourmobileenvironment.Transport.TaxiServiceFragment;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.Restaurant;
import mits.uwi.com.ourmobileenvironment.sas.volley.SASAuthRequestListener;
import mits.uwi.com.ourmobileenvironment.sas.volley.SASDataRequestListener;
import mits.uwi.com.ourmobileenvironment.sas.volley.SASErrorListener;
import mits.uwi.com.ourmobileenvironment.sas.volley.SASRequest;


public class GlobalRequestHandler {
    private static GlobalRequestHandler mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;
    private static HashMap<String, String> headers;



    private GlobalRequestHandler(Context context) {
        mCtx = context.getApplicationContext();
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
            InputStream keyStore = mCtx.getResources().openRawResource(R.raw.api);

            mRequestQueue = Volley.newRequestQueue(mCtx,
                    new ExtHttpClientStack(
                            new SslHttpClient(keyStore,
                                    DeveloperKey.KEYSTORE_PASS)));
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void getShuttleRoutes(ArrayList<BusRoute> BusRouteList, BusScheduleFragment busScheduleFragment )  {
        String url="https://mobile-app.mona.uwi.edu/service/shuttle_routes";
        TransportListener transportListener=new TransportListener("Shuttlelist",BusRouteList,busScheduleFragment,mCtx,BusRoute.class);
        TransportErrorListener transportErrorListener= new TransportErrorListener(BusRoute.class,mCtx,busScheduleFragment,BusRouteList);
        TransportRequest transportRequest= new TransportRequest(url,transportListener,transportErrorListener,mCtx);
        mRequestQueue.add(transportRequest);
    }

    public void getTaxiServices( ArrayList<TaxiService> taxiServiceList,TaxiServiceFragment taxiServiceFragment){
        String url="https://mobile-app.mona.uwi.edu/service/taxi_services";
        TransportListener transportListener=new TransportListener("TaxiList",taxiServiceList,taxiServiceFragment,mCtx,TaxiService.class);
        TransportErrorListener transportErrorListener=new TransportErrorListener(TaxiService.class,mCtx,taxiServiceFragment,taxiServiceList);
        TransportRequest transportRequest=new TransportRequest(url,transportListener,transportErrorListener,mCtx);
        mRequestQueue.add(transportRequest);

    }

    public void getJUTCRoutes(ArrayList<JUTCRoute> jutcRoutes,JUTCRouteFragment jutcRouteFragment){
        String url="https://mobile-app.mona.uwi.edu/service/jutc_routes";
        TransportListener transportListener=new TransportListener("JUTCList",jutcRoutes,jutcRouteFragment,mCtx,JUTCRoute.class);
        TransportErrorListener transportErrorListener=new TransportErrorListener(JUTCRoute.class,mCtx,jutcRouteFragment,jutcRoutes);
        TransportRequest transportRequest=new TransportRequest(url,transportListener,transportErrorListener,mCtx);
        mRequestQueue.add(transportRequest);
    }

    public void getRestaurantList(ArrayList<Restaurant> RList, EateriesActivity eateriesActivity){
        String url="https://mobile-app.mona.uwi.edu/service/eateries";
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

    public void getGuildBusRoutes(ArrayList<GuildBus> GList,GuildBusFragment guildBusFragment){
        String url="https://mobile-app.mona.uwi.edu/service/guild_routes";
        TransportListener transportListener= new TransportListener("GuildList",GList,guildBusFragment,mCtx,GuildBus.class);
        TransportErrorListener transportErrorListener=new TransportErrorListener(GuildBus.class,mCtx,guildBusFragment,GList);
        TransportRequest transportRequest=new TransportRequest(url,transportListener,transportErrorListener,mCtx);
        mRequestQueue.add(transportRequest);
    }

    public void getStudentInfo(String studentId,
                                   Fragment resultantFragment,
                                   AppCompatActivity fragmentActivity){
        String url= String.format("https://ext-web-srvs.uwimona.edu.jm/api/sas/registration/%s", studentId);
        SASDataRequestListener listener = new SASDataRequestListener(resultantFragment, fragmentActivity);
        SASErrorListener errorListener = new SASErrorListener(mCtx);
        SASRequest.SASDataRequest request = new SASRequest.SASDataRequest(url ,
                headers,
                listener,
                errorListener);
        mRequestQueue.add(request);
    }

    public void getCourseInfoCRN(String CRN,
                                 Fragment resultantFragment,
                                 AppCompatActivity fragmentActivity){
        // Retrieve Course Info via CRN
        String url = String.format("https://ext-web-srvs.uwimona.edu.jm/api/sas/section/%s", CRN);
        SASDataRequestListener listener = new SASDataRequestListener(resultantFragment, fragmentActivity);
        SASErrorListener errorListener = new SASErrorListener(mCtx);
        SASRequest.SASDataRequest request = new SASRequest.SASDataRequest(url ,
                headers,
                listener,
                errorListener);
        mRequestQueue.add(request);
    }

    public void getCourseInfoCC(String courseCode,
                                Fragment resultantFragment,
                                AppCompatActivity fragmentActivity){
        // Retrieve Course Info via Cousre Code
        String url = String.format("https://ext-web-srvs.uwimona.edu.jm/api/sas/course/%s", courseCode);
        SASDataRequestListener listener = new SASDataRequestListener(resultantFragment, fragmentActivity);
        SASErrorListener errorListener = new SASErrorListener(mCtx);
        SASRequest.SASDataRequest request = new SASRequest.SASDataRequest(url ,
                headers,
                listener,
                errorListener);
        mRequestQueue.add(request);
    }

    public void getSASAuthToken(HashMap<String, String> credentials){
        String url="https://ext-web-srvs.uwimona.edu.jm/api/oauth2/token";
        SASAuthRequestListener listener = new SASAuthRequestListener(headers);
        SASErrorListener errorListener = new SASErrorListener(mCtx);
        SASRequest.SASAuthRequest request = new SASRequest.SASAuthRequest(url,
                credentials,
                listener,
                errorListener);
        mRequestQueue.add(request);
    }
}
