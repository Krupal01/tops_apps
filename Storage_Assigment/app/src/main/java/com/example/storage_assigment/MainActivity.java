package com.example.storage_assigment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.storage_assigment.Preference.ChangeBackgroundFragment;
import com.example.storage_assigment.Preference.LoginActivity;
import com.example.storage_assigment.Preference.RecyclerView.cartFragment;
import com.example.storage_assigment.Preference.RecyclerView.itemFragment;
import com.example.storage_assigment.Preference.intro_slider.PrefManager;
import com.example.storage_assigment.Preference.intro_slider.WelcomeActivity;
import com.example.storage_assigment.databinding.ActivityMainBinding;



import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            binding.btnLogout.setOnClickListener(v -> {
                SharedPreferences preferences = getSharedPreferences("Login",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.Frame,new itemFragment())
                .commit();

        PrefManager prefManager = new PrefManager(getApplicationContext());
        if(prefManager.isFirstTimeLaunch()){
            prefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.setting){
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Frame,new ChangeBackgroundFragment())
                .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}