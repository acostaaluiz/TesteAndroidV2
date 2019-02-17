package com.example.flavioluiz.testesantander.controller;

import android.util.Log;

import com.example.flavioluiz.testesantander.Global;
import com.example.flavioluiz.testesantander.contract.presenter.LoggedInPresenter;
import com.example.flavioluiz.testesantander.contract.view.LoggedInView;
import com.example.flavioluiz.testesantander.repository.LoggedInService;
import com.example.flavioluiz.testesantander.repository.RetrofitInstance;
import com.example.flavioluiz.testesantander.repository.response.StatementResponse;
import com.example.flavioluiz.testesantander.repository.response.UserAccount;
import com.example.flavioluiz.testesantander.repository.response.UserAccountResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoggedInController implements LoggedInPresenter {

    private LoggedInView loggedInView;

    public LoggedInController(LoggedInView loggedInView){
        this.loggedInView = loggedInView;
    }

    @Override
    public void loadHeader() {

        loggedInView.showProgress();

        UserAccount userAccount = Global.userAccount;

        loggedInView.setHeader(userAccount);
    }

    @Override
    public void loadStatements() {

        LoggedInService loggedInService = RetrofitInstance.createService(LoggedInService.class, "user", "secretpassword");
        Call<StatementResponse> call = loggedInService.getStatements();

        call.enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
                if (response.isSuccessful()) {

                    StatementResponse statementResponse = response.body();
                    loggedInView.setStatementList(statementResponse.getStatementList());
                    loggedInView.dismissProgress();

                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
    }
}
