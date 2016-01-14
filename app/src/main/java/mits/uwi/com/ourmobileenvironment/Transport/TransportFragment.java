package mits.uwi.com.ourmobileenvironment.Transport;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

/**
 * Created by rox on 1/11/16.
 */
public class TransportFragment extends Fragment {
    protected RecyclerView.Adapter adapter;
    public TransportFragment(){

    }

    public void refreshView(){
        adapter.notifyDataSetChanged();

    }
}
