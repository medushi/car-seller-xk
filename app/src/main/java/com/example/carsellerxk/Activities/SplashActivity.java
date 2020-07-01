package com.example.carsellerxk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carsellerxk.Helpers.LoginHelper;
import com.example.carsellerxk.R;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    //variablat
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_acivity);

        //Animacione
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (LoginHelper.getUserLoggedInStatus(getApplication()))
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                if (!LoginHelper.getUserLoggedInStatus(getApplication()))
                    startActivity(new Intent(SplashActivity.this, Login.class));
                finish();
            }
        }, SPLASH_SCREEN);

    }
}
