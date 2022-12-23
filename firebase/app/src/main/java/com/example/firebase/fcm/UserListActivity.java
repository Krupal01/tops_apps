package com.example.firebase.fcm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityUserListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    private ActivityUserListBinding binding;
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database= FirebaseDatabase.getInstance().getReference().child("Users");

        ArrayList<user>dataArrayList=new ArrayList<>();

        ArrayAdapter<user> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dataArrayList);

        binding.UserList.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    dataArrayList.add(dataSnapshot.getValue(user.class));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}