package com.example.project.admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.databinding.ActivityNoticeBoardBinding;
import com.example.project.items.Notice;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoticeBoardActivity extends AppCompatActivity {

    private ActivityNoticeBoardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoticeBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Notice Board");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);



        binding.Submit.setOnClickListener(v -> {
            String title = binding.NoticeTitle.getText().toString();
            String notice = binding.notice.getText().toString();
            //firebase notice data update
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("CrimeTracker").child("NoticeBoard");
            Notice not = new Notice(title,notice);
            reference.child(title).setValue(not);
            Toast.makeText(getApplicationContext(),"Notice Added Sucess",Toast.LENGTH_LONG).show();
            binding.NoticeTitle.setText("");
            binding.notice.setText("");
        });

    }
}