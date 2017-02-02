package mits.uwi.com.ourmobileenvironment.sas.volley;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers.GlobalRequestHandler;
import mits.uwi.com.ourmobileenvironment.HomeActivity;

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
        if (error.networkResponse == null){
            errorMsg = error.getMessage();
        } else if (error.networkResponse.statusCode == 401){
            // TODO: Try to reauthenticate 401 response
            // TODO: Implement Retries counter; set to 5
//           errorMsg = "Unauthorized action executed";
            return;
        } else{
            errorMsg = "Internet Connection Error";
        }
        error.printStackTrace();
        SASErrorListener.showUserError(errorMsg, this.ctx);
        Intent i = new Intent(ctx.getApplicationContext(), HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.ctx.startActivity(i);
    }


}


