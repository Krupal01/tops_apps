package com.example.detail_collector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent=getIntent();
        String Fristname=intent.getStringExtra("Fristname");
        String secondname=intent.getStringExtra("secondname");
        String surname=intent.getStringExtra("surname");
        String mobile=intent.getStringExtra("mobile");
        String EmailAddress=intent.getStringExtra("EmailAddress");

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);

        textView1.setText(Fristname);
        textView2.setText(secondname);
        textView3.setText(surname);
        textView4.setText(mobile);
        textView5.setText(EmailAddress);
    }
}