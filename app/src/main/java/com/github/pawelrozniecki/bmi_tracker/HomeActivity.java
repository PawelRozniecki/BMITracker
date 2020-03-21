package com.github.pawelrozniecki.bmi_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class HomeActivity extends AppCompatActivity {

    protected float BMI = 0;
    public Button calculateBtn, maleButton, femaleButton;
    public com.google.android.material.textfield.TextInputEditText heightField, weightField;
    public TextView bmiResult, units, genderTextView, clickOnme;
    protected View screen;
    protected float height, weight;

    public boolean isMetric = true;
    public boolean isImperial = false;
    public boolean isMale = true;

    BMI b = new BMI();
    CounterAnimation c = new CounterAnimation();
    UsefulFunctions uf = new UsefulFunctions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        screen = findViewById(R.id.fragment_container);
        units = findViewById(R.id.units);
        femaleButton = findViewById(R.id.female_button);
        maleButton = findViewById(R.id.male_button);
        bmiResult = findViewById(R.id.bmiResult);
        genderTextView = findViewById(R.id.gender_text);
        weightField = findViewById(R.id.weight);
        heightField = findViewById(R.id.height);
        clickOnme = findViewById(R.id.clickMe);
        BottomNavigationView bottomNavigation = findViewById(R.id.nav_bottom);


        //ON CLICK LISTENERS

        bottomNavigation.setOnNavigationItemSelectedListener(navListener);
        startAnimation();
        bmiResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change activity on bmi result click
                Intent intent = new Intent(HomeActivity.this,bmiResultScreen.class);
                intent.putExtra("result", Float.toString(getBMI()));
                intent.putExtra("height", Float.toString(getHeight()));
                intent.putExtra("weight", Float.toString(getWeight()));
                startActivity(intent);

            }
        });

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMale = true;
                genderTextView.setText(getResources().getString(R.string.gender_male));

            }
        });

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMale = false;
                genderTextView.setText(getResources().getString(R.string.gender_female));
            }
        });
    }

    //Fade in animation onCreate()
    private void startAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alphaanim);
        screen.startAnimation(animation);
    }

    public void calculateBMI(View v) {

        try {

            weight = Float.parseFloat(weightField.getText().toString());
            height = Float.parseFloat(heightField.getText().toString());

            setHeight(height);
            setWeight(weight);
            bmiResult.setTextColor(getResources().getColor(R.color.splashFontcolor));
            bmiResult.setTextSize(60);
            bmiResult.setClickable(true);

            c.startCountAnimation(0.0f, b.calculate(weight, height, isMetric), 1800, bmiResult);
            BMI = b.calculate(weight,height,isMetric);
            setBMI(BMI);

            bmiResult.setText(Float.toString(BMI));




        } catch (Exception e) {

            bmiResult.setTextColor(getResources().getColor(R.color.errorColor));
            bmiResult.setTextSize(20);
            bmiResult.setClickable(false);

            if (uf.isEmpty(weightField)) {
                bmiResult.setText("Please enter your weight");
            }

            if (uf.isEmpty(heightField)) {
                bmiResult.setText("Please enter your height");
            }
            if (uf.isEmpty(weightField) && uf.isEmpty(heightField)) {
                bmiResult.setText("Please enter your weight and height");
            }
            Log.d("error", e.toString());

        }
    }

    //Handles button click on Bottom Navigation Bar
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()) {
                        case R.id.imperial:
                            isImperial = true;
                            isMetric = false;
                            units.setText(getString(R.string.imperial_units));
                            break;

                        case R.id.metric:
                            isMetric = true;
                            isImperial = false;
                            units.setText(getString(R.string.metric_units));
                            break;
                    }
                    return true;
                }
            };

    public void openAboutPage(View v){
        Intent intent = new Intent(HomeActivity.this, AboutPage.class);
        startActivity(intent);
    }
    //getters and setters
    public float getBMI(){return BMI;}
    public void  setBMI(float BMI){this.BMI = BMI;}

    public float getHeight(){return height;}
    public void  setHeight(float height){this.height = height;}

    public float getWeight(){return weight;}
    public void  setWeight(float weight){this.weight = weight;}
}
