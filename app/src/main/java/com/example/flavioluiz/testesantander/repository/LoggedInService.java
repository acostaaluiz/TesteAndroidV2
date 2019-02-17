package com.example.flavioluiz.testesantander.repository;

import com.example.flavioluiz.testesantander.repository.response.StatementResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LoggedInService {

    @GET("statements/1")
    Call<StatementResponse> getStatements();
}
