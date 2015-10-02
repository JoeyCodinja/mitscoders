package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mits.uwi.com.ourmobileenvironment.R;

public class MenuFragment extends DialogFragment {
    private static List<Menu_Item> menu;
    private RecyclerView rvm;
    private int i;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //String name;
        //name = EateriesFragment.RVAdapter.Res_Name();
        //name = "Test";
        //getDialog().setTitle(name);
        //newFragment.setContentView(R.layout.fragment_menu_head);
        View v =inflater.inflate(R.layout.menu_dialog, container, false);
        TextView name = (TextView)v.findViewById(R.id.eateries_name);
        ImageView photo = (ImageView)v.findViewById(R.id.eateries_photo);
        RecyclerView recList =(RecyclerView)v.findViewById(R.id.rvm);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        initializeData();
        MenuAdapter menudp =new MenuAdapter(MenuFragment.getMenuList());
        recList.setAdapter(menudp);



        //String eateries_n = EateriesFragment.s_eateries.getName();
        int id = EateriesFragment.photoid;
        name.setText(EateriesFragment.eateries.get(id).name);
        photo.setImageResource(EateriesFragment.eateries.get(id).photoId);
        return  v;
    }

    public void initializeData(){
       menu = new ArrayList<>();
        menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo","Meal Deal","2pc Chicken 1 Reg fries 1 500ml Soda" ,"$ 100 JMD"));
        menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo","Meal Deal","2pc Chicken 1 Reg fries 1 500ml Soda" ,"$ 100 JMD"));
        menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo","Meal Deal","2pc Chicken 1 Reg fries 1 500ml Soda" ,"$ 100 JMD"));
        menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo","Meal Deal","2pc Chicken 1 Reg fries 1 500ml Soda" ,"$ 100 JMD"));
        menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo","Meal Deal","2pc Chicken 1 Reg fries 1 500ml Soda" ,"$ 100 JMD"));
    }

    public static List<Menu_Item> getMenuList() {
        return menu;
    }

    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

        public class MenuViewHolder extends RecyclerView.ViewHolder {

            TextView menuRes;
            TextView menuMeal;
            TextView menuName;
            TextView menuComponent;
            TextView menuPrice;

            MenuViewHolder(View itemView) {
                super(itemView);
                menuRes = (TextView)itemView.findViewById(R.id.eateries_name);
                menuName = (TextView)itemView.findViewById(R.id.meal_name);
                menuComponent = (TextView)itemView.findViewById(R.id.meal_component);
                menuPrice = (TextView)itemView.findViewById(R.id.meal_price);
            }
        }

        List<Menu_Item> menu;

        MenuAdapter(List<Menu_Item> menu){
            this.menu = menu;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public MenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_menu, viewGroup, false);
            MenuViewHolder pvh = new MenuViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(MenuViewHolder MenuViewHolder, int i) {

            MenuViewHolder.menuName.setText(menu.get(i).name);
            MenuViewHolder.menuComponent.setText(menu.get(i).components);
            MenuViewHolder.menuPrice.setText(menu.get(i).price);
        }

        @Override
        public int getItemCount() {
            return menu.size();
        }
    }
}

