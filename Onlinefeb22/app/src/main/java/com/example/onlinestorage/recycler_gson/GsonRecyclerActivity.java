package com.example.onlinestorage.recycler_gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;


import com.example.onlinestorage.cricketgson.GsonAdapter;
import com.example.onlinestorage.databinding.ActivityGsonRecyclerBinding;
import com.example.onlinestorage.railway.RailAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class GsonRecyclerActivity extends AppCompatActivity {

    private ActivityGsonRecyclerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGsonRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String data=readFileFromAssets();
        if(data!=null){
            // json parsing
            try {

                Gson gson=new Gson();

                Type type = new TypeToken<ArrayList<UserRecycler>>() {}.getType();
                ArrayList<UserRecycler> cookings=gson.fromJson(data, type);

                binding.GsonRecycler.setLayoutManager(new LinearLayoutManager(this));
                GsonAdapter adapter = new GsonAdapter();
                adapter.setUser(cookings);
                binding.GsonRecycler.setAdapter(adapter);



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
