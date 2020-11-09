package com.nitish.programmershub;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;


public class Activity2 extends AppCompatActivity implements View.OnClickListener {
    public AdView mAdView;
    private InterstitialAd mInterstitialAd;
  public com.facebook.ads.InterstitialAd fb_InterstitialAd;
    com.facebook.ads.AdView fb_adView;
Button course , interview , programs , hindi, eng,compiler;
    GradientDrawable gd;
    Intent intent;
    LinearLayout linear_ads_contain ;






String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
     // button background
      gd = Design_helper.set_Colors("#FF0D0D","#EE4D22", (float) 20, GradientDrawable.Orientation.LEFT_RIGHT);
        AudienceNetworkAds.initialize(this);
        linear_ads_contain =(LinearLayout)findViewById(R.id.activity2_linear_ad_container);
        int fillColor = Color.parseColor("#FB8C00");
String[] colors = {"#E91E63","#512DA8","#558B2F","#F57C00","#1565C0"};
          set_btn();
btn_background();

//google ads
        mAdView = (AdView)findViewById(R.id.banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //admob inter ads

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.admob_interid));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

//facebook inter
//        try {
//            fb_InterstitialAd = new com.facebook.ads.InterstitialAd(this,getResources().getString(R.string.fb_interid));
//            fb_InterstitialAd.loadAd();
//        facebook_inter_listner();
//
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(getApplicationContext(),".",Toast.LENGTH_LONG).show();
//        }


        //facebook banner
        fb_adView = new com.facebook.ads.AdView(this, getResources().getString(R.string.fb_banner), AdSize.BANNER_HEIGHT_50);
        linear_ads_contain.addView(fb_adView);
        fb_adView.loadAd();



        Intent intent2 = getIntent();
        data = intent2.getStringExtra("course");


            ActionBar bar = getSupportActionBar();
            bar.setTitle(data);
        bar.setBackgroundDrawable(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));
       eng.setOnClickListener(this);
        course.setOnClickListener(this);
        interview.setOnClickListener(this);
                programs.setOnClickListener(this);
        hindi.setOnClickListener(this);
        compiler.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        String id = getResources().getResourceEntryName(view.getId());
//        Toast.makeText(this, ""+this.data.toLowerCase()+"_"+id,Toast.LENGTH_SHORT).show();
       intent= new Intent();


        if (view.getId()==R.id.course ||view.getId()==R.id.programs ) {

            intent = new Intent(this, activity3.class);

        }
        if(view.getId()==R.id.compiler)
        {
            intent = new Intent(this,viewer.class );

        }


        if(view.getId()==R.id.interview )
        {
            intent = new Intent(this,Interview.class );

        }
        if(view.getId()==R.id.hindi)
        {
            intent = new Intent(this,Youtube_play.class );

        }
        if(view.getId()==R.id.eng)
        {
            intent = new Intent(this, Youtube_play.class );

        }
        intent.putExtra("course",""+data.toLowerCase()+"_"+id);


        show_admob_ad();
      //  startActivity(intent);




// getIntent();

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
    public void show_admob_ad()
    {
        if(mInterstitialAd.isLoaded()) {
            // Step 1: Display the interstitial
            mInterstitialAd.show();
            // Step 2: Attach an AdListener
            mInterstitialAd.setAdListener(new AdListener() {

                @Override
                public void onAdClosed() {
                    // Step 2.1: Load another ad
                    AdRequest adRequest = new AdRequest.Builder()

                            .build();
                    //    Toast.makeText(getApplicationContext(),"ad closed",Toast.LENGTH_LONG).show();
                    mInterstitialAd.loadAd(adRequest);

                    // Step 2.2: Start the new activity
                    startActivity(intent);
                }
            });
        }
// If it has not loaded due to any reason simply load the next activity
        else {
            startActivity(intent);
        }



    }
    public  void set_btn()
    {

        course = (Button)findViewById(R.id.course);
        interview = (Button)findViewById(R.id.interview);
        programs = (Button)findViewById(R.id.programs);
        hindi = (Button)findViewById(R.id.hindi);
        eng = (Button)findViewById(R.id.eng);
        compiler = (Button)findViewById(R.id.compiler);
    }
    public void btn_background()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        { course.setBackground(gd);
        interview.setBackground(gd);

            programs.setBackground(gd);

        hindi.setBackground(gd);
       eng.setBackground(gd);
        compiler.setBackground(gd);}
    }
}


