package com.example.feb22online;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.feb22online.databinding.ActivityNewUserBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewUserActivity extends AppCompatActivity {
    private ActivityNewUserBinding binding;
    private static final String TAG = "NewUserActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityNewUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(v->{
            insertNewUser();
        });
    }

    private void insertNewUser() {
        ProjectService service=ProjectRetrofitClient.getService();
        String firstName=binding.etFirstName.getText().toString();
        String lastName=binding.etLastName.getText().toString();
        String email=binding.etEmail.getText().toString();
        String mobile=binding.etMobile.getText().toString();

        Call<String> call=service.insertUser(firstName, lastName,email, mobile);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String msg=response.body();
                Toast.makeText(NewUserActivity.this, msg, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(NewUserActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, t.toString());
            }
        });

    }
}