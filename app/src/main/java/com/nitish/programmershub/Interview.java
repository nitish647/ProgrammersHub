package com.nitish.programmershub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Interview extends AppCompatActivity {
    private Context mContext;
ListView interview_list;
String [] ques;
SearchView search;
private AdView fb_adView;
    String course;
com.google.android.gms.ads.AdView google_banner;
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
//                    Log.d("TAG", "The interstitial wasn'actionBarDrawerToggle loaded yet.");
//                }


        //facebook ads
        fb_adView= new AdView(this, getResources().getString(R.string.fb_banner_nitish), AdSize.BANNER_HEIGHT_50);


banner_ads();

        Intent i = getIntent();
       course = i.getStringExtra("course").toLowerCase();
        interview_list =findViewById(R.id.interview_list);
        search = findViewById(R.id.filter);
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

                showInterviewDialog(i);
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
    public void showInterviewDialog(int position) {
        LayoutInflater factory = LayoutInflater.from(Interview.this);

         View interviewDialogLayout = factory.inflate(R.layout.interview_dialog_layout, null);

         AlertDialog interviewDialog = new AlertDialog.Builder(Interview.this).create();


        interviewDialog.setView(interviewDialogLayout);
        interviewDialog.show();


        Button closeButton = interviewDialogLayout.findViewById(R.id.closeButton);
        WebView interviewWebView = interviewDialogLayout.findViewById(R.id.interviewWebView);
        interviewDialog.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        if(course.contains("javascript_interview"))
            interviewWebView.loadUrl("file:///android_asset/"+course+"/"+String.valueOf(position+1)+".html");
        else if (course.contains("python_interview"))
            interviewWebView.loadUrl("file:///android_asset/"+course+"/"+String.valueOf(position+1)+".html");
        else
            interviewWebView.loadUrl("file:///android_asset/"+course+"/"+sol[position]);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interviewDialog.dismiss();
            }
        });
    }
}
