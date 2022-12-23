package com.example.realmstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.realmstorage.note.noteListFragment;
import com.example.realmstorage.task.TaskFragment;
import com.example.realmstorage.taska.AtaskFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame,new AtaskFragment())
                .commit();
    }
}