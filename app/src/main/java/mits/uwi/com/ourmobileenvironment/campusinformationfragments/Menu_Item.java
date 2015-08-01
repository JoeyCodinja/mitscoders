package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

/**
 * Created by Leonardo on 7/24/2015.
 */
public class Menu_Item {
    String restaurant;
    String meal;
    String name;
    String components;
    float price;


    Menu_Item(String restaurant, String meal, String name, String components, float price) {
        this.restaurant = restaurant;
        this.meal = meal;
        this.name = name;
        this.components = components;
        this.price = price;
    }
}
