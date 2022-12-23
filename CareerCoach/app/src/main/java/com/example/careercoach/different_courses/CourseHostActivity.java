package com.example.careercoach.different_courses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.careercoach.R;

public class CourseHostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_host);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}