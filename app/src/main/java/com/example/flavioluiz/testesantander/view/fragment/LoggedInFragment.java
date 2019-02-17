package com.example.flavioluiz.testesantander.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flavioluiz.testesantander.R;
import com.example.flavioluiz.testesantander.contract.view.LoggedInView;
import com.example.flavioluiz.testesantander.controller.LoggedInController;
import com.example.flavioluiz.testesantander.repository.response.Statement;
import com.example.flavioluiz.testesantander.repository.response.UserAccount;
import com.example.flavioluiz.testesantander.utility.ProgressDialog;
import com.example.flavioluiz.testesantander.view.StatementAdapter;
import com.example.flavioluiz.testesantander.view.activity.ErrorActivity;

import java.util.List;

public class LoggedInFragment extends Fragment implements LoggedInView{

    public static final String TAG = LoggedInFragment.class.getSimpleName();

    private ProgressDialog progressDialog;
    private View header;
    private TextView txtViewUser;
    private TextView txtViewUserAccount;
    private TextView txtViewUserBalance;
    private ImageView buttonLogout;
    private RecyclerView statementRecycler;
    private LoggedInController loggedInController;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.logged_in_fragment, container, false);

        header = vw.findViewById(R.id.header);
        txtViewUser = header.findViewById(R.id.txtViewUser);
        txtViewUserAccount = header.findViewById(R.id.txtViewUserAccount);
        txtViewUserBalance = header.findViewById(R.id.txtViewUserBalance);
        statementRecycler = vw.findViewById(R.id.myRecyclerView);
        buttonLogout = header.findViewById(R.id.imgLogout);
        buttonLogout.setOnClickListener(buttonLogoutClick);

        loggedInController = new LoggedInController(this);
        loggedInController.loadHeader();

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
    public void setHeader(UserAccount userAccount) {

        txtViewUser.setText(userAccount.getName());
        txtViewUserAccount.setText(userAccount.getFullBankData());
        txtViewUserBalance.setText(userAccount.getBalance());

        loggedInController.loadStatements();
    }

    @Override
    public void setStatementList(List<Statement> statementList) {

        RecyclerView.LayoutManager myLayouyManager = new LinearLayoutManager(getActivity());
        RecyclerView.Adapter myRecyclerViewAdpter = new StatementAdapter(statementList);

        statementRecycler.setHasFixedSize(true);
        statementRecycler.setLayoutManager(myLayouyManager);
        statementRecycler.setAdapter(myRecyclerViewAdpter);
    }

    @Override
    public void showErrorActivity() {
        Intent intent = new Intent(getActivity(), ErrorActivity.class);
        getActivity().startActivity(intent);
    }

    private View.OnClickListener buttonLogoutClick = v -> {
        getFragmentManager().popBackStack();
    };
}
