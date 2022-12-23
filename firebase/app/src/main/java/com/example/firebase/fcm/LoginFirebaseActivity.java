package com.example.firebase.fcm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityLoginFirebaseBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFirebaseActivity extends AppCompatActivity {

    private ActivityLoginFirebaseBinding binding;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginFirebaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.Register.setOnClickListener(v -> {
            Intent intent = new Intent(this,RegistrationActivity.class);
            startActivity(intent);

            finish();
        });

        binding.submit.setOnClickListener(v -> {
            firebaseAuth = FirebaseAuth.getInstance();

            String Username = binding.Username.getText().toString();
            String Password = binding.Password.getText().toString();

            if (!Username.isEmpty() && !Password.isEmpty() && Password.length()>=6){
                firebaseAuth.signInWithEmailAndPassword(Username,Password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent intent = new Intent(getApplicationContext(),UserListActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }else {
                Toast.makeText(this, "please add valid information", Toast.LENGTH_SHORT).show();
            }
        });



    }
}