package mits.uwi.com.ourmobileenvironment.sas.processing;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by peoplesoft on 2/15/2016.
 */
public class BitmapWorkerTask /*extends AsyncTask<Integer, Void, Bitmap> */{

   /* private final WeakReference<ImageView> imageViewReference;
    private int data = 0;

    public BitmapWorkerTask (ImageView imageView){
        imageViewReference = new WeakReference<ImageView>(imageView);
    }
   /@Override
    protected  Bitmap doInBackground (Integer... params){
        data = params[0];
        return decodeSampledBitmapfromResource(getResource(), data, 100,100);
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap!=null){
            final ImageView imageView = imageViewReference.get();
            if (imageView != null){
                imageView.setImageBitmap(bitmap);
            }
        }
    }*/
}
