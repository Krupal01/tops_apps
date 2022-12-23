package com.example.project.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.adapter.ComplainAdapter;
import com.example.project.databinding.ActivityCheckComplainBinding;
import com.example.project.items.Complain;
import com.example.project.items.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CheckComplainActivity extends AppCompatActivity implements ComplainAdapter.OnClick {

    private ActivityCheckComplainBinding binding;
    private ArrayList<Complain> complain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckComplainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Check Complain");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);


        ArrayList<Complain> complain = new ArrayList<>();
        complain.add(new Complain("d","d","d","d","d"));
        binding.ComplainList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ComplainAdapter adapter = new ComplainAdapter(this);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("CrimeTracker").child("Complain");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> it = snapshot.getChildren().iterator();
                while(it.hasNext()){
                    DataSnapshot childSnapShot = it.next();
                    Complain newComplain=childSnapShot.getValue(Complain.class);
                    complain.add(newComplain);
                    Log.i("project",complain.toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                Log.i("project",error.toString());
            }
        });

        adapter.setAdapterArray(complain);
        binding.ComplainList.setAdapter(adapter);

    }

    @Override
    public void OnClick(Complain complain) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(complain.getAim());
        builder.setMessage(complain.getDescription()+" Aadhar no."+complain.getAadhar());
        builder.setPositiveButton("Delete", (dialog, which) -> {
            FirebaseDatabase database=FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("CrimeTracker").child("Complain").child(complain.getAim());
            ref.removeValue();
        });

        builder.setNeutralButton("cancle", (dialog, which) -> {
            Toast toast = Toast.makeText(getApplicationContext(),"delet cancle",Toast.LENGTH_SHORT);
            toast.show();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}