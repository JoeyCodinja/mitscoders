package mits.uwi.com.ourmobileenvironment.Transport;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.DialogFragment;

import mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers.GlobalRequestHandler;
import mits.uwi.com.ourmobileenvironment.R;


public class BusScheduleFragment extends TransportFragment<BusRoute> {

    BusScheduleAdapter busadap;

    public BusScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public boolean onQueryTextChange(String query){
        final List<BusRoute> filteredModelList = filter(transportModels, query);
        busadap.animateTo(filteredModelList);
        recList.scrollToPosition(0);
        return true;
    }

    private void showDialog(int Index) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        RouteDescription Landmark = new RouteDescription();
        Landmark.setDlist(transportModels.get(Index).getRouteDescription());
        Landmark.show(fm, "fragment_route_description");
    }

    @Override
    public  void refreshView(){
        busadap.notifyDataSetChanged();
    }

    public BusScheduleAdapter getBusadap() {
        return busadap;
    }
    @Override
    public TransportAdapter getAdap(){
        return getBusadap();
    }

    public void loadroutes(){
       GlobalRequestHandler.getInstance(this.getActivity()).getShuttleRoutes(transportModels, this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadroutes();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.recycle, container, false);
        recList =(RecyclerView)v.findViewById(R.id.rv);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        busadap=new BusScheduleAdapter(transportModels);
        recList.setAdapter(busadap);
        return  v;
    }





    private  class BusScheduleViewHolder extends TransportViewHolder{
        private TextView operationTimes,route,freq;

        public BusScheduleViewHolder(View v){
            super(v);
            operationTimes =(TextView) v.findViewById(R.id.operationTimes);
            route=(TextView) v.findViewById(R.id.route);
            freq=(TextView) v.findViewById(R.id.frequency);
            v.setClickable(true);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showDialog(getAdapterPosition());
                }
            });
        }

    }




    private class BusScheduleAdapter extends TransportAdapter<BusRoute,BusScheduleViewHolder>{





        public BusScheduleAdapter(ArrayList<BusRoute> rlist){
            super(rlist);
        }

        @Override
        public BusScheduleViewHolder onCreateViewHolder(ViewGroup parent,int pos){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_bus_schedule,parent,false);
            return new BusScheduleViewHolder(view);

        }


       @Override
        public int getItemCount(){
            return Tlist.size();
        }

        @Override
        public void onBindViewHolder(BusScheduleViewHolder BusScheduleViewHolder, int i) {
            BusRoute br = Tlist.get(i);
            BusScheduleViewHolder.operationTimes.setText(br.getOperationTimes());
            BusScheduleViewHolder.route.setText(br.getRoute());
            BusScheduleViewHolder.freq.setText(br.getFrequency());

        }








        }

    public static class RouteDescription extends DialogFragment {



        public void setDlist(ArrayList<String> dlist) {
            this.dlist = dlist;
        }

        private ArrayList<String> dlist;

        public RouteDescription() {
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
            getDialog().setTitle("Route Description");
            RouteDescAdapter routeDescAdapter=new RouteDescAdapter(dlist);
            recList.setAdapter(routeDescAdapter);
            return v;
        }

        private  class RouteDescViewHolder extends RecyclerView.ViewHolder

        {
            private TextView landmark;

            public RouteDescViewHolder(View v) {
                super(v);
                landmark = (TextView) v.findViewById(R.id.landmark);


            }

        }


        private class RouteDescAdapter extends RecyclerView.Adapter<RouteDescViewHolder>{


            private ArrayList<String > landlist;


            public RouteDescAdapter(ArrayList<String> landlist){
                this.landlist=landlist;
            }

            @Override
            public RouteDescViewHolder onCreateViewHolder(ViewGroup parent,int pos){
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_jutc_dialogue_layout,parent,false);
                return new RouteDescViewHolder(view);

            }


            @Override
            public int getItemCount(){
                return landlist.size();
            }

            @Override
            public void onBindViewHolder(RouteDescViewHolder routeDescViewHolder , int i) {
                ArrayList<String> landmark = landlist;
                routeDescViewHolder.landmark.setText(landmark.get(i));


            }








        }
    }




}
