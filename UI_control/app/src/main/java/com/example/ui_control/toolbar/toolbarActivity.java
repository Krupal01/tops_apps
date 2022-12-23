package com.example.ui_control.toolbar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui_control.databinding.ActivityToolbarBinding;

public class toolbarActivity extends AppCompatActivity {

    private ActivityToolbarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityToolbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportActionBar().hide();
    }
}