package mits.uwi.com.ourmobileenvironment.Transport;

import com.orm.SugarRecord;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rox on 1/11/16.
 */
public  abstract  class Transport extends SugarRecord {
    public  List<Transport> getTransportList(){
        return new ArrayList<>();
    }
    public abstract void SerialiseList();
    public abstract void DeserialiseList();
    public abstract String getSearchField();
}
