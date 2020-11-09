package com.nitish.programmershub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.style.LineHeightSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.Toast;


import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.sql.StatementEvent;

public class Interview extends AppCompatActivity {
    private Context mContext;
ListView interview_list;
String [] ques;
SearchView search;
private AdView fb_adView;
com.google.android.gms.ads.AdView google_banner;
private InterstitialAd mInterstitialAd;
    ArrayAdapter<String> arrayAdapter;
    String [] sol;
    LinearLayout linearLayout;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        getSupportActionBar().setBackgroundDrawable(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));
google_banner =(com.google.android.gms.ads.AdView)findViewById(R.id.interview_google_banner);
        linearLayout =(LinearLayout)findViewById(R.id.interview_linear_adcontain) ;
        //google ads
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(getResources().getString(R.string.admob_interid));
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());
//
//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                }
//                else {
//                    Log.d("TAG", "The interstitial wasn't loaded yet.");
//                }


        //facebook ads
        fb_adView= new AdView(this, getResources().getString(R.string.fb_banner), AdSize.BANNER_HEIGHT_50);


banner_ads();

        Intent i = getIntent();
        final String course = i.getStringExtra("course").toLowerCase();
        interview_list =(ListView)findViewById(R.id.interview_list);
        search = (SearchView)findViewById(R.id.filter);
        try {
            sol = getAssets().list(course);
            arrayList = new ArrayList<>();

            arrayList.addAll(Arrays.asList(getAssets().list(course)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(course.contains("java"))
        {  ques = getResources().getStringArray(R.array.java_interview);
        }
        if(course.contains("python"))
        {  ques = getResources().getStringArray(R.array.python_interview);}
            if(course.contains("c_interview"))
            {  ques = getResources().getStringArray(R.array.c_interview);
            }
        if(course.contains("cpp_interview"))
        {  ques = getResources().getStringArray(R.array.cpp_interview);
        }
        if(course.contains("php_interview"))
        {  ques = getResources().getStringArray(R.array.php_interview);
        }
        if(course.contains("javascript_interview"))
        {  ques = getResources().getStringArray(R.array.javascript_interview);

        }
        if(course.contains("swift_interview"))
        {  ques = getResources().getStringArray(R.array.swift_interview);
        }
        if(course.contains("csharp_interview"))
        {  ques = getResources().getStringArray(R.array.csharp_interview);
        }
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.listview_item,ques);
        interview_list.setAdapter(arrayAdapter);
//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                (Interview.this).arrayAdapter.getFilter().filter(charSequence);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });


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
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        Animation anim = null;
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_top_to_bottom);
        anim.setDuration(1000);
        interview_list.startAnimation(anim);
        interview_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
// i=i+1;


// rest of your coding functionality goes here of onClick method.

                WebView webView = new WebView(view.getContext());
                AlertDialog.Builder builder1 = new AlertDialog.Builder((Context)Interview.this,R.style.Theme_MaterialComponents_BottomSheetDialog);

                webView.getSettings().setJavaScriptEnabled(true);
                webView.setBackgroundColor(Color.parseColor("#E6EE9C"));
             //  webView.loadUrl("file:///android_asset/"+course+"/"+i+".html");
             //   Toast.makeText(view.getContext(), sol[i],Toast.LENGTH_SHORT).show();
                if(course.contains("javascript_interview"))
                  webView.loadUrl("file:///android_asset/"+course+"/"+String.valueOf(i+1)+".html");
              else if (course.contains("python_interview"))
                    webView.loadUrl("file:///android_asset/"+course+"/"+String.valueOf(i+1)+".html");
                else
                     webView.loadUrl("file:///android_asset/"+course+"/"+sol[i]);
builder1.setTitle("Answer");
                builder1.setNeutralButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

            final     AlertDialog alert11 = builder1.create();

               alert11.setView(webView);
                alert11.show();

           //     alert11.getWindow().setBackgroundDrawableResource(android.R.color.holo_orange_dark);
                alert11.getWindow().setBackgroundDrawableResource(R.drawable.button_circle);

Button button =  alert11.getButton(AlertDialog.BUTTON_NEUTRAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); //create a new one
                layoutParams.weight = 1;
                layoutParams.gravity = Gravity.CENTER; //this is layout_gravity
                button.setLayoutParams(layoutParams);
                button.setTextSize(20);
               // button.setBackgroundColor(Color.parseColor("#D81B60"));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    @Override
    protected void onDestroy() {
        if (fb_adView != null) {
           fb_adView.destroy();
        }
        super.onDestroy();
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


                linearLayout.removeAllViews();
               linearLayout.addView(fb_adView);
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
