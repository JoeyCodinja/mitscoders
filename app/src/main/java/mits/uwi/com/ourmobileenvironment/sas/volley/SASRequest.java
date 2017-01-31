package mits.uwi.com.ourmobileenvironment.sas.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
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
        protected Map<String, String> getParams() throws AuthFailureError {
            HashMap params = new HashMap<>();
            params.put("username", "mitsUser");
            params.put("password", "Op3nUp");
            params.put("grant_type", "'uwiMona_clientApp'");
            return params;
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
