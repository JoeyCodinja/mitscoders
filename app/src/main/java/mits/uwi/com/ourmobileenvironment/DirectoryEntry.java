package mits.uwi.com.ourmobileenvironment;

import java.util.ArrayList;

/**
 * Created by rox on 8/1/15.
 */
public class DirectoryEntry {
    private String name,num,title,department;
    private int icon;
    private static ArrayList<DirectoryEntry> mDirectories=new ArrayList<>();

    public DirectoryEntry(String name,String num,String title,String department,int icon){
        this.name=name;
        this.department=department;
        this.num=num;
        this.title=title;
        this.icon=icon;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public String getTitle() {
        return title;
    }

    public String getDepartment() {
        return department;
    }

    public int getIcon() {
        return icon;
    }

    public static ArrayList<DirectoryEntry> getmDirectories() {
        populate();
        return mDirectories;
    }

    public static void populate()
    {
        mDirectories.clear();
        mDirectories.add(new DirectoryEntry("Ernesto Abram","987-8900","Director","Student Services",R.drawable.buntu));
        mDirectories.add(new DirectoryEntry("EMark Zuckerberg","987-8900","Director","Student Services",R.drawable.cream));
        mDirectories.add(new DirectoryEntry("Bill gatessss","987-8900","Director","Student Services",R.drawable.dropbox));
        mDirectories.add(new DirectoryEntry("Steve Jobssss","987-8900","Director","Student Services",R.drawable.purple));
        mDirectories.add(new DirectoryEntry("Elon Mosssssk","987-8900","Director","Student Services",R.drawable.red));
        mDirectories.add(new DirectoryEntry("Carl Segaaaaan","987-8900","Director","Student Services",R.drawable.whatsapp));

    }
}
