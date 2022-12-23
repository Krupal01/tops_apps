package com.example.onlinestorage.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.onlinestorage.databinding.ActivityRetrofitBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    private ActivityRetrofitBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRetrofitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Call<List<RetroUser>> call=RetrofitClient.getService().groupList();

        call.enqueue(new Callback<List<RetroUser>>() {
            @Override
            public void onResponse(Call<List<RetroUser>> call, Response<List<RetroUser>> response) {
                List<RetroUser> userList = response.body();
                Log.i("hiii", userList.toString());
                ArrayAdapter<RetroUser> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,userList);
                binding.list.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<RetroUser>> call, Throwable t) {
                Log.i("hiii", t.toString());
            }
        });
    }
}