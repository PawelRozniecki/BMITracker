package com.github.pawelrozniecki.bmi_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
    }


    public void closeScreen(View v) {

        Intent intent = new Intent(AboutPage.this, HomeActivity.class);
        startActivity(intent);
    }
}
