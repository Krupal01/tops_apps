package com.example.project.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.adapter.NoticeAdapter;
import com.example.project.databinding.ActivityHomeBinding;
import com.example.project.items.Notice;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Dashboard");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);

        binding.complian.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ComplainActivity.class));
        });

        binding.status.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), StatusCheckActivity.class));
        });

        binding.wanted.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), WantedListActivity.class));
        });

        binding.missing.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MissingListActivity.class));
        });

        ArrayList<Notice> note = new ArrayList<>();
        binding.newsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        NoticeAdapter adapter = new NoticeAdapter();

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("CrimeTracker").child("NoticeBoard");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Notice Notice1 = dataSnapshot.getValue(Notice.class);
                    note.add(Notice1);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter.setNote(note);
        binding.newsRecycler.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Logout:
                SharedPreferences sharedPreferences = getSharedPreferences("Islogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(),"Log Out Sucess",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}