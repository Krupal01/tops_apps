package com.example.onlinestorage.cricketgson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.onlinestorage.R;

import com.example.onlinestorage.databinding.ActivityGsonListBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonListActivity extends AppCompatActivity {

    private ActivityGsonListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGsonListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String data=readFileFromAssets();
        if(data!=null){
            // json parsing
            try {

                Gson gson=new Gson();

                Type type = new TypeToken<ArrayList<User>>() {}.getType();
                List<User> cookings=gson.fromJson(data, type);

                ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cookings);
                binding.gsonList.setAdapter(adapter);

                Log.i("Tag1", cookings.toString());
            }catch (Exception ex){
                Log.i("Tag",ex.toString());
            }
        }


    }

    private String readFileFromAssets() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("gson.json")));

            String mLine, data="";
            while ((mLine = reader.readLine()) != null) {

                data+=mLine;
            }

            return data;
        } catch (Exception e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }
}