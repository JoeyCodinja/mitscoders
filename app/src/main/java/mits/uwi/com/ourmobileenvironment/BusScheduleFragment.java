package mits.uwi.com.ourmobileenvironment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class BusScheduleFragment extends Fragment {






    public BusScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
        BusScheduleAdapter busadap=new BusScheduleAdapter(Busfactory.getBusList());
        recList.setAdapter(busadap);

        return  v;
    }




    private static class BusScheduleViewHolder extends RecyclerView.ViewHolder{
        protected TextView depart,route,freq,nextdepart;

        public BusScheduleViewHolder (View v){
            super(v);
            depart=(TextView) v.findViewById(R.id.departure);
            route=(TextView) v.findViewById(R.id.route);
            freq=(TextView) v.findViewById(R.id.frequency);
            nextdepart=(TextView) v.findViewById(R.id.ndparture);
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
            busScheduleViewHolder.depart.setText(br.getDeparture());
            busScheduleViewHolder.route.setText(br.getRoute());
            busScheduleViewHolder.nextdepart.setText(br.getNextDeparture());
            busScheduleViewHolder.freq.setText(br.getFrequency());
        }








        }




}
