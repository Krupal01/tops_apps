package com.example.ui_control;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ui_control.Fragment.main;
import com.example.ui_control.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            Fragment fragment = new main();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame , fragment)
                    .commit();

    }


}