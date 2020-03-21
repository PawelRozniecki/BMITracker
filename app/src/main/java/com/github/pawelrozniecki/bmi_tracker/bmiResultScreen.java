package com.github.pawelrozniecki.bmi_tracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class bmiResultScreen extends AppCompatActivity {

    TextView result;
    TextView information;
    String weight, height, resultBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result_screen);
        result = (TextView) findViewById(R.id.sendBMI);
        information = (TextView) findViewById(R.id.information);

        Intent intent = getIntent();
        resultBMI = intent.getStringExtra("result");
        weight = intent.getStringExtra("weight");
        height = intent.getStringExtra("height");

        result.setText(resultBMI);
        validate(resultBMI);
    }


    public void validate(String resultBMI) {

        float BMI = Float.parseFloat(resultBMI);

        if (BMI > 30) {
            information.setText(getResources().getString(R.string.message_obese,height,weight, "OBESE"));
            information.setTextColor(getResources().getColor(R.color.bmi_red_obese));
            result.setTextColor(getResources().getColor(R.color.bmi_red_obese));

        }
        if (BMI > 24 && BMI <=30) {
            information.setText(getResources().getString(R.string.message_obese,height,weight, "OVERWEIGHT"));
            information.setTextColor(getResources().getColor(R.color.bmi_orange_overweight));
            result.setTextColor(getResources().getColor(R.color.bmi_orange_overweight));

        }
        if(BMI>18 && BMI<=24){
            information.setText(getResources().getString(R.string.message_normal,height,weight));
            information.setTextColor(getResources().getColor(R.color.bmi_green_normal));
            result.setTextColor(getResources().getColor(R.color.bmi_green_normal));

        }
        if(BMI>16 && BMI<=18){
            information.setText(getResources().getString(R.string.message_underweight,height,weight,"UNDERWEIGHT"));
            information.setTextColor(getResources().getColor(R.color.bmi_blue_dark_underweight));
            result.setTextColor(getResources().getColor(R.color.bmi_blue_dark_underweight));

        }
        if(BMI<=16){
            information.setText(getResources().getString(R.string.message_underweight,height,weight,"VERY UNDERWEIGHT"));
            information.setTextColor(getResources().getColor(R.color.bmi_blue_light_very_underweight));
            result.setTextColor(getResources().getColor(R.color.bmi_blue_light_very_underweight));


        }
    }

    public void closeScreen(View v) {

        Intent intent = new Intent(bmiResultScreen.this, HomeActivity.class);
        startActivity(intent);
    }
}
