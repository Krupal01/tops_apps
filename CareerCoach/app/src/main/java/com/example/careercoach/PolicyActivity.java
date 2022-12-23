package com.example.careercoach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;

import com.example.careercoach.databinding.ActivityPolicyBinding;

public class PolicyActivity extends AppCompatActivity {
    private ActivityPolicyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Privacy Policy");
        binding=ActivityPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //webView Performance

        binding.webViewPolicy.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            binding.webViewPolicy.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            binding.webViewPolicy.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        binding.webViewPolicy.loadUrl("file:///android_asset/policy.html");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}