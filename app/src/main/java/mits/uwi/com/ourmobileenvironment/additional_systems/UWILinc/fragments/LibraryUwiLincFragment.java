package mits.uwi.com.ourmobileenvironment.additional_systems.UWILinc.fragments;

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
 * Created by peoplesoft on 4/9/2016.
 */
public class LibraryUwiLincFragment extends Fragment {
    WebView mUWILinc;
    String url = "http://uwin-primo.hosted.exlibrisgroup.com/primo_library/libweb/action/search.do?vid=MON";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_uwi_linc,container,false);
        mUWILinc = (WebView)v.findViewById(R.id.uwi_linc);

        mUWILinc.getSettings().setJavaScriptEnabled(true);
        mUWILinc.getSettings().setBuiltInZoomControls(true);

        mUWILinc.setWebViewClient(new WebViewClient());
        mUWILinc.loadUrl(url);

        return v;
    }

    private class SwAWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            return false;
        }
    }
}
