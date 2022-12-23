package com.example.feb22online;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ProjectService {

    @GET("Login.php?flag=SELECT")
    Call<List<User>> getUserList();


    @FormUrlEncoded
    @POST("Login.php?flag=INSERT")
    Call<String> insertUser(@Field("first_name") String firstName,
                            @Field("last_name") String lastName,
                            @Field("email") String email,
                            @Field("mobile") String mobile);


    @FormUrlEncoded
    @POST("Login.php?flag=UPDATE")
    Call<String> updateUser(@Field("id") String id,
                            @Field("first_name") String firstName,
                            @Field("last_name") String lastName,
                            @Field("email") String email,
                            @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("Login.php?flag=DELETE")
    Call<String> deleteUser(@Field("id") String id);
}
