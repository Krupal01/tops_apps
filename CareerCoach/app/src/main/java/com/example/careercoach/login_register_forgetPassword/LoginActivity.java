package com.example.careercoach.login_register_forgetPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.careercoach.DashBoardActivity;
import com.example.careercoach.R;
import com.example.careercoach.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkLogin();

        firebaseAuth=FirebaseAuth.getInstance();



        binding.button2.setOnClickListener(v -> {
            Intent intent=new Intent(this,RegistrationActivity.class);
            startActivity(intent);
        });
        binding.tvNewUser.setOnClickListener(v -> {
            Intent intent=new Intent(this,RegistrationActivity.class);
            startActivity(intent);
        });

        binding.forgetPassword.setOnClickListener(v -> {
            Intent intent=new Intent(this,ForgetPasswordActivity.class);
            startActivity(intent);
        });

        binding.button2.setOnClickListener(v -> {
            String email=binding.email.getText().toString();
            String password=binding.password.getText().toString();



            if(email.equals("")||password.equals("") || !isEmailValid(email)){
                Toast.makeText(this, "Empty Field of Invalid Email", Toast.LENGTH_SHORT).show();
            }else {


                    firebaseAuth.signInWithEmailAndPassword(email,password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
                                    SharedPreferences.Editor editor=sharedPreferences.edit();

                                    editor.putBoolean("isLogin",true);
                                    editor.commit();

                                    Toast.makeText(LoginActivity.this, "Welcome User", Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(LoginActivity.this, DashBoardActivity.class);
                                    startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });
    }

    private void checkLogin() {
        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        boolean isLogin=sharedPreferences.getBoolean("isLogin",false);
        if(isLogin){
            Intent intent=new Intent(this,DashBoardActivity.class);
            startActivity(intent);
        }

    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}