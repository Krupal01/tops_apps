package com.example.project.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.example.project.databinding.ActivityStatusCheckBinding;
import com.example.project.items.Complain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatusCheckActivity extends AppCompatActivity {

    private ActivityStatusCheckBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatusCheckBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Status");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        binding.FindStatus.setOnClickListener(v -> {
            String aim = binding.searchComplain.getText().toString();
            DatabaseReference ref = database.getReference("CrimeTracker").child("Complain").child(aim);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Complain complain1=snapshot.getValue(Complain.class);
                    binding.searchDiscription.setText(complain1.getDescription());
                    binding.status.setText(complain1.getStatus());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.i("project", "Failed. "+error);
                }
            });
        });
    }
}