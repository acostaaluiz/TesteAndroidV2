package com.example.flavioluiz.testesantander.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.flavioluiz.testesantander.R;
import com.example.flavioluiz.testesantander.view.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        LoginFragment loginFragment = new LoginFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, loginFragment, LoginFragment.TAG)
        .commitNowAllowingStateLoss();
    }

    public void changeFragment(Fragment fragment, String tag){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.addToBackStack(tag);
        ft.replace(R.id.fragment_container, fragment, tag);
        ft.commit();
    }
}
