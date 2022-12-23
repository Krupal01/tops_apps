package com.example.firebasetorecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.firebasetorecyclerview.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.Recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ArrayList<String> data = new ArrayList<>();
        data.add("kk");
        data.add("ll");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Recycler");
        reference.setValue("krupal");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data1 = snapshot.getValue(String.class);
                data.add(data1);
                binding.data.setText(data1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MainAdapter adapter = new MainAdapter();
        adapter.setData(data);
        binding.Recycler.setAdapter(adapter);


    }
}