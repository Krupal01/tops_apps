package com.example.firebase.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityZoomBinding;

public class ZoomActivity extends AppCompatActivity {

    private ActivityZoomBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityZoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.zoom);
        binding.imageView.startAnimation(animation);

    }
}