package com.example.flavioluiz.testesantander.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.flavioluiz.testesantander.Global;
import com.example.flavioluiz.testesantander.R;
import com.example.flavioluiz.testesantander.repository.response.Error;

public class ErrorActivity extends AppCompatActivity {

    private TextView txtTitleValue;
    private TextView txtMessageValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_activity);

        txtTitleValue = findViewById(R.id.txtTitleValue);
        txtMessageValue = findViewById(R.id.txtErrorMessageValue);

        loadError();
    }

    private void loadError(){

        Error error = Global.error;

        txtTitleValue.setText(error.getCode());
        txtMessageValue.setText(error.getMessage());
    }
}
