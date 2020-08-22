package com.nitish.programmershub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.icu.lang.UCharacter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.onesignal.OneSignal;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public AdView mAdView;
    ActionBarDrawerToggle t;
    private com.google.android.gms.ads.InterstitialAd mInterstitialAd;
//
    ArrayList mImageUrl = new ArrayList<>();
    ArrayList mNames= new ArrayList<>();
WebView webView;
    private InterstitialAd fb_interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mAdView = (AdView)findViewById(R.id.main_banner);



        String appid = (String) getResources ().getText(R.string.startapps);


//admob ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //admob banner

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //facebook ads
        AudienceNetworkAds.initialize(this);

//        fb_interstitialAd = new InterstitialAd(this,   getResources().getString(R.string.fb_interid));
//        fb_interstitialAd.loadAd();

        // Startapp ads

//        StartAppSDK.init(this,appid, false);
//        StartAppAd.disableSplash();
//        StartAppSDK.enableReturnAds(false);

        //admob inter ads
//        mInterstitialAd = new com.google.android.gms.ads.InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(getResources().getString(R.string.admob_interid));
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        ActionBar bar = getSupportActionBar();
        final DrawerLayout dl = (DrawerLayout) findViewById(R.id.main_drawer);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nv = (NavigationView) findViewById(R.id.navigaion);


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                WebView webView = new WebView(getApplicationContext());
        if(id ==R.id.policy) {
            webView.loadUrl("file:///android_asset/privacy_policy_ph.html");

            final AlertDialog alertDialog = new AlertDialog.Builder(
                    MainActivity.this).create();
            alertDialog.setView(webView);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ok", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(android.content.DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        }
        if(id==R.id.more_apps)
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=GLASSWEB"));
            startActivity(intent);
        }
                if(id==R.id.share)
                {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            "Hey check out my app at: https://play.google.com/store/apps/details?id=com.nitish.programmershub");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
                dl.closeDrawers();

                        return false;
                }});







        bar.setBackgroundDrawable(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));

        mImageUrl.add((R.drawable.c1));
        mImageUrl.add(R.drawable.cpp4);
        mImageUrl.add(R.drawable.java7);
        mImageUrl.add(R.drawable.js3);

        mImageUrl.add(R.drawable.python3);
        mImageUrl.add(R.drawable.php2);
        mImageUrl.add(R.drawable.swift5);
        mImageUrl.add(R.drawable.csharp1);
        mNames.add("C");
        mNames.add("CPP");
        mNames.add("JAVA");
        mNames.add("JAVASCRIPT");

        mNames.add("PYTHON");
        mNames.add("PHP");
        mNames.add("SWIFT");
        mNames.add("cSharp");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        RecyclerViewAdapter RecyclerViewAdapter =
                new RecyclerViewAdapter(mNames, mImageUrl, this);

      GridLayoutManager GridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(GridLayoutManager);

        recyclerView.setAdapter(RecyclerViewAdapter);
        final Context context = recyclerView.getContext();


        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
//        mInterstitialAd.setAdListener(new AdListener()
//        {
//            public void onAdLoaded()
//            {
//                // Call displayInterstitial() function when the Ad loads
//                displayInterstitial();
//            }
//        });
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();

//        if(mInterstitialAd != null) {
//            mInterstitialAd.setAdListener(null);
//        }
    }
    public void displayInterstitial()
    {
        // If Interstitial Ads are loaded then show else show nothing.
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
