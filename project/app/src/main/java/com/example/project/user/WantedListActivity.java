package com.example.project.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.example.project.adapter.WantedAdapter;
import com.example.project.databinding.ActivityWantedListBinding;
import com.example.project.items.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class WantedListActivity extends AppCompatActivity {

    private ActivityWantedListBinding binding;
    private ArrayList<Person> WantedPerson = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWantedListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Wanted List");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);

        readValue();

        binding.wantedRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        WantedAdapter adapter = new WantedAdapter();
        adapter.setPerson(WantedPerson);
        binding.wantedRecycler.setAdapter(adapter);
    }

    private void readValue() {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("CrimeTracker").child("Wanted");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                WantedPerson =new ArrayList<>();
                Iterator<DataSnapshot> it = snapshot.getChildren().iterator();
                while(it.hasNext()){
                    DataSnapshot childSnapShot = it.next();
                    Person newPerson=childSnapShot.getValue(Person.class);
                    WantedPerson.add(newPerson);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("project", "Failed. "+error);
            }
        });
    }

}