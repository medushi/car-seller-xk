package com.example.carsellerxk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carsellerxk.R;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etUsername, etEmail, etPassword,etSurname,etCity;
    TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        bRegister = findViewById(R.id.bRegister);
        etSurname = findViewById(R.id.etSurname);
        etCity = findViewById(R.id.etCity);
        tvLoginLink = findViewById(R.id.tvLoginLink);

        bRegister.setOnClickListener(this);
        tvLoginLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bRegister:
                startActivity(new Intent(this, HomeActivity.class));
                break;

            case R.id.tvLoginLink:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}
