package com.example.project.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.databinding.ActivityUpdateMissingBinding;
import com.example.project.items.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateMissingActivity extends AppCompatActivity {

    private ActivityUpdateMissingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateMissingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Update Missing List");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);


        LayoutInflater inflater = getLayoutInflater();

        binding.NewPerson.setOnClickListener(v -> {
            View view = inflater.inflate(R.layout.custom_update_dialog,null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setView(view)
                    .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText name = view.findViewById(R.id.Name);
                            EditText path = view.findViewById(R.id.ImagePath);
                            FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("CrimeTracker").child("Missing");
                            Person person = new Person(name.getText().toString(),path.getText().toString());
                            ref.child(name.getText().toString()).setValue(person);
                        }
                    })
                    .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        binding.Delete.setOnClickListener(v -> {
            View view1 = inflater.inflate(R.layout.custom_update_dialog,null);
            TextView name = view1.findViewById(R.id.Name);
            TextView path = view1.findViewById(R.id.ImagePath);
            path.setVisibility(View.GONE);
            path.setEnabled(false);
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setView(view1)
                    .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("CrimeTracker").child("Missing");
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.hasChild(name.getText().toString())){
                                        ref.child(name.getText().toString()).removeValue();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"no data found",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                    })
                    .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        binding.Update.setOnClickListener(v -> {
            View view = inflater.inflate(R.layout.custom_update_dialog,null);
            TextView name = view.findViewById(R.id.Name);
            TextView path = view.findViewById(R.id.ImagePath);
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setView(view)
                    .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("CrimeTracker").child("Missing");
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.hasChild(name.getText().toString())){
                                        Person person = new Person(name.getText().toString(),path.getText().toString());
                                        ref.child(name.getText().toString()).setValue(person);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"no data found to update",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    })
                    .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        });




    }
}