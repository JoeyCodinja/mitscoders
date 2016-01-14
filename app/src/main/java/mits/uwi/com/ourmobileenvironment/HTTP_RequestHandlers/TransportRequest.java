package mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers;

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

/**
 * Created by rox on 1/10/16.
 */
public class TransportRequest extends JsonObjectRequest {
    private String lastMod;
    public TransportRequest(String url,Response.Listener<JSONObject>Listener,String lastMod,Response.ErrorListener errorListener){
        super( url,Listener, errorListener);
        this.lastMod = lastMod;


    }

    @Override
    public Map<String,String>getHeaders() throws AuthFailureError{
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Last-Modified", lastMod);
        return headers;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            JSONObject jsonObject=new JSONObject(jsonString);
            jsonObject.put("Last-Modified", response.headers.get("Last-Modified"));
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
