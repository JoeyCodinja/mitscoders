package mits.uwi.com.ourmobileenvironment.sasfragments.transcriptfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    String url = "https://www.google.com.jm/";
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

        return v;
    }

    private class SwAWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            return false;
        }
    }
}
