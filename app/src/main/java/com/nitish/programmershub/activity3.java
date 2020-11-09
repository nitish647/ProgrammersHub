package com.nitish.programmershub;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class
activity3 extends AppCompatActivity {
    SearchView search;
    AdView fb_adView;
ListView list;
com.google.android.gms.ads.AdView google_banner;
String data;
    private InterstitialAd mInterstitialAd;
LinearLayout linear_ads_contain ;
    ArrayAdapter<String> adapter = null;
    String [] list1;
    String [] list2;
    Intent intent2;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3);
        google_banner =(com.google.android.gms.ads.AdView)findViewById(R.id.act3_google_banner);
        linear_ads_contain =(LinearLayout)findViewById(R.id.activity3_linear_ad_container);
        search =(SearchView) findViewById(R.id.search);
        list =(ListView)findViewById(R.id.list);
      final ArrayList<String> arrayList = new ArrayList<String>();
        AssetManager assetManager;
        final Intent intent = getIntent();
        data = intent.getStringExtra("course");

getSupportActionBar().setBackgroundDrawable(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));
    //admob ads

        mInterstitialAd = new InterstitialAd(this);
       mInterstitialAd.setAdUnitId(getResources().getString(R.string.admob_interid));
    mInterstitialAd.loadAd(new AdRequest.Builder().build());




        //StartAppSDK.enableReturnAds(true);

        //google banner


//     com.google.android.gms.ads.AdView mAdView = new com.google.android.gms.ads.AdView(this);
//        mAdView.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
//        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        //facebook ads
      fb_adView = new AdView(this, getResources().getString(R.string.fb_banner), AdSize.BANNER_HEIGHT_50);
//       linear_ads_contain.addView(fb_adView);
//       fb_adView.loadAd();



        try {
            //showing the bannner ads
       banner_ads();





           list1 =getAssets().list(data);
            Arrays.sort(list1);
//            if(data.contains("java_programs"))
//                list1 = getResources().getStringArray(R.array.java_program);
            if(data.contains("java_program"))
                list1 = getResources().getStringArray(R.array.java_program);
            if(data.contains("cpp_program"))
                list1 = getResources().getStringArray(R.array.cpp_programs);
            if(data.contains("python_program"))
                list1 = getResources().getStringArray(R.array.python_program);
            if(data.contains("java_course"))
                list1 = getResources().getStringArray(R.array.java_course);
            if(data.contains("cpp_course"))
                list1 = getResources().getStringArray(R.array.cpp_course);
            if(data.contains("python_course"))
             list1 = getResources().getStringArray(R.array.python_course);
            if(data.contains("swift_course"))
                list1 = getResources().getStringArray(R.array.swift_course);
            if(data.contains("javascript_course"))
                list1 = getResources().getStringArray(R.array.javascript_course);
            if(data.contains("c_course"))
                list1 = getResources().getStringArray(R.array.c_course
                );
            if(data.contains("csharp_course"))
            {

                list1 = getResources().getStringArray(R.array.csharp_course);
            }
            if(data.contains("csharp_programs"))
            {

                list1 = getResources().getStringArray(R.array.csharp_programs);
            }
            if(data.contains("php_programs"))
            {

                list1 = getResources().getStringArray(R.array.php_programs);
            }
            if(data.contains("javascript_programs"))
            {

                list1 = getResources().getStringArray(R.array.javascript_programs);
            }
            if(data.contains("c_programs"))
            {

                list1 = getResources().getStringArray(R.array.c_programs);
            }
            if(data.contains("swift_programs"))
            {

                list1 = getResources().getStringArray(R.array.swift_programs);
            }
            if(data.contains("php_course"))
            {

                list1 = getResources().getStringArray(R.array.php_course);
            }

//Collections.addAll(arrayList, getAssets().list(data));
       //     Toast.makeText(this," "+data,Toast.LENGTH_SHORT).show();
            adapter = new ArrayAdapter<String>(this,
                   R.layout.listview_item,list1);



            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    search.onActionViewExpanded();
                }
            });
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    (activity3.this).adapter.getFilter().filter(newText);
                    return true;
                }
            });

//            search.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    (activity3.this).adapter.getFilter().filter(charSequence);
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//
//                }
//            });
        } catch (IOException e) {
            e.printStackTrace();
      }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int p =list.getSelectedItemPosition();
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             //   Toast.makeText(view.getContext(), "clicked "+list.getItemAtPosition(i)+data+"/"+list1[i], Toast.LENGTH_SHORT).show();
           //     webView.loadUrl("file:///android_asset/"+data+"/"+i+".html");
                String url;
//                if(data.contains("csharp_programs")) {
//                    url = data + "/csharp_programs.pdf";
//                }
//              else
                  url = "file:///android_asset/"+data+"/"+ list1[i]+".html";
               intent2 = new Intent(view.getContext(),viewer.class);
                intent2.putExtra("url",url);
                intent2.putExtra("data",data);
                intent2.putExtra("position",String.valueOf(i));
                  startActivity(intent2);
//            show_admob_ad();

           //     webView.loadUrl("file:///android_asset/"+data+"/"+ list1[i]+".html");
            //    webView.loadUrl("file:///android_asset/"+data+"/"+list.getItemAtPosition(i)+".html");

            }
        });

        Animation anim = null;
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_top_to_bottom);
        anim.setDuration(1000);
        list.startAnimation(anim);

      //  list.setBackgroundColor(Color.parseColor("#ffffff00"));



  //   list.addView(getLayoutInflater().inflate(R.drawable.c,R));
        list.setAdapter(adapter);
    }
    @Override
    protected void onDestroy() {
        if ( fb_adView != null) {
           fb_adView.destroy();
        }
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

//        if(mInterstitialAd != null) {
//            mInterstitialAd.setAdListener(null);
//        }
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
                    startActivity(intent2);
                }
            });
        }
// If it has not loaded due to any reason simply load the next activity
        else {
            startActivity(intent2);
        }



    }
    public void banner_ads()
    {
        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
// loading google banner
                AdRequest adRequest = new AdRequest.Builder().build();

                google_banner.loadAd(adRequest);
            }

            @Override
            public void onAdLoaded(Ad ad) {


                linear_ads_contain.removeAllViews();
                linear_ads_contain.addView(fb_adView);
                fb_adView.loadAd();

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        fb_adView.loadAd(fb_adView.buildLoadAdConfig().withAdListener(adListener).build());

    }
}
