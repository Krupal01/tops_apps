package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.project.admin.AdminSplashActivity;
import com.example.project.databinding.ActivityMainBinding;
import com.example.project.user.HomeActivity;
import com.example.project.user.MissingListActivity;
import com.example.project.user.RegisterUserActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Login");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);

        SharedPreferences sharedPreferences = getSharedPreferences("Islogin", Context.MODE_PRIVATE);
        Boolean IsLogin = sharedPreferences.getBoolean("Islogin",false);

        if(IsLogin==true){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);

            finish();
            Toast.makeText(this,"redirct to index" , Toast.LENGTH_SHORT).show();
        }

        mAuth = FirebaseAuth.getInstance();

        binding.Login.setOnClickListener(v -> {
            String email = binding.email.getText().toString();
            String password =  binding.Password.getText().toString();
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            SharedPreferences sharedPreferences1 = getSharedPreferences("Islogin",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences1.edit();
                            editor.putBoolean("Islogin",true);
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);

                            finish();
                            Toast.makeText(getApplicationContext(),"Wait" , Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("project",e.toString());
                            Toast.makeText(getApplicationContext(),"email or password is worng" , Toast.LENGTH_SHORT).show();
                        }
                    });

        });
        binding.Registration.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), RegisterUserActivity.class));
        });

        binding.ForgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity2.class));
        });

        binding.Admin.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AdminSplashActivity.class));
        });
    }
}