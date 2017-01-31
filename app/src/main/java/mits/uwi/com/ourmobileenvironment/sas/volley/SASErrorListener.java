package mits.uwi.com.ourmobileenvironment.sas.volley;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Danuel on 30/1/2017.
 */
public class SASErrorListener implements Response.ErrorListener {
    Context ctx;

    public SASErrorListener(Context ctx){
        this.ctx = ctx;
    }

    public static void showUserError(String errorMsg, Context ctx){
        // TODO: Add Snackbar here
        Toast.makeText(ctx, errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorResponse(VolleyError error){
        String errorMsg;
        if (error.networkResponse.statusCode == 401){
            errorMsg = "Unauthorized action executed";
        } else{
            errorMsg = "Internet Connection Error";
        }

        SASErrorListener.showUserError(errorMsg, this.ctx);
    }
}
