package mits.uwi.com.ourmobileenvironment.sas.transcriptfragments;

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
 * Created by User on 10/8/2015.
 */
public class TranscriptRequestFragment extends Fragment {
    WebView mTranscript;
    String url = "http://www.google.com.jm/";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transcript_pdf,container,false);
        mTranscript = (WebView)v.findViewById(R.id.transcript);

        mTranscript.getSettings().setJavaScriptEnabled(true);
        mTranscript.getSettings().setBuiltInZoomControls(true);

        mTranscript.setWebViewClient(new WebViewClient());
        mTranscript.loadUrl(url);
        mTranscript.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((i == KeyEvent.KEYCODE_BACK) && mTranscript.canGoBack()) {
                    mTranscript.goBack();
                    return true;
                }
                return false;

            }
        });

        return v;
    }

    private class SwAWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            return false;
        }
    }
}
