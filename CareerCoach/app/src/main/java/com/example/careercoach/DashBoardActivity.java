package com.example.careercoach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.careercoach.afterWhat.After10HostActivity;
import com.example.careercoach.afterWhat.After12HostActivity;
import com.example.careercoach.allCollegesList.AllCollegesActivity;
import com.example.careercoach.databinding.ActivityDashBoardBinding;

import com.example.careercoach.different_careers.CareerHostActivity;
import com.example.careercoach.different_colleges.CollegeListActivity;
import com.example.careercoach.different_courses.CourseHostActivity;
import com.example.careercoach.different_exams.ExamHostActivity;
import com.example.careercoach.jobs.JobsActivity;
import com.example.careercoach.news_feed.NewsFeedActivity;
import com.example.careercoach.settings.SettingsActivity;

public class DashBoardActivity extends AppCompatActivity {
    private ActivityDashBoardBinding binding;
    private Toast exitToast;
    long back_pressed;

    @Override
    public void onBackPressed() {
        if (back_pressed + 1000 > System.currentTimeMillis()){
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        else{
            Toast.makeText(getBaseContext(),
                    "Press once again to exit!", Toast.LENGTH_SHORT)
                    .show();
        }
        back_pressed = System.currentTimeMillis();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Career Coach");

        binding=ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.imageView2.setOnClickListener(v -> {
            Intent intent=new Intent(this, CollegeListActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        });

        binding.imageView6.setOnClickListener(v -> {

            Intent intent=new Intent(this, ExamHostActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

        });
        binding.imageView7.setOnClickListener(v -> {
            Intent intent=new Intent(this,AboutUsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        });
        binding.tvAfter12.setOnClickListener(v -> {
            Intent intent=new Intent(this, After12HostActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        });
        binding.tvAfter10.setOnClickListener(v -> {
            Intent intent=new Intent(this, After10HostActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        });
        binding.imageView3.setOnClickListener(v -> {
            Intent intent=new Intent(this, CourseHostActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        });
        binding.imageView4.setOnClickListener(v -> {
            Intent intent=new Intent(this, CareerHostActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        });
        binding.tvAllColleges.setOnClickListener(v -> {
            Intent intent=new Intent(this, AllCollegesActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        });
        binding.imageViewJobs.setOnClickListener(v -> {
            Intent intent=new Intent(this, JobsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_settings){
           Intent intent=new Intent(this, SettingsActivity.class);
           startActivity(intent);
        }
        if(item.getItemId()==R.id.action_news){
            Intent intent=new Intent(this, NewsFeedActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}