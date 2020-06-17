package com.example.carsellerxk.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.carsellerxk.Activities.MoreActivity;
import com.example.carsellerxk.Activities.ProfileActivity;
import com.example.carsellerxk.Activities.SavedActivity;
import com.example.carsellerxk.MainActivity;
import com.example.carsellerxk.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        BottomNavigationView bottomNavigationView =   findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        return true;
                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext()
                                , SavedActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext()
                                , ProfileActivity.class));
                        overridePendingTransition(0,0);
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
