package com.github.pawelrozniecki.bmi_tracker;

import android.widget.EditText;

public class UsefulFunctions {


    protected float round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (float) Math.round(value * scale) / scale;
    }

    protected boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }

}
