package mits.uwi.com.ourmobileenvironment.adapters;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import mits.uwi.com.ourmobileenvironment.CampusInformationSubActivity;
import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.UWIInformationFragments.Faculties;
import mits.uwi.com.ourmobileenvironment.faculty.FacultyDownloader;
import mits.uwi.com.ourmobileenvironment.faculty.FacultyInfo;

/**
 * Created by Danuel on 14/10/2016.
 */
public class FacultyExpandableListAdapter implements ExpandableListAdapter,
        ExpandableListView.OnChildClickListener,
        Faculties.DownloadCoordinator{

    Map<String, Object> faculties_info;
    ArrayList<String> groupHeadings;
    Context ctx;
    public String faculty;

    @SuppressWarnings("unchecked")
    public FacultyExpandableListAdapter(Context ctx, FacultyInfo faculties_info, String faculty){
        this.faculty = faculty;
        this.faculties_info = (Map<String,Object>)faculties_info.facultyValues.get(faculty);
        this.ctx = ctx;

        Set<String> keySetIntermediary = this.faculties_info.keySet();

        groupHeadings = new ArrayList<>();
        for (String heading: keySetIntermediary){
            if (!heading.equals("About") && !heading.equals("Name")){
                groupHeadings.add(heading);
            }
        }
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return groupHeadings.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public int getChildrenCount(int groupPosition) {
        String key = (String)groupHeadings.get(groupPosition);

        if (key.equals("About") || key.equals("SocialMedia"))
            return 1;

        if (key.equals(("Resources"))){
            Set<String> resourcesChildren = ((Map<String,Object>)this.faculties_info.get(key)).keySet();
            int exclusionCount = 0;
            for (String children: resourcesChildren){
                if (children.contains("img")){
                    exclusionCount++;
                }
            }
            return ((Map<String,Object>)faculties_info.get(key)).size()-exclusionCount;
        }

        return ((Map<String, Object>)faculties_info.get(key)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupHeadings.get(groupPosition);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object getChild(int groupPosition, int childPosition) {
        String key = (String)getGroup(groupPosition);
        ArrayList<Object> subKeyList = new ArrayList<>();
        Object[] subKeyIntermediate = ((Map<String, Object>)faculties_info.get(key)).keySet().toArray();
        for (Object subKeyItem: subKeyIntermediate){
            if ( !((String)subKeyItem).contains("img") )
                subKeyList.add(subKeyItem);
        }

        String subKey = (String)subKeyList.get(childPosition);

        Object returnValue;

        switch(key){
            case "About":
                returnValue =  faculties_info.get(key);
                break;
            case "Resources":
            case "SocialMedia":
                returnValue = ((Map<String,Object>)faculties_info.get(key)).get(subKey);
                break;
            default:
                returnValue = null;
                break;
        }

        return returnValue;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition,
                             boolean isExpanded,
                             View convertView,
                             ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View groupView = inflater.inflate(R.layout.list_group_faculty_information, null);
        TextView groupTitle = (TextView)groupView.findViewById(R.id.group_name);
        String groupText = (String)groupHeadings.get(groupPosition);
        if (groupText.equals("SocialMedia"))
            groupText = "Social Media";
        groupTitle.setText(groupText);
        return groupView;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getChildView(int groupPosition,
                             int childPosition,
                             boolean isLastChild,
                             View convertView,
                             ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View childLayout = null;
        Animation easeIn = AnimationUtils.loadAnimation(ctx, R.anim.ease_fade_in_right);

        switch((String)getGroup(groupPosition)){
            case "SocialMedia":
                childLayout = inflater.inflate(R.layout.social, null);
                ImageView[] socialMediaButtons = new ImageView[4];
                String socialMediaURL;
                socialMediaButtons[0] = (ImageView)childLayout.findViewById(R.id.twitterButton);
                socialMediaButtons[1] = (ImageView)childLayout.findViewById(R.id.facebookButton);
                socialMediaButtons[2] = (ImageView)childLayout.findViewById(R.id.snapchatButton);
                socialMediaButtons[3] = (ImageView)childLayout.findViewById(R.id.instagramButton);

                for (ImageView socialMediaButton: socialMediaButtons){
                    switch(socialMediaButton.getId()){
                        case R.id.twitterButton:
                            socialMediaURL = (String)(((Map<String,Object>)
                                    faculties_info.get(getGroup(groupPosition))).get("Twitter"));
                            break;
                        case R.id.facebookButton:
                            socialMediaURL = (String)(((Map<String,Object>)
                                    faculties_info.get(getGroup(groupPosition))).get("Facebook"));
                            break;
                        case R.id.instagramButton:
                            socialMediaURL = (String)(((Map<String,Object>)
                                    faculties_info.get(getGroup(groupPosition))).get("Instagram"));
                        case R.id.snapchatButton:
                            socialMediaURL = (String)(((Map<String,Object>)
                                    faculties_info.get(getGroup(groupPosition))).get("Snapchat"));
                            break;
                        default:
                            socialMediaURL = "";
                            break;
                    }
                    socialMediaButton.setTag(socialMediaURL);
                    socialMediaButton.setOnClickListener(new SocialMediaOnClickListener());
                }
                break;
            case "Resources":

                childLayout = inflater.inflate(R.layout.list_child_faculty_information, null);


                String name = ((Map<String,Object>)getChild(groupPosition, childPosition))
                        .get("name").toString();
                TextView nameListing = (TextView)childLayout.findViewById(R.id.child_content);
                ImageView downloadIndicator = new ImageView(ctx);
                if (Build.VERSION.SDK_INT >= 21){
                    downloadIndicator
                            .setImageDrawable(ctx.getDrawable(R.drawable.ic_get_app_black_24dp));
                }else{
                    downloadIndicator
                            .setImageDrawable(ctx.getResources()
                                    .getDrawable(R.drawable.ic_get_app_black_24dp));
                }
                LinearLayout.LayoutParams layout =
                        (LinearLayout.LayoutParams)nameListing.getLayoutParams();
                layout.weight = 1.0f;
                nameListing.setText(name);
                ((LinearLayout)childLayout).addView(downloadIndicator, layout);
                break;
            case "About":
                childLayout = inflater.inflate(R.layout.list_child_faculty_information, null);
                TextView about = (TextView)childLayout.findViewById(R.id.child_content);
                String aboutText = (String)faculties_info.get((String)getGroup(groupPosition));
                about.setText(aboutText);
                break;
            default:
                break;
        }

        childLayout.setAnimation(easeIn);
        childLayout.startAnimation(easeIn);

        return childLayout;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        if (getGroup(groupPosition).equals("Resources")){
            String clickedResources = (String)((Map<String,Object>)getChild(groupPosition, childPosition))
                    .get("name");
            if (clickedResources.contains("Handbook")){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        if (faculties_info.size() == 0 ){
            return true;
        }
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean onChildClick(ExpandableListView parent,
                                View v,
                                int groupPosition,
                                int childPosition,
                                long id) {
        if(isChildSelectable(groupPosition, childPosition)){
            if (getGroup(groupPosition).equals("Resources")){
                Map<String,Object> resources = (Map<String,Object>)getChild(groupPosition, childPosition);
                if (((String)resources.get("name")).contains("Handbook")){
                    downloadResource((String)resources.get("url"),
                            ((FacultyExpandableListAdapter)parent
                                    .getExpandableListAdapter())
                                    .faculty);
                }

            }
        }
        return isChildSelectable(groupPosition, childPosition);
    }

    @Override
    public boolean downloadResource(String uri, String resourceId) {
        try {
            FacultyDownloader downloader =
                    new FacultyDownloader(
                            (DownloadManager)ctx.getSystemService(Context.DOWNLOAD_SERVICE));
            if (!fileAlreadyExists(resourceId +"faculty_handbook.pdf")){
                DownloadManager.Request downloadRequest = downloader.createRequest(Uri.parse(uri),
                        resourceId + "faculty_handbook.pdf");
                downloader.queueDownload(downloadRequest);
            }
            else{
                Toast.makeText(this.ctx.getApplicationContext(),
                        "Resource already downloaded",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        }catch(IllegalArgumentException e){
            Toast.makeText(this.ctx.getApplicationContext(),
                    "No Download available",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

    @Override
    public boolean fileAlreadyExists(String filename) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        for (File file: dir.listFiles()){
            if (file.getName().equals(filename))
                return true;
        }
        return false;
    }

    private class SocialMediaOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String url = (String)v.getTag();
            if (url != null && !url.equals("")){
                Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(i);
            }
            else
               Toast.makeText(ctx,
                       "This faculty does not exist on this social media",
                       Toast.LENGTH_LONG)
                       .show();
        }
    }
}
