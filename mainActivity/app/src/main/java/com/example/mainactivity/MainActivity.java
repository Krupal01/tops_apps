package com.example.mainactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mainactivity.databinding.ActivityMainBinding;
import com.example.mainactivity.main.mainFragment;
import com.example.mainactivity.recycler.RecyclerMain;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fragment fragment = new mainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame , fragment)
                .commit();
    }


}