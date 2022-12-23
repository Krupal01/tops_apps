package com.example.project.admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.databinding.ActivityAdminLoginBinding;
import com.example.project.items.Admin;
import com.example.project.items.Complain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class AdminLoginActivity extends AppCompatActivity {

    private ActivityAdminLoginBinding binding;
    boolean isAdmin = true;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Login For Admin");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);


        binding.AdminLogin.setOnClickListener(v -> {
            FirebaseDatabase database=FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("Admin");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Iterator<DataSnapshot> it = snapshot.getChildren().iterator();
                    while(it.hasNext()){
                        DataSnapshot childSnapShot = it.next();
                        String name = childSnapShot.getKey().toString();
                        String Password=childSnapShot.getValue(String.class);

                        if (name.equals(binding.UserId.getText().toString()) && Password.equals(binding.Password.getText().toString())){
                            isAdmin = true;
                            Intent intent = new Intent(getApplicationContext(),AdminMainActivity.class);
                            startActivity(intent);

                            finish();
                        }
                        else {
                            isAdmin = false;
                        }

                    }
                    if (isAdmin == false){
                        Toast.makeText(getApplicationContext(),"Enter Valid Username and Password",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.i("project", "Failed. "+error);
                }
            });
        });


    }
}