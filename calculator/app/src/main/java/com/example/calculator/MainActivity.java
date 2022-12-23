package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText num1;
    private EditText num2;
    private TextView ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        ans = findViewById(R.id.ans);

    }


    public void sum(View view){
        int i1 = Integer.valueOf(num1.getText().toString());
        int i2 = Integer.valueOf(num2.getText().toString());
        int s=i1+i2;
        ans.setText(String.valueOf(s));
    }

    public void sub(View view){
        int i1 = Integer.valueOf(num1.getText().toString());
        int i2 = Integer.valueOf(num2.getText().toString());
        int s=i1-i2;
        ans.setText(String.valueOf(s));
    }

    public void mul(View view){
        int i1 = Integer.valueOf(num1.getText().toString());
        int i2 = Integer.valueOf(num2.getText().toString());
        int s=i1*i2;
        ans.setText(String.valueOf(s));
    }

    public void div(View view){
        int i1 = Integer.valueOf(num1.getText().toString());
        int i2 = Integer.valueOf(num2.getText().toString());
        int s=i1/i2;
        ans.setText(String.valueOf(s));
    }

}