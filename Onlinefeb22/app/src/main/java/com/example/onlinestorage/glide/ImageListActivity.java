package com.example.onlinestorage.glide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.onlinestorage.databinding.ActivityImageListBinding;

import java.util.List;

public class ImageListActivity extends AppCompatActivity {
    private ActivityImageListBinding binding;
    private List<ImageData> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityImageListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerViewImage.setLayoutManager(new LinearLayoutManager(this));

        MyDatabase database=MyDatabase.getInstance(this);
        ImageDao imageDao=database.imageDao();
        dataList=imageDao.getImageData();

        ImageAdapter adapter=new ImageAdapter();
        adapter.setDataList(dataList);

        binding.recyclerViewImage.setAdapter(adapter);
    }
}