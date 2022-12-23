package com.example.ui_control.Whatapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.ui_control.databinding.ActivityWpBinding;

import java.util.ArrayList;

public class WpActivity extends AppCompatActivity {

    private ActivityWpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add( new chatFragment());
        fragments.add(new stausFragment());
        fragments.add(new callsFragment());


        ArrayList<String> title = new ArrayList<>();
        title.add("chat");
        title.add("status");
        title.add("calls");


        pagerAdapter adapter = new pagerAdapter(getSupportFragmentManager() , fragments, title);
        binding.viewPager.setAdapter(adapter);

        binding.tabLayout.setupWithViewPager(binding.viewPager);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle(title.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}