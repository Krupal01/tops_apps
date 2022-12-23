package com.example.firebase.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //no title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(binding.getRoot());

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splash_animation);
        binding.imageView.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(this, 2000);

    }

    @Override
    public void run() {
        Intent intent = new Intent(this , homeActivity.class);
        startActivity(intent);

        finish();
    }
}