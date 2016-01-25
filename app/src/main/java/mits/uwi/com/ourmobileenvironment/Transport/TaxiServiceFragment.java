package mits.uwi.com.ourmobileenvironment.Transport;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.HTTP_RequestHandlers.GlobalRequestHandler;
import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by rox on 1/10/16.
 */
public class TaxiServiceFragment extends TransportFragment<TaxiService> {

    TaxiServiceAdapter taxiadap;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        loadtaxiservices();

    }
    private void showDialog(int Index) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        TaxiContact Landmark = new TaxiContact();
        Landmark.setContactlist(transportModels.get(Index).getNumbers());
        Landmark.show(fm, "fragment_taxi_contact");
    }
    public TaxiServiceAdapter getTaxiadap() {
        return taxiadap;
    }

    @Override
    public TransportAdapter getAdap(){
        return getTaxiadap();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.recycle, container, false);
        recList =(RecyclerView)v.findViewById(R.id.rv);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        taxiadap=new TaxiServiceAdapter(transportModels);
        recList.setAdapter(taxiadap);
        return  v;
    }

    private  class TaxiServiceViewHolder extends TransportViewHolder{
        private TextView email,name;

        public TaxiServiceViewHolder (View v){
            super(v);
            email=(TextView) v.findViewById(R.id.taxi_service_email );
            name=(TextView) v.findViewById(R.id.taxi_service_name);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showDialog(getAdapterPosition());
                }
            });
        }

    }
    private class TaxiServiceAdapter extends TransportAdapter<TaxiService,TaxiServiceViewHolder>{



        public TaxiServiceAdapter(ArrayList<TaxiService> taxiServiceArrayList){
           super(taxiServiceArrayList);
        }

        @Override
        public TaxiServiceViewHolder onCreateViewHolder(ViewGroup parent,int pos){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_taxi_service, parent, false);

            return new TaxiServiceViewHolder(view);

        }


        @Override
        public int getItemCount(){
            return Tlist.size();
        }

        @Override
        public void onBindViewHolder(TaxiServiceViewHolder taxiServiceViewHolder, int i) {
            TaxiService taxiService = Tlist.get(i);
            if(!taxiService.getEmail().isEmpty()) {
                taxiServiceViewHolder.email.setText(taxiService.getEmail());
            }
            else{
                taxiServiceViewHolder.email.setText("No Email Available");
            }
            taxiServiceViewHolder.name.setText(taxiService.getName());

        }

    }
    public void loadtaxiservices(){
        GlobalRequestHandler.getInstance(getActivity()).getTaxiServices(transportModels,this);
    }

    @Override
    public void refreshView(){
        taxiadap.notifyDataSetChanged();
        Log.d("class",this.getClass().getName());

    }

    @Override
    public boolean onQueryTextChange(String query){
        final List<TaxiService> filteredModelList = filter(transportModels, query);
        taxiadap.animateTo(filteredModelList);
        recList.scrollToPosition(0);
        return true;
    }
    public static class TaxiContact extends DialogFragment {



        public void setContactlist(ArrayList<String> contactlist) {
            this.contactlist = contactlist;
        }

        private ArrayList<String> contactlist;

        public TaxiContact() {
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
            getDialog().setTitle("Contact List");
            ContactListAdapter contactListAdapter =new ContactListAdapter(contactlist);
            recList.setAdapter(contactListAdapter);
            return v;
        }

        private  class ContactListViewHolder extends RecyclerView.ViewHolder

        {
            private TextView contact;

            public ContactListViewHolder(View v) {
                super(v);
                contact = (TextView) v.findViewById(R.id.contact);


            }

        }


        private class ContactListAdapter extends RecyclerView.Adapter<ContactListViewHolder>{


            private ArrayList<String > contactlist;


            public ContactListAdapter(ArrayList<String> contactlist){
                this.contactlist = contactlist;
            }

            @Override
            public ContactListViewHolder onCreateViewHolder(ViewGroup parent,int pos){
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_contact_list,parent,false);
                return new ContactListViewHolder(view);

            }


            @Override
            public int getItemCount(){
                return contactlist.size();
            }

            @Override
            public void onBindViewHolder(ContactListViewHolder contactListViewHolder, int i) {
                ArrayList<String> contactlist = this.contactlist;
                contactListViewHolder.contact.setText(contactlist.get(i));


            }








        }
    }

}
