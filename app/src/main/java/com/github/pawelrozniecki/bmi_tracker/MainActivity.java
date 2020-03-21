package com.github.pawelrozniecki.bmi_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 3000;
    TextView logo ;
    TextView logoLower;
    View screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo_upper);
        logoLower = findViewById(R.id.logo_lower);
        screen = findViewById(R.id.splashLayout);
        startAnimation();
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(homeIntent);
                overridePendingTransition(0, 0);

                finish();
            }
        },SPLASH_TIMEOUT);



    }

    private void  startAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splashscreen);
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.lowerlogo);
        Animation animation2 =AnimationUtils.loadAnimation(this, R.anim.splash);


        logo.startAnimation(animation);
        logoLower.startAnimation(animation1);
        screen.startAnimation(animation2);
    }

}
