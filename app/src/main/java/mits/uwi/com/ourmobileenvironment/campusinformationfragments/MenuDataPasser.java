package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

/**
 * Created by Leonardo on 9/30/2015.
 */
public class MenuDataPasser {
    private static MenuDataPasser ourInstance = new MenuDataPasser();

    public static MenuDataPasser getInstance() {
        return ourInstance;
    }

    private MenuDataPasser() {
    }
    /*public static DataHolderClass getInstance() {
        if (dataObject == null)
            dataObject = new DataHolderClass();
        return dataObject;
    }*/
    private Eateries_list resName;

    public Eateries_list getResName() {
        return resName;
    }

    public void setResName(Eateries_list name) {
        this.resName = name;
    }

}
