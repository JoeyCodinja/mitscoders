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
import java.util.List;

import mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers.GlobalRequestHandler;
import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by peoplesoft on 6/14/2016.
 */
public class GuildBusFragment extends TransportFragment<GuildBus> {
    GuildBusAdapter guildBusAdapter;


    @Override
    public void  onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

    }

    private void showDialog(int Index) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        GuildBusRoute Landmark = new GuildBusRoute();
        Landmark.setContactlist(transportModels.get(Index).getNumbers());
        Landmark.show(fm, "fragment_guild_bus");
    }

    @Override
    public void refreshView(){
        guildBusAdapter.notifyDataSetChanged();
    }

    public GuildBusAdapter getGuildBusAdapter(){
        return guildBusAdapter;
    }

    @Override
    public TransportAdapter getAdap(){
        return getGuildBusAdapter();
    }

    @Override
    public boolean onQueryTextChange(String query){
        final List<GuildBus> filteredModelList = filter(transportModels, query);
        guildBusAdapter.animateTo(filteredModelList);
        recList.scrollToPosition(0);
        return true;

    }

    public void loadGuildBusRoutes() {
        GlobalRequestHandler.getInstance(this.getActivity()).getGuildBusRoutes(transportModels, this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v =inflater.inflate(R.layout.recycle, container, false);
        recList =(RecyclerView)v.findViewById(R.id.rv);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        guildBusAdapter=new GuildBusAdapter(transportModels);
        recList.setAdapter(guildBusAdapter);
        return  v;
    }

    private class GuildBusViewHolder extends TransportViewHolder {
        private TextView cost, departure_time, route_name;


        public GuildBusViewHolder(View v) {
            super(v);
            cost = (TextView) v.findViewById(R.id.guild_bus_cost);
            departure_time=(TextView) v.findViewById(R.id.guild_bus_departure_time);
            route_name=(TextView)v.findViewById(R.id.guild_bus_route_name);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showDialog(getAdapterPosition());
                }
            });


        }
    }

    private class  GuildBusAdapter extends TransportAdapter<GuildBus,GuildBusViewHolder>{

        public  GuildBusAdapter(ArrayList<GuildBus> GuildBusList){
            super(GuildBusList);
        }

        @Override
        public GuildBusViewHolder onCreateViewHolder(ViewGroup parent,int pos){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_guild_bus,parent,false);

            return new GuildBusViewHolder(view);

        }

        @Override
        public  int getItemCount(){return Tlist.size();}

        @Override
        public void onBindViewHolder(GuildBusViewHolder guildBusViewHolder, int i){
            GuildBus guildBus=Tlist.get(i);
            guildBusViewHolder.route_name.setText(guildBus.getRoute_name());
            guildBusViewHolder.departure_time.setText(guildBus.getDeparture_time());
            guildBusViewHolder.cost.setText(guildBus.getCost());
        }
    }

    public static class GuildBusRouteDialog extends DialogFragment{

        private ArrayList<String> Routelist;


        public void setRoutelist(ArrayList<String> dlist) {
            this.Routelist = dlist;
        }

        public GuildBusRouteDialog(){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View v =inflater.inflate(R.layout.recycle, container, false);
            RecyclerView recList =(RecyclerView)v.findViewById(R.id.rv);
            recList.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recList.setLayoutManager(llm);
            getDialog().setTitle("Route Description");
            GuildBusDescAdapter guildBusDescAdapter=new GuildBusDescAdapter(Routelist);
            recList.setAdapter(guildBusDescAdapter);
            return v;
        }


        private class GuildBusDescAdapter extends RecyclerView.Adapter<GuildBusDescViewHolder>




    }
}
