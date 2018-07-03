package com.example.user.navigationdrawer1;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

public class VideosFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.videos, container, false);
        WebView mWebView = (WebView) v.findViewById(R.id.wbVideo);
        mWebView.loadUrl("https://www.youtube.com/embed/k1U8N4Ogoks?autoplay=1&vq=small");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.web_browser, container, false);
        return v;
    }
}
