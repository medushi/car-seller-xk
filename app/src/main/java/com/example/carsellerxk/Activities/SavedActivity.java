package com.example.carsellerxk.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carsellerxk.Helpers.LoginHelper;
import com.example.carsellerxk.Helpers.SQLiteHelper;
import com.example.carsellerxk.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SavedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        ListView savedPostsListView = findViewById(R.id.lvSavedPosts);
        TextView txtNoFavoritesFound =findViewById(R.id.tvNoFavorites);
        SQLiteHelper  sqLiteHelper =  new SQLiteHelper(getApplicationContext());
        String [] values= sqLiteHelper.getFavorites(LoginHelper.getLoggedInEmailUser(getApplicationContext())).toArray(new String[0]);
        if(values.length==0)
            txtNoFavoritesFound.setVisibility(View.VISIBLE);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values);
        savedPostsListView.setAdapter(adapter);


        BottomNavigationView bottomNavigationView =   findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.saved);

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
