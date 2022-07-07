package com.nitish.programmershub.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.nitish.programmershub.Design_helper;
import com.nitish.programmershub.R;


public class SplashActivity extends AppCompatActivity {
LottieAnimationView lottieAnimationView;
TextView textView;
    Intent intent;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       // StartAppSDK.init(this,getResources().getString(R.string.startapps), false);
        getSupportActionBar().setBackgroundDrawable(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));

        lottieAnimationView = (LottieAnimationView)findViewById(R.id.lottie_splash);
        textView = (TextView)findViewById(R.id.splash_text);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
  intent= new Intent(getApplicationContext(),MainActivity.class);
  startActivity(intent);
  finish();
            }
        },5000);
    }
}
