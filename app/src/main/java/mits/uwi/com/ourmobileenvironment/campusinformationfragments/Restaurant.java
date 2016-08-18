package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by Leonardo on 7/6/2015.
 */
public class Restaurant extends SugarRecord {
    String name;
    String location;
    String businessHours;
    int photoId;
    boolean fav;
    double coordx;
    double coordy;
    private String imgurl;




    public Restaurant(){}


    Restaurant(String name, String location, String businessHours, int photoId, float coordx,float coordy,String imgurl) {
        this.name = name;
        this.location = location;
        this.businessHours = businessHours;
        this.photoId = photoId;
        this.coordx =coordx;
        this.coordy =coordy;
        this.fav = false;
        this.imgurl=imgurl;
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
    public double getCoordy() {
        return coordy;
    }
    public double getCoordx() {
        return coordx;
    }
    public String getBusinessHours() {
        return businessHours;
    }
    public String getImgurl() {
        return imgurl;
    }


}

