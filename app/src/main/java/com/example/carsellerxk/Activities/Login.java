package com.example.carsellerxk.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carsellerxk.Models.RegistrationModel;
import com.example.carsellerxk.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText etEmail, etPassword;
    TextView tvRegisterLink;
    private DatabaseReference _dbReference;
    private List<RegistrationModel> _registeredUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        tvRegisterLink = findViewById(R.id.tvRegisterLink);
        _dbReference = FirebaseDatabase.getInstance().getReference().child("userinfo");

        _dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    _registeredUsers.add(new RegistrationModel(dsp.child("firstName").getValue().toString(),
                            dsp.child("lastName").getValue().toString(), dsp.child("city").getValue().toString(),
                            dsp.child("email").getValue().toString(), dsp.child("password").getValue().toString()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoginSuccessful(etEmail.getText().toString(), etPassword.getText().toString())){
                    printInLoginMessage("succeeded");
                    startActivity(new Intent(Login.this,HomeActivity.class));
                    finish();
                }
                else
                    printInLoginMessage("failed");
            }
        });

        tvRegisterLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    private boolean isLoginSuccessful(String email, String password) {
        boolean stateOfLogin = false;
        for (RegistrationModel userRegistered : _registeredUsers) {
            if (email.equals(userRegistered.getEmail())) {
                if (password.equals(userRegistered.getPassword()))
                    stateOfLogin = true;
            }
        }
        return stateOfLogin;
    }

    private void printInLoginMessage(String typeCase) {
        if (typeCase.equals("failed"))
            Toast.makeText(Login.this, "Kredencialet jane gabim!", Toast.LENGTH_LONG).show();
        else if (typeCase.equals("succeeded"))
            Toast.makeText(Login.this, "Kycja u be me sukses!", Toast.LENGTH_LONG).show();
    }
}
