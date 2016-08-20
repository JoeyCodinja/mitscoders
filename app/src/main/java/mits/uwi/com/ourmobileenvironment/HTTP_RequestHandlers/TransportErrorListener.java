package mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;
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

    public TransportErrorListener(Class<T> transport,Context mCtx,TransportFragment transportFragment,ArrayList<T>Tlist){
        this.transport=transport;
        this.transportFragment=transportFragment;
        internetConnection=Toast.makeText(mCtx,"Please Check Internet Connection",Toast.LENGTH_SHORT);
        this.Tlist=Tlist;



    }

    @Override
    public void onErrorResponse(VolleyError error){
        try{
        List<T> objlist=T.listAll(transport);


        TransportFragment.TransportAdapter adapter=transportFragment.getAdap();
          if (objlist.isEmpty()){
              internetConnection.show();

          }
        else {
              Tlist.clear();
              for (T transport:objlist){
                  transport.DeserialiseList();
                  Tlist.add(transport);
                  adapter.Add(transport);
              }
              transportFragment.refreshView();
              transportFragment.getActivity().findViewById(R.id.progress_bar).setVisibility(View.GONE);


          }
        }

        catch (SQLiteException e){
            //table not created yet
        }
    }

}
