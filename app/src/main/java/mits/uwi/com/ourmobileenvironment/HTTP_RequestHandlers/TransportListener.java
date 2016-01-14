package mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import mits.uwi.com.ourmobileenvironment.Transport.Transport;
import mits.uwi.com.ourmobileenvironment.Transport.TransportFragment;

/**
 * Created by rox on 1/10/16.
 */
public class TransportListener<T extends Transport> implements Response.Listener<JSONObject> {
    private JSONArray objectList;
    private String listName;
    private Toast internalError ;
    private String lastMod;
    private Transport currentrecord;
    private ArrayList Tlist;
    private TransportFragment transportFragment;
    private Class<T> transportSubclass;


    public  TransportListener(String listName,ArrayList<T>Tlist,TransportFragment transportFragment,Context mCtx,String lastMod,Class<T>transportSubclass){
        this.listName=listName;
        this.Tlist=Tlist;
        this.transportFragment=transportFragment;
        this.lastMod=lastMod;
        this.transportSubclass=transportSubclass;
        this.internalError=Toast.makeText(mCtx, "Internal error occured please restart application", Toast.LENGTH_SHORT);
    }

    @Override
    public void onResponse(JSONObject response){
        Gson gson=new Gson();
        String status;
        try {
            objectList=response.getJSONArray(listName);
            lastMod=response.getString("Last-Modified");
            status=response.getString("Status");
            if (status.equals("200")){
                Deleteall();
            }
            for (int i=0; i<objectList.length();i++){
                currentrecord =gson.fromJson(objectList.getJSONObject(i).toString(), transportSubclass);
                Log.d("serv",currentrecord.toString());
                currentrecord.SerialiseList();
                Tlist.add(currentrecord);
                if (status.equals("200")){
                    currentrecord.save();
                }
                transportFragment.refreshView();

            }
        }
        catch (JSONException e){
            internalError.show();
        }


    }

    public  void Deleteall(){
        T.deleteAll(transportSubclass);
    }
}
