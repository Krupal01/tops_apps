package com.example.onlinestorage.railway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.onlinestorage.R;
import com.example.onlinestorage.databinding.ActivityRailListBinding;
import com.example.onlinestorage.post.post;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RailListActivity extends AppCompatActivity implements RailAsync.OnRailResponce {
    String url="https://jsonplaceholder.typicode.com/posts";
    private ActivityRailListBinding binding;
    private RailAdapter adapter;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRailListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pd=ProgressDialog.show(this,"Wait","Fetching Data!!!!");
        binding.Recycler.setLayoutManager(new LinearLayoutManager(this));

        RailAsync async = new RailAsync();
        async.setUrl(url);
        async.setListner(this);
        async.execute();
    }

    @Override
    public void Onresponce(String responce) {
        Log.i("kru",responce);
        pd.dismiss();
        List<RailObject> data=new ArrayList<>();

        try {

            JSONArray jArray = new JSONArray(responce);

            // Extract data from json and store into ArrayList as class objects
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);

                int userId=json_data.getInt("userId");
                int id=json_data.getInt("id");
                String title=json_data.getString("title");
                String body=json_data.getString("body");

                data.add(new RailObject(id,userId,title,body));
            }

            // Setup and Handover data to recyclerview
            binding.Recycler.setLayoutManager(new LinearLayoutManager(this));
            RailAdapter adapter = new RailAdapter();
            adapter.setRail((ArrayList<RailObject>) data);
            binding.Recycler.setAdapter(adapter);


        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }


    }
}