package com.example.flavioluiz.testesantander.contract.view;

import com.example.flavioluiz.testesantander.model.User;

public interface LoginView {

    void showProgress();
    void dismissProgress();
    void showMessageInvalidCPF();
    void showMessageInvalidEmail();
    void doLogin(User user);
    void showLoggedInInterface();
}
