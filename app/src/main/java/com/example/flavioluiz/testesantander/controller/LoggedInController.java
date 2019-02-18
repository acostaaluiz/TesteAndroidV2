package com.example.flavioluiz.testesantander.controller;

import com.example.flavioluiz.testesantander.Global;
import com.example.flavioluiz.testesantander.contract.presenter.LoggedInPresenter;
import com.example.flavioluiz.testesantander.contract.view.LoggedInView;
import com.example.flavioluiz.testesantander.repository.LoggedInService;
import com.example.flavioluiz.testesantander.repository.RetrofitInstance;
import com.example.flavioluiz.testesantander.repository.response.Error;
import com.example.flavioluiz.testesantander.repository.response.StatementResponse;
import com.example.flavioluiz.testesantander.repository.response.UserAccount;

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

                    Error error = response.body().getError();

                    if(error.getCode() == null) {

                        StatementResponse statementResponse = response.body();
                        loggedInView.setStatementList(statementResponse.getStatementList());
                        loggedInView.dismissProgress();

                    } else {

                        Global.error = response.body().getError();

                        loggedInView.dismissProgress();
                        loggedInView.showErrorActivity();
                    }

                } else {

                    StatementResponse statementResponse = response.body();
                    Global.error = statementResponse.getError();

                    loggedInView.dismissProgress();
                    loggedInView.showErrorActivity();
                }
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {

                Error error = new Error("Error", t.getMessage());
                Global.error = error;

                loggedInView.showErrorActivity();
            }
        });
    }
}
