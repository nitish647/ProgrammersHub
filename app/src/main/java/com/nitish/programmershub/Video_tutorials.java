package com.nitish.programmershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class Video_tutorials extends  YouTubeBaseActivity  {
int millis;
    private InterstitialAd fb_interstitialAd;
    String   playlist_id;
    LinearLayout linearLayout;
    LottieAnimationView lottieAnimationView;
    private YouTubePlayer YPlayer;
    public TextView textView;
    private com.google.android.gms.ads.InterstitialAd mInterstitialAd;
YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_tutorials);

        String key =  (String) getResources ().getText(R.string.key);
        lottieAnimationView = (LottieAnimationView)findViewById(R.id.lotie_tutorial);
youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_tut);
linearLayout =(LinearLayout)findViewById(R.id.videotut);
textView = (TextView)findViewById(R.id.txt_title);


/// youtube activity 3 playing the youtube videos




//admob inter ads
        mInterstitialAd = new com.google.android.gms.ads.InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.admob_interid));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

//        mInterstitialAd.setAdListener(new AdListener()
//        {
//            public void onAdLoaded()
//            {
//                // Call displayInterstitial() function when the Ad loads
//                displayInterstitial();
//            }
//        });


        //facebook ads
//        fb_interstitialAd = new InterstitialAd(this,   getResources().getString(R.string.fb_interid));
//        fb_interstitialAd.loadAd();


if(!isNetworkAvailable())
    lottieAnimationView.setVisibility(View.VISIBLE);





        Intent intent = getIntent();


         playlist_id =intent.getStringExtra("videoid");
         textView.setText(intent.getStringExtra("title"));



      //  Toast.makeText(this,"vide id "+playlist_id,Toast.LENGTH_LONG).show();
//setupviewpager(viewPager);
        youTubePlayerView.initialize(key, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {

                YPlayer = youTubePlayer;



                    youTubePlayer.cueVideo(playlist_id);


                  //  YPlayer.cueVideo(getIntent().getStringExtra("videoID"));

             millis =   youTubePlayer.getCurrentTimeMillis();

                youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                    @Override
                    public void onFullscreen(boolean b) {
                        if(b)
                           youTubePlayer.play();


                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }

        });

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        else {


            return false;
        }
    }
    public void setFb_interstitialAd()
    {
        fb_interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                fb_interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        });

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown

    }

    @Override
    protected void onDestroy() {
//        if (fb_interstitialAd != null) {
//            fb_interstitialAd.destroy();
//        }

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
//                    finish();
                    onBackPressed();
                }
            });
        }else{
            super.onBackPressed();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();


    }
    public void displayInterstitial()
    {
        // If Interstitial Ads are loaded then show else show nothing.
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}


