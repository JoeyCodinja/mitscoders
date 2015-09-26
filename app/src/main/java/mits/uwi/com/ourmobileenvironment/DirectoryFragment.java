package mits.uwi.com.ourmobileenvironment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class DirectoryFragment extends Fragment {
    private ArrayList<DirectoryEntry> mDirectories;


    public DirectoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.title_activity_directory);
        mDirectories=DirectoryEntry.getmDirectories();



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.recycle, container, false);

        RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.rv);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        DirectoryAdapter directoryAdapter=new DirectoryAdapter(DirectoryEntry.getmDirectories());

        recyclerView.setAdapter(directoryAdapter);

        return v;



    };

    private class  DirectoryViewHolder extends RecyclerView.ViewHolder {
        protected TextView name,num,title,dept;
        protected ImageView rimg;

        public DirectoryViewHolder(View v){
            super(v);
            name=(TextView) v.findViewById(R.id.name);
            num=(TextView) v.findViewById(R.id.number);
            title=(TextView)v.findViewById(R.id.title);
            dept=(TextView)v.findViewById(R.id.department);
            rimg=(ImageView)v.findViewById(R.id.roundimg);

        }
    }

    private class DirectoryAdapter extends RecyclerView.Adapter<DirectoryFragment.DirectoryViewHolder>{
        private ArrayList<DirectoryEntry> dlist;
        private ImageView loc,phone,arrow;

        public DirectoryAdapter(ArrayList<DirectoryEntry> dlist){
            this.dlist=dlist;

        }

        @Override
        public  DirectoryViewHolder onCreateViewHolder(ViewGroup parent,int pos){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.directoryfragment,parent,false);

            loc=(ImageView)view.findViewById(R.id.location);
            phone=(ImageView) view.findViewById(R.id.phone);
            arrow=(ImageView) view.findViewById(R.id.arrow);
            loc.setColorFilter(getResources().getColor(R.color.blue_background), PorterDuff.Mode.MULTIPLY);
            phone.setColorFilter(getResources().getColor(R.color.blue_background), PorterDuff.Mode.MULTIPLY);
            arrow.setColorFilter(getResources().getColor(R.color.blue_background), PorterDuff.Mode.MULTIPLY);
            return new DirectoryViewHolder(view);
        }

        @Override
        public int getItemCount(){
            return dlist.size();
        }

        @Override
        public void onBindViewHolder(DirectoryViewHolder directoryViewHolder,int i){
            DirectoryEntry directoryEntry=dlist.get(i);
            directoryViewHolder.name.setText(directoryEntry.getName());
            directoryViewHolder.dept.setText(directoryEntry.getDepartment());
            directoryViewHolder.title.setText(directoryEntry.getTitle());
            directoryViewHolder.num.setText(directoryEntry.getNum());
            directoryViewHolder.rimg.setImageResource(directoryEntry.getIcon());
        }

    }
}
