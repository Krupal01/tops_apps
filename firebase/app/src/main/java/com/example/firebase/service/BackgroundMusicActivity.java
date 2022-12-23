package com.example.firebase.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.firebase.databinding.ActivityBackgroundMusicBinding;

public class BackgroundMusicActivity extends AppCompatActivity {
    private Intent intent;
    private ActivityBackgroundMusicBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBackgroundMusicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = new Intent(this,MusicService.class);
        startService(intent);

    }

    @Override
    protected void onPause() {
        stopService(intent);
        super.onPause();
    }
}