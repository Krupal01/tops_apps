package com.example.ui_control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.ui_control.databinding.ActivityInputNumberBinding;

public class inputNumber extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText etMessage;
    private EditText etMessage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_number);

        etMessage = findViewById(R.id.etMessage);
        etMessage1 = findViewById(R.id.etMessage1);

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
}

//{
//
//    private ActivityInputNumberBinding binding;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_input_number);
//        setTitle("input");
//
//        binding = ActivityInputNumberBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        String no1=binding.firstNo.getText().toString();
//        String no2=binding.secondNo.getText().toString();
//
//        Context context = this;
//
//        binding.submit.setOnClickListener(v -> {
//            Intent intent = new Intent( context , RangOfNumber.class );
//            intent.putExtra("no1" , no1);
//            intent.putExtra("no2" , no2);
//            startActivity(intent);
//        });
//
//    }
//}