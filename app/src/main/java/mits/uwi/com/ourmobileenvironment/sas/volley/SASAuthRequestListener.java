package mits.uwi.com.ourmobileenvironment.sas.volley;

import com.android.volley.Response;
import com.google.common.base.Function;
import com.google.common.base.Functions;

import org.json.JSONObject;

import java.util.HashMap;

import mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers.GlobalRequestHandler;

/**
 * Created by Danuel on 30/1/2017.
 */
public class SASAuthRequestListener implements Response.Listener<String> {

    Boolean responseReceived;
    HashMap<String, String> headers;

    public SASAuthRequestListener(){
        this(null);
    }

    public SASAuthRequestListener(HashMap<String, String> headersToUpdate){
        this.headers = headersToUpdate;
        this.responseReceived = false;

    }

    public SASAuthRequestListener setHeaders(HashMap<String, String> headers){
        this.headers = headers;
        return this;
    }


    public HashMap<String, String> getHeaders() throws UnsupportedOperationException{
        if (responseReceived){
            return headers;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void onResponse(String s) {
        responseReceived = true;
        headers.put("Authorization", "Bearer ".concat(s));
    }
}
