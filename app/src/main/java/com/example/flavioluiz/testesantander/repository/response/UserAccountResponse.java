package com.example.flavioluiz.testesantander.repository.response;

import com.google.gson.annotations.SerializedName;

public class UserAccountResponse {

    @SerializedName("userAccount")
    private UserAccount userAccount;

    @SerializedName("error")
    private Error error;

    public UserAccountResponse(UserAccount userAccount, Error error){
        this.userAccount = userAccount;
        this.error = error;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
