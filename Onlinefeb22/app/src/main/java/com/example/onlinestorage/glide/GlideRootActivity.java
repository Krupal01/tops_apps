package com.example.onlinestorage.glide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.onlinestorage.R;
import com.example.onlinestorage.databinding.ActivityGlideRootBinding;

public class GlideRootActivity extends AppCompatActivity {
    private ActivityGlideRootBinding binding;
    private ImageData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGlideRootBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(v -> {
            MyDatabase database = MyDatabase.getInstance(this);
            ImageDao imageDao = database.imageDao();

            data = new ImageData();
            data.url = binding.edtImageUrl.getText().toString();

            imageDao.saveImage(data);


            Toast.makeText(this, "Data Added SuccessFully", Toast.LENGTH_SHORT).show();


        });
    }
}