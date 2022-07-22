package com.nitish.programmershub.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nitish.programmershub.Adapter.CourseListAdapter;
import com.nitish.programmershub.Design_helper;
import com.nitish.programmershub.R;
import com.nitish.programmershub.Utils.AnimationHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class InterviewActivity extends AppCompatActivity {
    private Context mContext;
ListView interview_list;
String [] quesArray;
ArrayList<String> questionArrayList= new ArrayList<>();
SearchView search;

    String course;

    ArrayAdapter<String> arrayAdapter;
    String [] sol;

    ArrayList<String> arrayList;
    public com.google.android.gms.ads.AdView adView;

    RecyclerView interviewListRecycler;
    private InterstitialAd interstitialAd;
    CourseListAdapter courseListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        interviewListRecycler = findViewById(R.id.interviewListRecycler);
        getSupportActionBar().setBackgroundDrawable(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));

        //google ads
        loadInterstitialAd();
        setAdmobBannerAdView();




        Intent i = getIntent();
       course = i.getStringExtra("course").toLowerCase();
        interview_list =findViewById(R.id.interview_list);
        search = findViewById(R.id.searchView);
        try {
            sol = getAssets().list(course);
            arrayList = new ArrayList<>();

            arrayList.addAll(Arrays.asList(getAssets().list(course)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(course.contains("java"))
        {  quesArray = getResources().getStringArray(R.array.java_interview);
        }
        if(course.contains("python"))
        {  quesArray = getResources().getStringArray(R.array.python_interview);}
            if(course.contains("c_interview"))
            {  quesArray = getResources().getStringArray(R.array.c_interview);
            }
        if(course.contains("cpp_interview"))
        {  quesArray = getResources().getStringArray(R.array.cpp_interview);
        }
        if(course.contains("php_interview"))
        {  quesArray = getResources().getStringArray(R.array.php_interview);
        }
        if(course.contains("javascript_interview"))
        {  quesArray = getResources().getStringArray(R.array.javascript_interview);

        }
        if(course.contains("swift_interview"))
        {  quesArray = getResources().getStringArray(R.array.swift_interview);
        }
        if(course.contains("csharp_interview"))
        {  quesArray = getResources().getStringArray(R.array.csharp_interview);
        }


        arrayAdapter = new ArrayAdapter<String>(this,R.layout.listview_item, quesArray);
        interview_list.setAdapter(arrayAdapter);

        questionArrayList = new ArrayList<String>(Arrays.asList(quesArray));
        courseListAdapter = new CourseListAdapter(InterviewActivity.this,course,questionArrayList);
        interviewListRecycler.setAdapter(courseListAdapter);
        interviewListRecycler.startAnimation(AnimationHelper.topToButtonAnimation(InterviewActivity.this));
        interviewListRecycler.setLayoutManager(new LinearLayoutManager(this));






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
                courseListAdapter.getFilter().filter(newText);
                return false;
            }
        });



        interview_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                showInterviewDialog(i);
            }
        });

    }

    public void loadInterstitialAd() {


        AdRequest adRequest = new AdRequest.Builder().build();
        com.google.android.gms.ads.interstitial.InterstitialAd.load(
                this,
                getString(R.string.admob_interid),
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        InterviewActivity.this.interstitialAd = interstitialAd;
                        Log.i("gInterstitialAd", "onAdLoaded");

                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        InterviewActivity.this.interstitialAd = null;

                                        loadInterstitialAd();
                                        // go to back when the ad is dismissed
                                        onBackPressed();

                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        InterviewActivity.this.interstitialAd = null;
                                        Log.d("gInterstitialAd", "The ad failed to show.");
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        Log.d("gInterstitialAd", "The ad was shown.");
                                    }
                                });
                    }


                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i("gInterstitialAd", "ad loading failed : "+loadAdError.getMessage());
                        interstitialAd = null;

                        String error = String.format(
                                "domain: %s, code: %d, message: %s",
                                loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());

                        Log.d("gInterstitialAd","Ad loading failed : "+error);
                    }
                });
    }
    public void showInterviewDialog(int position) {
        LayoutInflater factory = LayoutInflater.from(InterviewActivity.this);

         View interviewDialogLayout = factory.inflate(R.layout.interview_dialog_layout, null);

         AlertDialog interviewDialog = new AlertDialog.Builder(InterviewActivity.this).create();


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
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        if (interstitialAd != null) {
            interstitialAd.show(this);

        }else{
            super.onBackPressed();
        }
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
