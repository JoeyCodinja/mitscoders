package mits.uwi.com.ourmobileenvironment.Transport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by rox on 1/11/16.
 */
public abstract class  TransportFragment<T extends Transport> extends Fragment implements SearchView.OnQueryTextListener{
    protected TransportAdapter adapter;


    protected RecyclerView recList;
    protected ArrayList<T> transportModels=new ArrayList<>();
    public TransportFragment(){

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
    }

    public RecyclerView getRecList() {
        return recList;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater menuInflater){
        menuInflater.inflate(R.menu.menu_bus_schedule, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query){
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query){
        final List<T> filteredModelList = filter(transportModels, query);
        adapter.animateTo(filteredModelList);
        recList.scrollToPosition(0);
        return true;

    }




    public void refreshView(){
        adapter.notifyDataSetChanged();

    }

    public abstract TransportAdapter getAdap();

    protected   <T extends Transport>List<T> filter(List<T> models, String query) {
        query = query.toLowerCase();

        final List<T> filteredModelList = new ArrayList<>();
        for (T model : models) {
            final String text = model.getSearchField().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


    public class TransportViewHolder extends RecyclerView.ViewHolder{

        public TransportViewHolder(View view){
            super(view);
        }

    }

    public abstract  class TransportAdapter<T ,V extends TransportViewHolder> extends RecyclerView.Adapter<V>{
        protected int layout;
        protected ArrayList<T>Tlist;

        public TransportAdapter(ArrayList<T> Tlist){
            this.Tlist=new ArrayList<>(Tlist);
        }


        public void animateTo(List<T> transportModels) {
            applyAndAnimateRemovals(transportModels);
            applyAndAnimateAdditions(transportModels);
            applyAndAnimateMovedItems(transportModels);
        }



        public void Add(T transrec){
            this.Tlist.add(transrec);
        }


        private void applyAndAnimateRemovals(List<T> transportModels) {
            for (int i = Tlist.size() - 1; i >= 0; i--) {
                final T model = Tlist.get(i);
                if (!transportModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<T> transportModels) {
            for (int i = 0, count = transportModels.size(); i < count; i++) {
                final T model = transportModels.get(i);
                if (!Tlist.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<T> transportModels) {
            for (int toPosition = transportModels.size() - 1; toPosition >= 0; toPosition--) {
                final T model = transportModels.get(toPosition);
                final int fromPosition = Tlist.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public T removeItem(int position) {
            final T model = Tlist.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, T model) {
            Tlist.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final T model = Tlist.remove(fromPosition);
            Tlist.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }
    }

}

