package com.example.onlinestorage.post;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.onlinestorage.R;
import com.example.onlinestorage.databinding.ActivityPostBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class postActivity extends AppCompatActivity implements async.OnpostResponce {
    String url="https://jsonplaceholder.typicode.com/posts";
    private ActivityPostBinding binding;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pd=ProgressDialog.show(this,"Wait","Fetching Data!!!!");

        async asyncTask=new async();
        asyncTask.setRequestUrl(url);
        asyncTask.setListner(this);
        asyncTask.execute();
    }

    @Override
    public void onResponce(String responce) {
        Log.i("hi",responce);
        pd.dismiss();
        ArrayList<post> postList=new ArrayList<>();

        try {
            JSONArray jsonArray=new JSONArray(responce);
            if(jsonArray!=null){
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject postJson=jsonArray.getJSONObject(i);
                    int userId=postJson.getInt("userId");
                    int id=postJson.getInt("id");
                    String title=postJson.getString("title");
                    String body=postJson.getString("body");
                    post thePost=new post(id, userId, title, body);
                    postList.add(thePost);
                }

            }

            ArrayAdapter<post> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postList);
            binding.listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}