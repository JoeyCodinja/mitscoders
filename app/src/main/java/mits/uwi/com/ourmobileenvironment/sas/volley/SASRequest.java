package mits.uwi.com.ourmobileenvironment.sas.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Danuel on 30/1/2017.
 */
public class SASRequest{

    public static class SASAuthRequest extends StringRequest{
        HashMap<String, String> params;
        public SASAuthRequest(String url,
                              HashMap<String, String> credentials,
                              Response.Listener<String> listener,
                              Response.ErrorListener errorListener) {
            super(StringRequest.Method.POST, url, listener, errorListener);
            this.params = credentials;
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("username", "mitsTest");
            params.put("password", "Op3nUp");
            params.put("grant_type", "uwiMona_clientApp");

            Iterator iterator = params.keySet().iterator();
            String encodedParams = "";
            String key = (String)iterator.next();
            while(iterator.hasNext()){
                String value = params.get(key);
                encodedParams = encodedParams.concat( key + "=" + value + "&");
                key = (String)iterator.next();
            }
            encodedParams = encodedParams.concat(key + "=" +  params.get(key));
            return encodedParams.getBytes();
        }

        @Override
        public String getBodyContentType() {
            return "application/x-www-form-urlencoded";
        }
    }

    public static class SASDataRequest extends JsonObjectRequest {
        HashMap<String, String> headers;

        public SASDataRequest(String url,
                              HashMap<String, String> headers,
                              Response.Listener<JSONObject> listener,
                              Response.ErrorListener errorListener){
            super(JsonObjectRequest.Method.GET, url, listener, errorListener);
            this.headers = headers;
        }

        @Override
        public HashMap<String, String> getHeaders() {
            return this.headers;
        }
    }
}
