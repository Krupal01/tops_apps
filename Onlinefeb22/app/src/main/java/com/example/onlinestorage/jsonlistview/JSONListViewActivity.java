package com.example.onlinestorage.jsonlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.onlinestorage.R;
import com.example.onlinestorage.databinding.ActivityJsonlistViewBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONListViewActivity extends AppCompatActivity implements jsonASYNC.ASYNCListner {

    private String url = "https://jsonplaceholder.typicode.com/posts/";
    private ActivityJsonlistViewBinding binding;
    final ArrayList<jsonObject> tubeLines = new ArrayList<>();

    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJsonlistViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pd=ProgressDialog.show(this,"Wait","Fetching Data!!!!");

        jsonASYNC async = new jsonASYNC();
        async.setListner(this);
        async.setUrl(url);
        async.execute();
    }

    @Override
    public void onresponceListner(String responce) {
        pd.dismiss();
        try {
            JSONArray array = new JSONArray(responce);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int userId=object.getInt("userId");
                int id=object.getInt("id");
                String title=object.getString("title");
                String body=object.getString("body");
                jsonObject object1 = new jsonObject(userId,id,title,body);
                tubeLines.add(object1);
            }
            ArrayAdapter<jsonObject> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tubeLines);
            binding.JSONList.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}