package mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.Transport.Transport;
import mits.uwi.com.ourmobileenvironment.Transport.TransportFragment;
/**
 * Created by rox on 1/10/16.
 */
public class TransportListener<T extends Transport> implements Response.Listener<JSONObject>{
    private JSONArray objectList;
    private String listName;
    private Toast internalError ;
    private Transport currentrecord;
    private ArrayList Tlist;
    private TransportFragment transportFragment;
    private HashMap<String, String> headers;
    private Class<T> transportSubclass;

    public TransportListener(String listName,
                             ArrayList<T>Tlist,
                             TransportFragment transportFragment,
                             Context mCtx,
                             Class<T>transportSubclass){
        this.listName=listName;
        this.Tlist=Tlist;
        this.transportFragment=transportFragment;
        this.transportSubclass=transportSubclass;
        this.internalError=Toast.makeText(mCtx, "Internal error occured please restart application", Toast.LENGTH_SHORT);
    }



    @Override
    public void onResponse(JSONObject response){
        Gson gson=new Gson();
        String status;
        TransportFragment.TransportAdapter adapter=transportFragment.getAdap();
        Log.e("resp",response.toString());
        try {
            objectList=response.getJSONArray(listName);
            status=response.getString("Status");
            if (status.equals("200")){
                try {
                    Deleteall();
                }
                catch (SQLiteException e){
                    //no database created
                }
            }
            for (int i=0; i<objectList.length();i++){
                currentrecord =gson.fromJson(objectList.getJSONObject(i).toString(), transportSubclass);
                currentrecord.SerialiseList();
                Tlist.add(currentrecord);
                adapter.Add(currentrecord);
                if (status.equals("200")){
                    currentrecord.save();
                }
            }
            if (transportFragment.getActivity() == null){
                return;
            }

            transportFragment.getActivity().findViewById(R.id.progress_bar).setVisibility(View.GONE);
            transportFragment.refreshView();

        }
        catch (JSONException e){
            internalError.show();
            e.printStackTrace();
        }


    }

    public  void Deleteall(){
        T.deleteAll(transportSubclass);
    }
}
