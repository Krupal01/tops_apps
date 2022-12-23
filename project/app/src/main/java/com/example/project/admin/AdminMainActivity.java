package com.example.project.admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.project.R;
import com.example.project.databinding.ActivityAdminMainBinding;

public class AdminMainActivity extends AppCompatActivity {

    private ActivityAdminMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Admin Dashboard");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);

        binding.complian.setOnClickListener(v -> {
            Intent intent = new Intent(this,CheckComplainActivity.class);
            startActivity(intent);
        });
        binding.missing.setOnClickListener(v -> {
            Intent intent = new Intent(this,UpdateMissingActivity.class);
            startActivity(intent);
        });
        binding.notice.setOnClickListener(v -> {
            Intent intent = new Intent(this,NoticeBoardActivity.class);
            startActivity(intent);
        });
        binding.status.setOnClickListener(v -> {
            Intent intent = new Intent(this,UpdateStatusActivity.class);
            startActivity(intent);
        });
        binding.wanted.setOnClickListener(v -> {
            Intent intent = new Intent(this,UpdateWantedActivity.class);
            startActivity(intent);
            startActivity(intent);
        });


    }
}