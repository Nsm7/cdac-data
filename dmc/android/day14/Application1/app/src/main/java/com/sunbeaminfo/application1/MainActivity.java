package com.sunbeaminfo.application1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    public class ReverseInterpolator implements Interpolator {

        @Override
        public float getInterpolation(float paramFloat) {
            return Math.abs(paramFloat -1f);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public void onAnimation1(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation1);
        animation.setDuration(1000);
        animation.setRepeatMode(Animation.RESTART);
        imageView.startAnimation(animation);
    }

    public void onAnimation2(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation2);
        animation.setDuration(1000);
        animation.setRepeatMode(Animation.RESTART);
        imageView.startAnimation(animation);
    }

    public void onAnimation3(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation3);
        animation.setDuration(1000);
        animation.setRepeatMode(Animation.RESTART);
        imageView.startAnimation(animation);
    }

    public void onAnimation4(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation4);
        animation.setDuration(1000);
        animation.setRepeatMode(Animation.RESTART);
        imageView.startAnimation(animation);
    }

    public void onAnimation5(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation5);
        animation.setDuration(1000);
        animation.setRepeatMode(Animation.RESTART);
        imageView.startAnimation(animation);
    }
}
