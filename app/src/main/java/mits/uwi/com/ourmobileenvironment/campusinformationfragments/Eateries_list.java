package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import java.util.ArrayList;

/**
 * Created by Leonardo on 7/6/2015.
 */
class Eateries_list {
    String name;
    String location;
    String hours;
    int photoId;
    boolean fav;
    double coordx,coordy;
    String catergory;
    ArrayList<Menu_Item> menu = new ArrayList<>();

    Eateries_list(String name, String location, String hours, int photoId, ArrayList<Menu_Item> m , String cat ,double x, double y) {
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.photoId = photoId;
        this.menu = m;
        this.coordx =x;
        this.coordy =y;
        this.catergory= cat;
        this.fav = false;
    }
    public void setPhoto(int photoId){
        this.photoId = photoId;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPhoto(){
        return this.photoId;
    }

    public String getName(){
        return this.name;
    }

    public void setFav(){
        if(fav == false)
            fav = true;
        else
            fav = false;
    }

    public double getCoordx() {
        return coordx;
    }

    public void setCoordx(double coordx) {
        this.coordx = coordx;
    }

    public double getCoordy() {
        return coordy;
    }

    public void setCoordy(double coordy) {
        this.coordy = coordy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getCatergory() {
        return catergory;
    }

    public void setCatergory(String catergory) {
        this.catergory = catergory;
    }
}

