package com.example.ui_control;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui_control.databinding.ActivityLocalHTML2Binding;

public class localHTMLActivity2 extends AppCompatActivity {

    private ActivityLocalHTML2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocalHTML2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.localHTML.loadUrl("file:///android_asset/text1.html");
        binding.localHTML.setWebViewClient(new Webclass());
    }

    private class Webclass extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}