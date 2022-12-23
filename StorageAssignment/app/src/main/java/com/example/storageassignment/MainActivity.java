package com.example.storageassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

import com.example.storageassignment.fragment.AssertDatabaseFragment;
import com.example.storageassignment.fragment.BackUpFragment;
import com.example.storageassignment.taska.ANewTaskFragment;
import com.example.storageassignment.databinding.ActivityMainBinding;
import com.example.storageassignment.fragment.LoginFragment;
import com.example.storageassignment.fragment.NewTaskFragment;
import com.example.storageassignment.fragment.RegisterFragment;
import com.example.storageassignment.fragment.TaskListFragment;
import com.example.storageassignment.fragment.newNoteFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame,new AssertDatabaseFragment())
                .commit();
    }

}