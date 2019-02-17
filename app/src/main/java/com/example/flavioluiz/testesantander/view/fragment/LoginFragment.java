package com.example.flavioluiz.testesantander.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.flavioluiz.testesantander.R;
import com.example.flavioluiz.testesantander.contract.view.LoginView;
import com.example.flavioluiz.testesantander.controller.LoginController;
import com.example.flavioluiz.testesantander.model.User;
import com.example.flavioluiz.testesantander.utility.ProgressDialog;
import com.example.flavioluiz.testesantander.view.activity.LoginActivity;

public class LoginFragment extends Fragment implements LoginView{

    public static final String TAG = LoginFragment.class.getSimpleName();

    private ProgressDialog progressDialog;
    private TextInputEditText txtUserValue;
    private TextInputEditText txtPasswordValue;
    private TextInputLayout userLayout;
    private TextInputLayout passwordLayout;
    private Button buttonLogin;
    private LoginController loginController;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.login_fragment, container, false);

        txtUserValue = vw.findViewById(R.id.txtUserValue);
        txtPasswordValue = vw.findViewById(R.id.txtPasswordValue);
        userLayout = vw.findViewById(R.id.userLayout);
        passwordLayout = vw.findViewById(R.id.passwordLayout);
        buttonLogin = vw.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(buttonLoginClick);

        loginController = new LoginController(this);

        return vw;
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog();
        progressDialog.show(getActivity().getSupportFragmentManager(),null);
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessageInvalidCPF() {
        txtUserValue.requestFocus();
        userLayout.setError(getResources().getString(R.string.cpf_invalid));
    }

    @Override
    public void showMessageInvalidEmail() {
        txtUserValue.requestFocus();
        userLayout.setError(getResources().getString(R.string.email_invalid));
    }

    @Override
    public void doLogin(User user) {

    }

    @Override
    public void showLoggedInInterface() {

        userLayout.setError(null);
        passwordLayout.setError(null);

        LoggedInFragment fragment = new LoggedInFragment();
        ((LoginActivity) getActivity()).changeFragment(fragment, LoggedInFragment.TAG);
    }

    private View.OnClickListener buttonLoginClick = new View.OnClickListener() {
        public void onClick(View v) {

            String userName = txtUserValue.getText().toString();
            String password = txtPasswordValue.getText().toString();

            User user = new User(userName, password);

            loginController.validateCredentials(user);
        }
    };
}
