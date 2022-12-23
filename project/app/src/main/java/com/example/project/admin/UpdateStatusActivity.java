package com.example.project.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.databinding.ActivityUpdateStatusBinding;
import com.example.project.items.Complain;
import com.example.project.items.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateStatusActivity extends AppCompatActivity {

    private ActivityUpdateStatusBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateStatusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Update Status");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);

        binding.StatusUpdate.setOnClickListener(v -> {
            String complain = binding.complain.getText().toString();
            String status = binding.StatusText.getText().toString();
            //firebase
            FirebaseDatabase database=FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("CrimeTracker").child("Complain");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(complain)){
                        ref.child(complain).child("status").setValue(status);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"no data found to update",Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
    }
}