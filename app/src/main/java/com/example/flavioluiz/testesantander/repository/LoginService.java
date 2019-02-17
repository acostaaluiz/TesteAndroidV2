package com.example.flavioluiz.testesantander.repository;

import com.example.flavioluiz.testesantander.repository.response.UserAccountResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface LoginService {

    @FormUrlEncoded
    @POST("login")
    Call<UserAccountResponse> getLogin(@Field("user") String user, @Field("password") String password);
}
