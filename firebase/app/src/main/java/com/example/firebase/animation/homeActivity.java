package com.example.firebase.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityHomeBinding;

public class homeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.Blink.setOnClickListener(v -> {
            Intent intent = new Intent(this,BlinkActivity.class);
            startActivity(intent);

            finish();
        });

        binding.move.setOnClickListener(v -> {
            Intent intent = new Intent(this,MoveActivity.class);
            startActivity(intent);

            finish();
        });
        binding.Rotate.setOnClickListener(v -> {
            Intent intent = new Intent(this,RatateActivity.class);
            startActivity(intent);

            finish();
        });
        binding.zoom.setOnClickListener(v -> {
            Intent intent = new Intent(this,ZoomActivity.class);
            startActivity(intent);

            finish();
        });


    }
}