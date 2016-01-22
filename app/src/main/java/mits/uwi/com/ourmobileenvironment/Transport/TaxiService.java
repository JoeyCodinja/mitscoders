package mits.uwi.com.ourmobileenvironment.Transport;

import com.orm.dsl.Ignore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rox on 1/10/16.
 */
public class TaxiService extends Transport{
    private String Name;
    private String Email;
    private String SerialisedContact;

    @Ignore
    private ArrayList<String> Numbers;

    public  List<Transport> getTaxiList() {

        return (List) TaxiService.listAll(TaxiService.class);
    }

    public TaxiService(){ }

    public TaxiService(String Name,String Email,ArrayList<String> Numbers){
        this.Name=Name;
        this.Email=Email;
        this.Numbers = Numbers;
    }
    public void SerialiseList(){
        SerialiseContacts();
    }
    public void DeserialiseList(){
        DeserialiseContacts();

    }
    @Override
    public  List<Transport>getTransportList(){
        return getTaxiList();
    }

    public void SerialiseContacts(){
        this.SerialisedContact= android.text.TextUtils.join(",", Numbers);
    }

    public void DeserialiseContacts(){
        String[] contacts=android.text.TextUtils.split(SerialisedContact,",");
        this.Numbers = new ArrayList<>(Arrays.asList(contacts));
    }


    public String getSerialisedContact() {
        return SerialisedContact;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public ArrayList<String> getNumbers() {
        return Numbers;
    }

    @Override
    public String toString(){
        return getName()+","+getEmail()+","+getNumbers().toString();
    }

    @Override
    public String getSearchField(){
        return getName();
    }






}
