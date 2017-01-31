package mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by rox on 1/10/16.
 */
public class TransportRequest extends JsonObjectRequest {
    private Context context;

    public TransportRequest(String url,
                            Response.Listener<JSONObject>Listener,
                            Response.ErrorListener errorListener,
                            Context context){
        super( url,Listener, errorListener);
        this.context=context;


    }

    @Override
    public Map<String,String>getHeaders() throws AuthFailureError{
        HashMap<String, String> headers = new HashMap<>();
        SharedPreferences sharedPref = context.getSharedPreferences(
                "mits.uwi.com.ourmobileenvironment.Last-Modified", Context.MODE_PRIVATE);
        headers.put("Last-Modified",sharedPref.getString("Last-Modified",""));
        Log.d("Lastmod", sharedPref.getString("Last-Modified", ""));

        return headers;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(
                    "mits.uwi.com.ourmobileenvironment.Last-Modified", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("Last-Modified", response.headers.get("Last-Modified"));
            editor.commit();
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            JSONObject jsonObject=new JSONObject(jsonString);
            jsonObject.put("Status",""+response.statusCode);
            return Response.success(jsonObject,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}
