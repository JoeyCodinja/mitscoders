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
    float coord[];
   ArrayList<Menu_Item> menu = new ArrayList<>();

    Eateries_list(String name, String location, String hours, int photoId, ArrayList<Menu_Item> m /*, float x, float y*/) {
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.photoId = photoId;
        this.menu = m;
//        this.coord[0]=x;
//        this.coord[1]=y;
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
}

