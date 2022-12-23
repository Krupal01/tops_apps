package com.example.firebase.fcm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class RegistrationActivity extends AppCompatActivity {


    private ActivityRegistrationBinding binding;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Uers");
        binding.register.setOnClickListener(v -> {
            firebaseAuth = FirebaseAuth.getInstance();

            String Firstname = binding.firstname.getText().toString();
            String Lastname = binding.lastname.getText().toString();
            String Email = binding.Email.getText().toString();
            String Mobile = binding.mobile.getText().toString();
            String Gender = binding.gender.getText().toString();
            String Password = binding.Password.getText().toString();

            user user = new user(Firstname,Lastname,Mobile,Email,Gender);

            if (!Email.isEmpty() && !Password.isEmpty() && Password.length()>=6) {

//                databaseReference.push().setValue(user);
                firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent intent = new Intent(getApplicationContext() , LoginFirebaseActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("registration",e.toString());
                            }
                        });
            }
            else {
                Toast.makeText(this, "please add full detile", Toast.LENGTH_SHORT).show();
            }

        });

    }
}