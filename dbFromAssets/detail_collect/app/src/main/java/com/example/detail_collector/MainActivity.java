package com.example.detail_collector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText name1;
    private EditText name2;
    private EditText name3;
    private EditText phone;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name1 = findViewById(R.id.Name1);
        name2 = findViewById(R.id.Name2);
        name3 = findViewById(R.id.Name3);
        phone = findViewById(R.id.Phone);
        email = findViewById(R.id.Email);
    }

    public void submit(View view){
        String Firstname = name1.getText().toString();
        String secondname = name2.getText().toString();
        String surname = name3.getText().toString();
        String mobilenumber = phone.getText().toString();
        String EmailAddress = email.getText().toString();

        Intent intent = new Intent(this , Activity2.class);
        intent.putExtra("Fristname", Firstname);
        intent.putExtra("secondname", secondname);
        intent.putExtra("surname" , surname);
        intent.putExtra("mobile",mobilenumber);
        intent.putExtra("EmailAddress", EmailAddress);
        startActivity(intent);

    }
}