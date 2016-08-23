package mits.uwi.com.ourmobileenvironment.additional_systems.Evaluations.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mits.uwi.com.ourmobileenvironment.R;

/**
 * Created by peoplesoft on 4/11/2016.
 */

/**
 * 
 * 
 * Created by jbutler on 4/11/2016.
 */
public class TeachingEvalsWebView  extends Fragment {
    WebView mTeachEvals;

    String url = "http://evals.mona.uwi.edu/StudentLoginJ.asp";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_teaching_eval, container, false);
        mTeachEvals = (WebView) v.findViewById(R.id.evals);

        mTeachEvals.getSettings().setJavaScriptEnabled(true);
        mTeachEvals.getSettings().setBuiltInZoomControls(true);

        mTeachEvals.setWebViewClient(new WebViewClient());
        mTeachEvals.loadUrl(url);

        mTeachEvals.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((i == KeyEvent.KEYCODE_BACK) && mTeachEvals.canGoBack()) {
                    mTeachEvals.goBack();
                    return true;
                }
                return false;

            }
        });

        return v;
    }

    private class SwAWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }

}

