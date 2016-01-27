package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

/**
 * Created by Leonardo on 7/24/2015.
 */
public class Menu_Item {
    String restaurant;
    String mealgroup;
    String meal;
    String components;
    String price;


    Menu_Item(String restaurant, String mealgroup, String meal, String components, String price) {
        this.restaurant = restaurant;
        this.mealgroup = mealgroup;
        this.meal = meal;
        this.components = components;
        this.price = price;
    }
}
