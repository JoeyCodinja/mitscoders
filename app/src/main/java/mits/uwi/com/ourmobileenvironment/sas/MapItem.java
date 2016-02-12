package mits.uwi.com.ourmobileenvironment.sas;

/**
 * Created by peoplesoft on 2/10/2016.
 */
public class MapItem {
    private char type;
    private double latitude;
    private double longtitude;
    private String Title, Description;

    public MapItem ()
    {

    }
    public MapItem( char type, String description, double latitude, double longtitude) {
        this.type = type;
        this.latitude = latitude;
        this.longtitude = longtitude;
        if (type=='C')
            Title = "Class Location";
        else if (type =='A')
            Title = "Administrative Building";
        else
            Title = "Important Location";
        Description = description;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
        if (type=='C')
            Title = "Class Location";
        else if (type =='A')
            Title = "Administrative Building";
        else
            Title = "Important Location";
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "MapItem{" +
                "type=" + type +
                ", latitude=" + latitude +
                ", longtitude=" + longtitude +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
