package com.example.onlinestorage.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProjectService {

    @GET("marvel")
    Call<List<RetroUser>> groupList();
}
