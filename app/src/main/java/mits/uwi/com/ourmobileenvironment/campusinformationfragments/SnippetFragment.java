package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by Danuel on 22/07/2015.
 */
public class SnippetFragment extends Fragment {

    public static final String EXTRA_SNIPPET_BODY = "com.mits.uwi.ourmobileenvironment.snippet_body";
    public static final String EXTRA_SNIPPET_TITLE = "com.mits.uwi.ourmobileenvironment.snippet_title";

    public String mSnippetBody;
    public String mSnippetTitle;

    public DocumentView mSnippetBodyView;
    public TextView mSnippetTitleView;

    public SnippetFragment() {

    }

    public static SnippetFragment newInstance(String snippetTitle, String snippetBody){
        Bundle args = new Bundle();
        args.putCharSequence(EXTRA_SNIPPET_BODY, snippetBody);
        args.putCharSequence(EXTRA_SNIPPET_TITLE, snippetTitle);

        SnippetFragment fragment = new SnippetFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSnippetBody = (String)getArguments().getCharSequence(EXTRA_SNIPPET_BODY);
        mSnippetTitle = (String)getArguments().getCharSequence(EXTRA_SNIPPET_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_snippet,container, false);

        mSnippetTitleView = (TextView) v.findViewById(R.id.snippet_title);
        mSnippetTitleView.setText(mSnippetTitle);

        mSnippetBodyView = (DocumentView) v.findViewById(R.id.snippet_body);
        mSnippetBodyView.setText(mSnippetBody);

        return v;
    }
}
