package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;


import com.example.firebase.databinding.ActivityMainBinding;
import com.example.firebase.fcm.FirebaseMasseging;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean IsFound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSubmit.setOnClickListener(v -> {
//            String msg=binding.data.getText().toString();

            FirebaseDatabase database=FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("persons").push();

            person thePerson=new person("k","l","5255225645","ksfksnfk@gmail.com","male");
            ref.setValue(thePerson);
        });

//        binding.btnDelete.setOnClickListener(v -> {
//            String key="-McIEj8SUHJKUjfuBn1R";
//            FirebaseDatabase database=FirebaseDatabase.getInstance();
//            DatabaseReference ref = database.getReference("persons").child(key);
//            ref.removeValue();
//        });
//
//        binding.btnUpdate.setOnClickListener(v -> {
//            String key="-McIEj8SUHJKUjfuBn1R";
//            FirebaseDatabase database=FirebaseDatabase.getInstance();
//            DatabaseReference ref = database.getReference("persons").child(key);
//            String msgUpdate = "karan";
//            ref.setValue(msgUpdate);
//        });

        readData();
        binding.OTP.setText(getIntent().getStringExtra("otp"));

    }

    private void readData() {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("persons");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<HashMap<String, person>> personList=new ArrayList<>();
                Iterator<DataSnapshot> it = snapshot.getChildren().iterator();
                while(it.hasNext()){
                    DataSnapshot childSnapShot = it.next();
                    String key=childSnapShot.getKey();
                    person person = childSnapShot.getValue(person.class);
                    HashMap<String, person> hm=new HashMap<>();
                    hm.put(key, person);
                    personList.add(hm);
                }
                binding.readData.setText(personList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("MainError", "Failed. "+error);
            }
        });
    }
}