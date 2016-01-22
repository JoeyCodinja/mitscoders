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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Eateries_list E;
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
        E = MenuDataPasser.getInstance().getResName();
        //id = MenuDataPasser.getInstance().getResPhoto();
        name.setText(E.name);
        photo.setImageResource(E.photoId);
        return  v;
    }

    public void initializeData(){
        Eateries_list A;
        A = MenuDataPasser.getInstance().getResName();
        menu = new ArrayList<>();
        menu = A.menu;
        //menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo","Meal Deal","2pc Chicken 1 Reg fries 1 500ml Soda" ,"$ 100 JMD"));
        //menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo","Meal Deal","2pc Chicken 1 Reg fries 1 500ml Soda" ,"$ 100 JMD"));
        //menu.add(new Menu_Item("Juici Patties", "Classic Combo","Patties","2pc Patty 1 500ml Soda" ,"$ 150 JMD"));
        //menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo","Meal Deal","2pc Chicken 1 Reg fries 1 500ml Soda" ,"$ 100 JMD"));
        //menu.add(new Menu_Item("Kentucky Fried Chicken", "Classic Combo","Meal Deal","2pc Chicken 1 Reg fries 1 500ml Soda" ,"$ 100 JMD"));
    }

    public static List<Menu_Item> getMenuList() {
        return menu;
    }

    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

        public class MenuViewHolder extends RecyclerView.ViewHolder {

            TextView menuRes;
            TextView menuMealGroup;
            TextView menuMealName;
            TextView menuComponent;
            TextView menuPrice;

            MenuViewHolder(View itemView) {
                super(itemView);
                menuRes = (TextView)itemView.findViewById(R.id.eateries_name);
                menuMealName = (TextView)itemView.findViewById(R.id.meal_mealname);
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

                    MenuViewHolder.menuMealName.setText(menu.get(i).meal);
                    MenuViewHolder.menuComponent.setText(menu.get(i).components);
                    MenuViewHolder.menuPrice.setText(menu.get(i).price);
        }

        @Override
        public int getItemCount() {

            return menu.size();

        }
    }
}

