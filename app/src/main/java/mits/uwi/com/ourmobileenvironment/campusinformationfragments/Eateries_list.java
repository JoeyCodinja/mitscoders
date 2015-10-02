package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

/**
 * Created by Leonardo on 7/6/2015.
 */
class Eateries_list {
    String name;
    String location;
    String hours;
    int photoId;

    Eateries_list(String name, String location, String hours, int photoId) {
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.photoId = photoId;
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


}

