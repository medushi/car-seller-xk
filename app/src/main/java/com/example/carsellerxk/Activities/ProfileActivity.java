package com.example.carsellerxk.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import com.example.carsellerxk.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

//        Button bLogout;
//        EditText etUsername;
//        EditText etEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        etUsername = findViewById(R.id.etUsername);
//        etEmail = findViewById(R.id.etEmail);
//        bLogout = findViewById(R.id.bLogout);
//
//        bLogout.setOnClickListener(this);
//    }
//    @Override
//    public void onClick(View v) {
//            switch(v.getId()){
//            case R.id.bLogout:
//
//            startActivity(new Intent(this, Login.class));
//            break;
//            }
        BottomNavigationView bottomNavigationView =   findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.newpost:
                        startActivity(new Intent(getApplicationContext()
                                , NewPostActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext()
                                , SavedActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.more:
                        startActivity(new Intent(getApplicationContext()
                                , MoreActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
