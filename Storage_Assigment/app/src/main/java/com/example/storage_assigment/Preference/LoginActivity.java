package com.example.storage_assigment.Preference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.storage_assigment.MainActivity;
import com.example.storage_assigment.R;
import com.example.storage_assigment.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkLogin();

        binding.btnLogin.setOnClickListener(v -> {
            String name = binding.etName.getText().toString();
            String password = binding.etPassword.getText().toString();

            if(name.equals("admin") && password.equals("123456")) {
                SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", name);
                editor.putString("Password", password);
                editor.putBoolean("isLogin", true);

                Toast.makeText(this, "Login sucess", Toast.LENGTH_SHORT);

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void checkLogin() {
            SharedPreferences preferences = getSharedPreferences("Login",MODE_PRIVATE);
            boolean isLogin=preferences.getBoolean("isLogin",false);
            if(isLogin){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }

    }
}