package com.example.ui_control;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class inputNumber extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText etMessage;
    private EditText etMessage1;
    private EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_number);

        etMessage = findViewById(R.id.etMessage);
        etMessage1 = findViewById(R.id.etMessage1);
        url = findViewById(R.id.url);

        Log.i(TAG, "onCreate");
    }

    public void submitForm(View view) {
        Log.i(TAG, "Submit Form");
        String msg = etMessage.getText().toString();
        String msg1 = etMessage1.getText().toString();
        Log.i(TAG, msg);
        Log.i(TAG, msg1);


        // launch Second Activity

        Context context = this;


        Intent intent = new Intent(context, RangOfNumber.class);
        // pass data to SecondActivity
        intent.putExtra("msg", msg);
        intent.putExtra("msg1", msg1);
        startActivity(intent);
    }

    public void serch(View view) {
        String url1 = url.getText().toString();
        Intent intent = new Intent(this , webView.class);
        intent.putExtra("url",url1);
        startActivity(intent);
    }
}

