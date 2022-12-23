package com.example.ui_control;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ui_control.databinding.ActivityRangOfNumberBinding;

public class RangOfNumber extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private TextView tvMessage;
    private TextView tvMessage1;
    private TextView range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rang_of_number);

        Intent intent=getIntent();
        String msg=intent.getStringExtra("msg");
        String msg1=intent.getStringExtra("msg1");
        Log.i(TAG, msg);
        Log.i(TAG, msg1);

        tvMessage=findViewById(R.id.tvMessage);
        tvMessage.setText("firstNumber "+msg);
        tvMessage1=findViewById(R.id.tvMessage1);
        tvMessage1.setText("secondNumber "+msg1);
        range = findViewById(R.id.range);
        range.setText("Range : ");

        int no1 = Integer.parseInt(msg);
        int no2 = Integer.parseInt(msg1);

        while (no1<=no2){
            range.setText(range.getText() + "," + no1);
            no1++;
        }

    }
}


//{
//
//    private ActivityRangOfNumberBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rang_of_number);
//        setTitle("Rang");
//
//
//        binding = ActivityRangOfNumberBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        Intent intent=getIntent();
//        String msg=intent.getStringExtra("no1");
//        String msg1=intent.getStringExtra("no2");
//
//        binding.range1.setText(msg);
//        binding.range2.setText(msg1);
//
//        int no1 = Integer.parseInt(msg);
//        int no2 = Integer.parseInt(msg1);
//
//        while (no1<=no2){
//            binding.range.setText(binding.range.getText() + "," + no1);
//            no1++;
//        }
//
//
//    }
//
//}