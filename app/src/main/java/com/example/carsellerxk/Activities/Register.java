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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etUsername, etEmail, etPassword,etSurname,etCity,etPhone;
    TextView tvLoginLink;
    private DatabaseReference _dbReference;
    private int _nextUserId = 0;

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
        etPhone = findViewById(R.id.etPhone);
        tvLoginLink = findViewById(R.id.tvLoginLink);

        _dbReference = FirebaseDatabase.getInstance().getReference().child("userinfo");

        _dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                _nextUserId = (int) dataSnapshot.getChildrenCount() + 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _dbReference.child("uid" + _nextUserId).setValue(getUserToRegister());
                Snackbar snackbar = Snackbar.make(v,"Regjistrimi me sukses! Mund te kyceni",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        tvLoginLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                startActivity(new Intent(this, Login.class));
                break;

            case R.id.tvLoginLink:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

    private RegistrationModel getUserToRegister() {
        return new RegistrationModel(etUsername.getText().toString(), etSurname.getText().toString(),
                etCity.getText().toString(),etEmail.getText().toString(),etPassword.getText().toString());
    }
}
