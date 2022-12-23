package com.example.sum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText number1;
    private EditText number2;
    private TextView  ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        ans  = findViewById(R.id.ans);
    }

    public void sum(View view){
        int i1 = Integer.valueOf(number1.getText().toString());
        int i2 = Integer.valueOf(number2.getText().toString());
        int s = i1 + i2;
        ans.setText(String.valueOf(s));
    }
}