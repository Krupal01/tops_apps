package com.example.onlinestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.onlinestorage.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAsyncTask.OnTaskResponseListener {
    private static final String TAG = "MainActivity";
    private String requestUrl="https://jsonplaceholder.typicode.com/posts/1";
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        MyAsyncTask asyncTask=new MyAsyncTask();
        asyncTask.setRequestUrl(requestUrl);
        asyncTask.setListener(this);
        asyncTask.execute();

    }

    @Override
    public void onResponse(String response) {
        Log.i("krupal",response);
        try {
            JSONObject jsonObject=new JSONObject(response);
            int userId=jsonObject.getInt("userId");
            String title=jsonObject.getString("title");
            String body=jsonObject.getString("body");
            binding.tvData.setText(title+"\n\n\n"+body);
            }
        catch(Exception e){
                e.printStackTrace();
        }
    }
}