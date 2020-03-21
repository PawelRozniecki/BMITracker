package com.github.pawelrozniecki.bmi_tracker;

import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BMI {

    private UsefulFunctions uf = new UsefulFunctions();

    public float calculate(float weight, float height, boolean flag) {

        float BMI = 0.0f;

        try {
            if (flag) {
                //weight / (height/100)^2
                BMI = uf.round((float) (weight / Math.pow(height / 100, 2)), 1);
            } else {
                //imperial

                BMI = uf.round((float) ((703 * weight) / Math.pow(height, 2)), 1);
            }

        } catch (Exception e) {

            Log.e("error", e.toString());

        }
        return BMI;
    }


}
