package com.example.firebase.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityBlinkBinding;

public class BlinkActivity extends AppCompatActivity {

    private ActivityBlinkBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBlinkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.blink);
        binding.imageView.startAnimation(animation);
    }
}