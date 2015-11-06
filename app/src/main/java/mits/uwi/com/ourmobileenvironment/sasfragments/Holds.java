package mits.uwi.com.ourmobileenvironment.sasfragments;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by User on 10/9/2015.
 */
public class Holds {
    private String HoldType, Reason,Originator, Processed_Affected;
    private Date FrmDt, ToDt;
    private Double Amount;
    private static ArrayList<Holds> mHolds = new ArrayList<>();

    public Holds(String holdType, String reason, String originator, String processed_Affected, Date frmDt, Date toDt, Double amount) {
        HoldType = holdType;
        Reason = reason;
        Originator = originator;
        Processed_Affected = processed_Affected;
        FrmDt = frmDt;
        ToDt = toDt;
        Amount = amount;
    }

    public String getHoldType() {
        return HoldType;
    }

    public void setHoldType(String holdType) {
        HoldType = holdType;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getOriginator() {
        return Originator;
    }

    public void setOriginator(String originator) {
        Originator = originator;
    }

    public String getProcessed_Affected() {
        return Processed_Affected;
    }

    public void setProcessed_Affected(String processed_Affected) {
        Processed_Affected = processed_Affected;
    }

    public Date getFrmDt() {
        return FrmDt;
    }

    public void setFrmDt(Date frmDt) {
        FrmDt = frmDt;
    }

    public Date getToDt() {
        return ToDt;
    }

    public void setToDt(Date toDt) {
        ToDt = toDt;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return
                " " + HoldType  +
                " " + Reason +
                " " + Originator  +
                " " + Processed_Affected  +
                " " + DateFormat.getDateInstance(DateFormat.MEDIUM).format(FrmDt) +
                " " + DateFormat.getDateInstance(DateFormat.MEDIUM).format(ToDt) +
                " " + Amount ;
    }

    public static ArrayList<Holds>getmHolds (){
        populate();
        return mHolds;
    }


    public static void populate()
    {
        Calendar myCalendar = new GregorianCalendar(2004, 7, 18);
        Calendar myCalendar1 = new GregorianCalendar(2004, 7, 18);
        Calendar myCalendar2 = new GregorianCalendar(2004, 7, 18);
        Calendar myCalendar3 = new GregorianCalendar(2004, 7, 18);
        Calendar myCalendar4 = new GregorianCalendar(2004, 7, 18);
        Calendar myCalendar5 = new GregorianCalendar(2004, 7, 18);
        Date fst = myCalendar.getTime();
        Date fst1 = myCalendar1.getTime();
        Date fst2 = myCalendar2.getTime();
        Date fst3 = myCalendar3.getTime();
        Date fst4 = myCalendar4.getTime();
        Date fst5 = myCalendar5.getTime();

        DateFormat ft = new SimpleDateFormat("dd-mmm-yy");
        mHolds.add(new Holds("No IDentification Card", "ID FINE","ADMIN","Grades", fst,fst1,250.00));
        mHolds.add(new Holds("No IDentification Card", "ID FINE","ADMIN","Grades", fst2,fst3,250.00));
        mHolds.add(new Holds("No IDentification Card", "ID FINE","ADMIN","Grades", fst4,fst5,250.00));

    }
}
