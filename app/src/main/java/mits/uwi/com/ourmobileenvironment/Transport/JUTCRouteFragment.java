package mits.uwi.com.ourmobileenvironment.Transport;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers.GlobalRequestHandler;
import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by rox on 1/12/16.
 */
public class JUTCRouteFragment extends TransportFragment {
    private ArrayList<JUTCRoute> JUTCList=new ArrayList<>();
    JUTCRouteAdapter jutcRouteAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadJUTCRoutes();

    }

    private void showDialog(int Index) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        RouteDialog Landmark = new RouteDialog();
        Landmark.setRoutelist(JUTCList.get(Index).getRouteList());
        Landmark.show(fm, "fragment_JUTC_Route");
    }

    public void refreshView() {
        jutcRouteAdapter.notifyDataSetChanged();
    }


    public void loadJUTCRoutes() {
        GlobalRequestHandler.getInstance(this.getActivity()).getJUTCRoutes(JUTCList, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.recycle, container, false);
        RecyclerView recList =(RecyclerView)v.findViewById(R.id.rv);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        jutcRouteAdapter=new JUTCRouteAdapter(JUTCList);
        recList.setAdapter(jutcRouteAdapter);
        return  v;
    }

    private  class JUTCRouteViewHolder extends RecyclerView.ViewHolder{
        private TextView origin, destination,routenum;

        public JUTCRouteViewHolder (View v){
            super(v);
            origin =(TextView) v.findViewById(R.id.origin );
            destination =(TextView) v.findViewById(R.id.destination);
            routenum=(TextView) v.findViewById(R.id.route_num);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showDialog(getAdapterPosition());
                }
            });
        }

    }

    private class JUTCRouteAdapter extends RecyclerView.Adapter<JUTCRouteFragment.JUTCRouteViewHolder>{


        private ArrayList<JUTCRoute> JUTCRouteList;


        public JUTCRouteAdapter(ArrayList<JUTCRoute> JUTCRouteList){
            this.JUTCRouteList = JUTCRouteList;
        }

        @Override
        public JUTCRouteViewHolder onCreateViewHolder(ViewGroup parent,int pos){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_jutc_route,parent,false);

            return new JUTCRouteViewHolder(view);

        }


        @Override
        public int getItemCount(){
            return JUTCRouteList.size();
        }

        @Override
        public void onBindViewHolder(JUTCRouteViewHolder jutcRouteViewHolder, int i) {
            JUTCRoute jutcRoute = JUTCRouteList.get(i);
            jutcRouteViewHolder.routenum.setText(jutcRoute.getRouteNum());
            jutcRouteViewHolder.origin.setText("Origin: "+jutcRoute.getOrigin());
            jutcRouteViewHolder.destination.setText("Destination: "+jutcRoute.getDestination());

        }








    }

    public static class RouteDialog extends DialogFragment {



        public void setRoutelist(ArrayList<String> dlist) {
            this.Routelist = dlist;
        }

        private ArrayList<String> Routelist;

        public RouteDialog() {
            // Empty constructor is required for DialogFragment
            // Make sure not to add arguments to the constructor
            // Use `newInstance` instead as shown below
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v =inflater.inflate(R.layout.recycle, container, false);
            RecyclerView recList =(RecyclerView)v.findViewById(R.id.rv);
            recList.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recList.setLayoutManager(llm);
            getDialog().setTitle("Route");
            RouteDescAdapter routeDescAdapter=new RouteDescAdapter(Routelist);
            recList.setAdapter(routeDescAdapter);
            return v;
        }

        private  class RouteDescViewHolder extends RecyclerView.ViewHolder

        {
            private TextView route;

            public RouteDescViewHolder(View v) {
                super(v);
                route = (TextView) v.findViewById(R.id.route_dialog);


            }

        }


        private class RouteDescAdapter extends RecyclerView.Adapter<RouteDescViewHolder>{


            private ArrayList<String > routelist;


            public RouteDescAdapter(ArrayList<String> routelist){
                this.routelist = routelist;
            }

            @Override
            public RouteDescViewHolder onCreateViewHolder(ViewGroup parent,int pos){
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_route_dialog,parent,false);
                return new RouteDescViewHolder(view);

            }


            @Override
            public int getItemCount(){
                return routelist.size();
            }

            @Override
            public void onBindViewHolder(RouteDescViewHolder routeDescViewHolder , int i) {
                ArrayList<String> route = routelist;
                routeDescViewHolder.route.setText(route.get(i));


            }








        }
    }

}
