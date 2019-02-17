package com.example.flavioluiz.testesantander.contract.view;

import com.example.flavioluiz.testesantander.repository.response.Statement;
import com.example.flavioluiz.testesantander.repository.response.UserAccount;

import java.util.List;

public interface LoggedInView {

    void showProgress();
    void dismissProgress();
    void setHeader(UserAccount userAccount);
    void setStatementList(List<Statement> statementList);
    void showErrorActivity();
}
