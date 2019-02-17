package com.example.flavioluiz.testesantander.repository.response;

import com.google.gson.annotations.SerializedName;

public class UserAccount {

    @SerializedName("userId")
    private String userId;

    @SerializedName("name")
    private String name;

    @SerializedName("bankAccount")
    private String bankAccount;

    @SerializedName("agency")
    private String agency;

    @SerializedName("balance")
    private String balance;

    public UserAccount(String userId, String name, String bankAccount, String agency, String balance){

        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getBalance() {
        return "R$ " + balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getFullBankData(){
        return bankAccount + " / " + agency;
    }
}
