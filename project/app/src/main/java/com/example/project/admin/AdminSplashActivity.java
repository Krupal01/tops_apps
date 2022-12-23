package com.example.project.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.databinding.ActivityAdminSplashBinding;

public class AdminSplashActivity extends AppCompatActivity implements Runnable {

    private ActivityAdminSplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminSplashBinding.inflate(getLayoutInflater());
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(binding.getRoot());

        Handler handler = new Handler();
        handler.postDelayed(this , 2000);
    }


    @Override
    public void run() {
        Intent intent = new Intent(this,AdminLoginActivity.class);
        startActivity(intent);

        finish();
    }
}