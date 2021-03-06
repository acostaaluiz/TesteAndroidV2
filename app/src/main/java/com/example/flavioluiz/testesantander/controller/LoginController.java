package com.example.flavioluiz.testesantander.controller;

import android.util.Log;

import com.example.flavioluiz.testesantander.Global;
import com.example.flavioluiz.testesantander.contract.presenter.LoginPresenter;
import com.example.flavioluiz.testesantander.contract.view.LoginView;
import com.example.flavioluiz.testesantander.model.User;
import com.example.flavioluiz.testesantander.repository.LoginService;
import com.example.flavioluiz.testesantander.repository.RetrofitInstance;
import com.example.flavioluiz.testesantander.repository.response.Error;
import com.example.flavioluiz.testesantander.repository.response.UserAccountResponse;
import com.example.flavioluiz.testesantander.utility.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController implements LoginPresenter {

    private LoginView loginView;

    public LoginController(LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void validateCredentials(User user) {
        loginView.showProgress();

        String userCode = user.getUser().replace(".", "").replace("-","");

        if(Validation.isNumeric(userCode)){

            if(!Validation.isValidCPF(user.getUser())) {
                loginView.showMessageInvalidCPF();
                loginView.dismissProgress();
            }
            else
                doLogin(user);

        } else {

            if(!Validation.isValidEmail(user.getUser())) {
                loginView.showMessageInvalidEmail();
                loginView.dismissProgress();
            }
            else
                doLogin(user);
        }

    }

    private void doLogin(User user){

        LoginService loginService = RetrofitInstance.createService(LoginService.class, "user", "secretpassword");
        Call<UserAccountResponse> call = loginService.getLogin(user.getUser(), user.getPassword());

        call.enqueue(new Callback<UserAccountResponse >() {
            @Override
            public void onResponse(Call<UserAccountResponse> call, Response<UserAccountResponse> response) {

                if (response.isSuccessful()) {

                    Error error = response.body().getError();

                    if(error.getCode() ==  null) {

                        UserAccountResponse userAccountResponse = response.body();
                        Global.userAccount = userAccountResponse.getUserAccount();

                        loginView.dismissProgress();
                        loginView.showLoggedInInterface();

                    } else {

                        Global.error = response.body().getError();

                        loginView.dismissProgress();
                        loginView.showErrorActivity();
                    }

                } else {

                    UserAccountResponse userAccountResponse = response.body();
                    Global.error = userAccountResponse.getError();

                    loginView.dismissProgress();
                    loginView.showErrorActivity();
                }
            }

            @Override
            public void onFailure(Call<UserAccountResponse> call, Throwable t) {

                Error error = new Error("Error", t.getMessage());
                Global.error = error;

                loginView.dismissProgress();
                loginView.showErrorActivity();
            }
        });
    }

}
