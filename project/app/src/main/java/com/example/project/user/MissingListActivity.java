package com.example.project.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.project.adapter.MissingAdapter;
import com.example.project.databinding.ActivityMissingListBinding;
import com.example.project.items.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class MissingListActivity extends AppCompatActivity {

    private ActivityMissingListBinding binding;
    private ArrayList<Person> MissingPerson = new ArrayList<>() ;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMissingListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Missing List");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);

        readValue();
        pd = new ProgressDialog(this);
        pd.show();

        binding.missingRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        MissingAdapter adapter = new MissingAdapter();
        adapter.setPerson(MissingPerson);
        binding.missingRecycler.setAdapter(adapter);
    }
    private void readValue() {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("CrimeTracker").child("Missing");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> it = snapshot.getChildren().iterator();
                while(it.hasNext()){
                    DataSnapshot childSnapShot = it.next();
                    Person newPerson=childSnapShot.getValue(Person.class);
                    MissingPerson.add(newPerson);
                }
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("project", "Failed. "+error);
            }
        });
    }
}
