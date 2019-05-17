package com.felhr.serialportexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class LogAktivity extends AppCompatActivity {
    private WebView webView;
    ImageView tombol;
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
