package com.example.feb22online;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProjectRetrofitClient {

    public static ProjectService service;

    public static ProjectService getService(){
        if(service==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2/feb22/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(ProjectService.class);
        }
        return service;
    }

}
