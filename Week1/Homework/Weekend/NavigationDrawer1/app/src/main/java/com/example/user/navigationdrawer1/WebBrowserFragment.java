package com.example.user.navigationdrawer1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebBrowserFragment extends Fragment {

    public WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.web_browser, container, false);
        mWebView = (WebView) v.findViewById(R.id.wbBrowser);
        mWebView.loadUrl("https://google.com");

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
