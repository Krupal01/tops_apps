package com.example.ui_control;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui_control.databinding.ActivityWebViewBinding;

public class webView extends AppCompatActivity {

    private ActivityWebViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        binding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("web");
//        binding.WV.loadUrl("https://www.google.com/");
//        binding.WV.setWebViewClient(new MyWebviewClient());
//        binding.WV.getSettings().setJavaScriptEnabled(true);
//    }
//
//    class MyWebviewClient extends WebViewClient{
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            super.onPageStarted(view, url, favicon);
//            binding.progressBar.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//            binding.progressBar.setVisibility(View.GONE);
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            return super.shouldOverrideUrlLoading(view, request);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        if(binding.WV.canGoBack()){
//            binding.WV.goBack();
//        }else {
//            super.onBackPressed();
//        }
//    }
//
        Intent intent = getIntent();
        String url2 = intent.getStringExtra("url");
        binding.WV.setWebViewClient(new WebViewClient());
        binding.WV.loadUrl("https://"+ url2 + "/");
        binding.WV.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(binding.WV.canGoBack()){
            binding.WV.goBack();
        }else {
            super.onBackPressed();
        }
    }
}