package com.github.pawelrozniecki.bmi_tracker;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Build;
import android.widget.TextView;
import androidx.annotation.RequiresApi;


public class CounterAnimation {

    public void startCountAnimation(float min, float max, int duration, final TextView textView){

        ValueAnimator animator = ValueAnimator.ofFloat(min,max);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            /**
             *  0-16 very underweight DARK blue
                17-18 underweight LIGHT blue
                19-24 normal
                25-30 overweight
                >30 obese **/

                float result =  (float) animation.getAnimatedValue();
                if(result<16){
                    textView.setTextColor(Color.parseColor("#3498db"));
                }
                if(result>16 && result<=18){
                    textView.setTextColor(Color.parseColor("#2980b9"));
                }
                if(result>18 && result <=24){
                    textView.setTextColor(Color.parseColor("#27ae60"));
                }
                if( result>24 && result <=30){
                    textView.setTextColor(Color.parseColor("#f39c12"));
                }
                if(result>30){
                    textView.setTextColor(Color.parseColor("#e74c3c"));
                }
                float x = (float) (Math.round(result*100.0)/100.0);
                textView.setText(String.valueOf(x));
            }
        });
        animator.start();
    }
}
