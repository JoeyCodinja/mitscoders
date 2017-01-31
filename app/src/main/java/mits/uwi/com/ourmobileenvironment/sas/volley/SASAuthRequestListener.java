package mits.uwi.com.ourmobileenvironment.sas.volley;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Danuel on 30/1/2017.
 */
public class SASAuthRequestListener implements Response.Listener<String> {

    Boolean responseReceived;
    HashMap<String, String> headers;

    public SASAuthRequestListener(HashMap<String, String> headersToUpdate){
        this.headers = headersToUpdate;
        this.responseReceived = false;
    }

    public HashMap<String, String> getHeaders() throws UnsupportedOperationException{
        if (responseReceived){
            return headers;
        }
        throw new UnsupportedOperationException();
    }

    public void onResponse(String response){
        responseReceived = true;
        headers.put("Authorization", "Bearer ".concat(response));
    }
}


