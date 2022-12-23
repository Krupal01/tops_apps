package com.example.ui_control;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui_control.databinding.ActivityObjectPass2Binding;

public class ObjectPassActivity2 extends AppCompatActivity {

    private ActivityObjectPass2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityObjectPass2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.passObject.loadUrl("file:///android_asset/objectPass.html");
    }

    private static class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void sh(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

    }
}