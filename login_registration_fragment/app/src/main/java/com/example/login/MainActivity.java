package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.login.fragment.loginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            Fragment fragment = new loginFragment();
            FragmentManager m = getSupportFragmentManager();
            FragmentTransaction transaction = m.beginTransaction();
            transaction.replace(R.id.frame , fragment);
            transaction.addToBackStack(loginFragment.class.getName());
            transaction.commit();


    }
}