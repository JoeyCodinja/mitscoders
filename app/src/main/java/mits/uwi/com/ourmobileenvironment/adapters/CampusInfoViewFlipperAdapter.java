package mits.uwi.com.ourmobileenvironment.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.IDocumentLayout;
import com.bluejamesbond.text.StringDocumentLayout;
import com.bluejamesbond.text.style.TextAlignment;

/**
 * Created by Danuel on 20/8/2016.
 */

public class CampusInfoViewFlipperAdapter implements Adapter {

    Context context;
    SnippetObject[] objects;

    public CampusInfoViewFlipperAdapter(String[] titles, String[] children, Context context){
        this.context = context;
        objects = new SnippetObject[titles.length];
        for (int index=0; index < titles.length; index++){
            objects[index] = new SnippetObject(titles[index],
                    children[index]);

        }
    }

    private class SnippetObject extends Object{
        private String title;
        private String snippet;

        public SnippetObject(String title,
                             String snippet){
            this.title = title;
            this.snippet = snippet;
        }

        public String getTitle(){
            return title;
        }

        public String getSnippet(){
            return snippet;
        }
    }

    private View FlipperView(){
        LinearLayout parent = new LinearLayout(context);
        TextView titleText = new TextView(context);
        DocumentView snippetText = new DocumentView(context);
        parent.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams layout_items =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        snippetText.setLayoutParams(layout_items);
        titleText.setLayoutParams(layout_items);

        titleText.setPadding(10, 0, 0, 0);

        StringDocumentLayout.LayoutParams layout = snippetText.getDocumentLayoutParams();
        layout.setAntialias(true);
        layout.setTextAlignment(TextAlignment.JUSTIFIED);
        layout.setInsetPaddingLeft(20f);
        layout.setInsetPaddingRight(20f);

        parent.addView(titleText);
        parent.addView(snippetText);

        return parent;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return objects.length;
    }

    @Override
    public Object getItem(int position) {
        return objects[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SnippetObject objectAtPosition = objects[position];

        LinearLayout flipperView = (LinearLayout)FlipperView();
        ((TextView)flipperView.getChildAt(0)).setText(objectAtPosition.getTitle());
        ((DocumentView)flipperView.getChildAt(1)).setText(objectAtPosition.getSnippet());

        return flipperView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return objects.length;
    }

    @Override
    public boolean isEmpty() {
        return objects.length == 0;
    }

}
