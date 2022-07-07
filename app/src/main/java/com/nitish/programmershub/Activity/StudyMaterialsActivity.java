package com.nitish.programmershub.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nitish.programmershub.Design_helper;
import com.nitish.programmershub.R;


public class StudyMaterialsActivity extends AppCompatActivity  {
    public AdView mAdView;

  public com.facebook.ads.InterstitialAd fb_InterstitialAd;
    com.facebook.ads.AdView fb_adView;


    Intent intent;



Button interviewButton,courseButton,programsButton,compilerButton,englishButton,hindiButton;

String data;
    public AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_materials_layout);
     // button background
        AudienceNetworkAds.initialize(this);
        set_btn();

        setAdmobBannerAdView();
        //admob inter ads


        //facebook banner
        fb_adView = new com.facebook.ads.AdView(this, getResources().getString(R.string.fb_banner_nitish), AdSize.BANNER_HEIGHT_50);
        fb_adView.loadAd();



        Intent intent2 = getIntent();
        data = intent2.getStringExtra("course");


            ActionBar bar = getSupportActionBar();
            bar.setTitle(data);
        bar.setBackgroundDrawable(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));


        Intent intent ;
        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              Intent  intent = new Intent(StudyMaterialsActivity.this, CourseListActivity.class);
                intent.putExtra("course",""+data.toLowerCase()+"_course");
              startActivity(intent);

            }
        });

        programsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent  intent = new Intent(StudyMaterialsActivity.this, CourseListActivity.class);
                intent.putExtra("course",""+data.toLowerCase()+"_programs");
                startActivity(intent);

            }
        });


        interviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent  intent = new Intent(StudyMaterialsActivity.this, InterviewActivity.class);
                intent.putExtra("course",""+data.toLowerCase()+"_interview");
                startActivity(intent);

            }
        });
        compilerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent  intent = new Intent(StudyMaterialsActivity.this, ContentViewerActivity.class);
                intent.putExtra("course",""+data.toLowerCase()+"_compiler");
                startActivity(intent);

            }
        });
        hindiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent  intent = new Intent(StudyMaterialsActivity.this, YoutubeChannelsActivity.class);
                intent.putExtra("course",""+data.toLowerCase()+"_hindi");
                startActivity(intent);

            }
        });

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent  intent = new Intent(StudyMaterialsActivity.this, YoutubeChannelsActivity.class);
                intent.putExtra("course",""+data.toLowerCase()+"_eng");
                startActivity(intent);

            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();

//        if(mInterstitialAd != null) {
//            mInterstitialAd.setAdListener(null);
//        }
    }

public void facebook_inter_listner()
{
fb_InterstitialAd.loadAd((com.facebook.ads.InterstitialAd.InterstitialLoadAdConfig) fb_InterstitialAd.buildLoadAdConfig().withAdListener(new InterstitialAdListener() {
    @Override
    public void onInterstitialDisplayed(Ad ad) {

    }

    @Override
    public void onInterstitialDismissed(Ad ad) {

    }

    @Override
    public void onError(Ad ad, AdError adError) {

    }

    @Override
    public void onAdLoaded(Ad ad) {
        if(!fb_InterstitialAd.isAdInvalidated())
        fb_InterstitialAd.show();

    }

    @Override
    public void onAdClicked(Ad ad) {

    }

    @Override
    public void onLoggingImpression(Ad ad) {

    }
}));
}



    @Override
    protected void onDestroy() {
        if ( fb_adView != null) {
            fb_adView.destroy();
        }
//        if(fb_InterstitialAd!=null)
//            fb_InterstitialAd.destroy();
        super.onDestroy();
    }

    public  void set_btn()
    {

        courseButton = findViewById(R.id.courseButton);

        interviewButton = findViewById(R.id.interviewButton);
        programsButton = findViewById(R.id.programsButton);
        hindiButton = findViewById(R.id.hindiButton);
        englishButton = findViewById(R.id.englishButton);
        compilerButton =findViewById(R.id.compilerButton);
    }
    public void setAdmobBannerAdView()
    {
        adView = findViewById(R.id.adView);
        // Create an ad request.
        AdRequest adRequest = new AdRequest.Builder().build();
        // Start loading the ad in the background.
        adView.loadAd(adRequest);
    }
}


