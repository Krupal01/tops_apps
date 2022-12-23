package com.example.assignmenttopsnew.dbFromAssets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.assignmenttopsnew.R;
import com.example.assignmenttopsnew.databinding.ActivityDbMainBinding;

import java.util.ArrayList;
import java.util.List;

public class DbMainActivity extends AppCompatActivity {
    private ActivityDbMainBinding binding;
    private List<DbData>dbDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDbMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        DbDatabase database=DbDatabase.getInstance(this);
        DbDao dbDao=database.dbDao();

        dbDataList=dbDao.getAllDbData();

        ArrayAdapter<DbData>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dbDataList);

        binding.listViewDbMain.setAdapter(adapter);





    }
}