package mits.uwi.com.ourmobileenvironment.Transport;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.GlobalRequestHandler;
import mits.uwi.com.ourmobileenvironment.R;


public class BusScheduleFragment extends Fragment {
    private ArrayList<BusRoute> tempblist=new ArrayList<>();
    BusScheduleAdapter busadap;

    public ArrayList<BusRoute> getTempblist() {
        return tempblist;
    }



    public void setBusadap(BusScheduleAdapter busadap) {
        this.busadap = busadap;
    }



    public BusScheduleAdapter getBusadap() {
        return busadap;
    }



    public BusScheduleFragment() {
        // Required empty public constructor
    }


    public void setTempblist(ArrayList<BusRoute> blist){
        this.tempblist.clear();
        this.tempblist.addAll(blist);
    }

    public void loadroutes(){
        GlobalRequestHandler.getInstance(this.getActivity()).getShuttleRoutes(tempblist);
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
        RecyclerView recList =(RecyclerView)v.findViewById(R.id.rv);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        busadap=new BusScheduleAdapter(tempblist);
        recList.setAdapter(busadap);

        return  v;
    }

    public void notifydatasetchanged(){
        busadap.notifyDataSetChanged();
    }




    private static class BusScheduleViewHolder extends RecyclerView.ViewHolder{
        protected TextView operationTimes,route,freq, RouteDescription;

        public BusScheduleViewHolder (View v){
            super(v);
            operationTimes =(TextView) v.findViewById(R.id.operationTimes);
            route=(TextView) v.findViewById(R.id.route);
            freq=(TextView) v.findViewById(R.id.frequency);
        }

    }




    private class BusScheduleAdapter extends RecyclerView.Adapter<BusScheduleFragment.BusScheduleViewHolder>{


        private ArrayList<BusRoute> rlist;


        public BusScheduleAdapter(ArrayList<BusRoute> rlist){
            this.rlist=rlist;
        }

        @Override
        public BusScheduleViewHolder onCreateViewHolder(ViewGroup parent,int pos){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_bus_schedule,parent,false);
            return new BusScheduleViewHolder(view);

        }


       @Override
        public int getItemCount(){
            return rlist.size();
        }

        @Override
        public void onBindViewHolder(BusScheduleViewHolder busScheduleViewHolder, int i) {
            BusRoute br = rlist.get(i);
            busScheduleViewHolder.operationTimes.setText(br.getOperationTimes());
            busScheduleViewHolder.route.setText(br.getRoute());
            busScheduleViewHolder.freq.setText(br.getFrequency());
        }








        }




}
