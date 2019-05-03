package com.felhr.serialportexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LogAktivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_log_aktivity);

        webView = (WebView) this.findViewById(R.id.webView);
        webView.setWebViewClient (new WebViewClient ());
        webView.loadUrl("http://ykeyless.serveo.net/logaktivity");
        WebSettings webSettings = webView. getSettings ();
        webSettings.setJavaScriptEnabled (true);
    }



}
