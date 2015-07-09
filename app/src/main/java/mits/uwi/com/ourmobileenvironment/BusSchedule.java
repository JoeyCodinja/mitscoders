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


public class BusSchedule extends Fragment {
    private ArrayList<BusRoute> busList=new ArrayList<>();

    public void populatebusList(){
        busList.add(new BusRoute("Liguanea to campus","Departs at 9:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Hospital","Departs at 10:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Papine","Departs at 12:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Mona Road","Departs at 6:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to August Town","Departs at 8:30","Every hour","Next departure 8:30"));
        busList.add(new BusRoute("Liguanea to Hope Gradens", "Departs at 7:30", "Every hour", "Next departure 8:30"));
    }

    public ArrayList<BusRoute> getBusList(){
        return busList;
    }



    public BusSchedule() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populatebusList();



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
        BusScheduleAdapter busadap=new BusScheduleAdapter(busList);
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




    private class BusScheduleAdapter extends RecyclerView.Adapter<BusSchedule.BusScheduleViewHolder>{


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
