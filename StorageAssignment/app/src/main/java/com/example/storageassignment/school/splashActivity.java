package com.example.storageassignment.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.storageassignment.R;
import com.example.storageassignment.databinding.ActivitySplashBinding;

public class splashActivity extends AppCompatActivity implements Runnable {

    private ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        //full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //no title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(binding.getRoot());

        Handler handler = new Handler();
        handler.postDelayed( this, 3000);
    }

    @Override
    public void run() {
        Intent intent = new Intent(this , schoolActivity.class);
        startActivity(intent);

        finish();
    }
}