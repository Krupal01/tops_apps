package com.example.onlinestorage.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static ProjectService service;

    public static ProjectService getService(){
        if(service==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://simplifiedcoding.net/demos/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(ProjectService.class);
        }
        return service;
    }
}
