package com.example.storage1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.storage1.fragment.ListOfDataFragment;
import com.example.storage1.fragment.newUserFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame,new ListOfDataFragment())
                .commit();
    }
}