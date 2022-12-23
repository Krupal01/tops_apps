package com.example.careercoach.different_colleges;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.careercoach.R;
import com.example.careercoach.databinding.ActivityCollegeListBinding;

public class CollegeListActivity extends AppCompatActivity {
    private ActivityCollegeListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCollegeListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}