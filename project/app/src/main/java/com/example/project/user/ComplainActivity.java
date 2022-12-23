package com.example.project.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project.databinding.ActivityComplainBinding;
import com.example.project.items.Complain;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComplainActivity extends AppCompatActivity {

    private ActivityComplainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComplainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Register Complain");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFAAAAAA"));
        actionBar.setBackgroundDrawable(colorDrawable);

        binding.btnComplainSubmit.setOnClickListener(v -> {
            String name = binding.YourName.getText().toString();
            String mobile = binding.Mobile.getText().toString();
            String aadhar = binding.AadharCard.getText().toString();
            String compline = binding.Description.getText().toString();
            String aim = binding.Aim.getText().toString();
            if(!(name.isEmpty()&&aadhar.isEmpty()&&mobile.isEmpty()&&compline.isEmpty()&&aim.isEmpty())) {
                //firebase code
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("CrimeTracker").child("Complain");

                Complain newComplain = new Complain(name, mobile, aadhar, compline, aim);

                AlertDialog builder = new AlertDialog.Builder(this)
                        .setTitle("Confirm")
                        .setMessage("if you enter correct detail as your Aadhar card than and only than your complian will considered, otherwise it will deleted Automatically")
                        .setPositiveButton("ok", (dialog, which) -> ref.child(aim).setValue(newComplain))
                        .setNeutralButton("Cancle", (dialog, which) -> {
                            Toast.makeText(getApplicationContext(), "Complain is not submit", Toast.LENGTH_SHORT).show();
                        })
                        .create();
                builder.show();

                binding.YourName.setText("");
                binding.Mobile.setText("");
                binding.AadharCard.setText("");
                binding.Description.setText("");
                binding.Aim.setText("");

            }
            else {
                Toast.makeText(getApplicationContext(),"All Field are Requied",Toast.LENGTH_LONG).show();
            }


        });
    }
}