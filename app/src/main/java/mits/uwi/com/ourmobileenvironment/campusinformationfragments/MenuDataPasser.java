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
}
