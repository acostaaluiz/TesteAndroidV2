package com.example.flavioluiz.testesantander;

import android.app.Application;

import com.example.flavioluiz.testesantander.repository.response.Error;
import com.example.flavioluiz.testesantander.repository.response.UserAccount;

public class Global extends Application {

    public static  UserAccount userAccount;
    public static Error error;
}
