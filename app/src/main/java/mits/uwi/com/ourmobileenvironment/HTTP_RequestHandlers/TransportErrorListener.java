package mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.Transport.Transport;
import mits.uwi.com.ourmobileenvironment.Transport.TransportFragment;

/**
 * Created by rox on 1/11/16.
 */
public class TransportErrorListener <T extends Transport>implements Response.ErrorListener {
    private Class<T> transport;
    private Toast internetConnection;
    private ArrayList<T>Tlist;
    private TransportFragment transportFragment;
    private Transport currentRecord;

    public TransportErrorListener(Class<T> transport,Context mCtx,TransportFragment transportFragment,ArrayList<T>Tlist){
        this.transport=transport;
        this.transportFragment=transportFragment;
        internetConnection=Toast.makeText(mCtx,"Please Check Internet Connection",Toast.LENGTH_SHORT);
        this.Tlist=Tlist;



    }

    @Override
    public void onErrorResponse(VolleyError error){
          if (T.listAll(transport).isEmpty()){
              internetConnection.show();

          }
        else {
              Tlist.clear();
              for (T transport:T.listAll(this.transport)){
                  transport.DeserialiseList();
                  Tlist.add(transport);
              }
              transportFragment.refreshView();

          }
    }

}
