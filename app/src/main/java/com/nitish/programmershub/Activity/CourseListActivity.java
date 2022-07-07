package com.nitish.programmershub.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nitish.programmershub.Design_helper;
import com.nitish.programmershub.R;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class
CourseListActivity extends AppCompatActivity {
    SearchView search;
    AdView fb_adView;
ListView courseListView;

String data;
    public com.google.android.gms.ads.AdView adView;

    String courseAssetUrl;
    int selectedCoursePosition;
    ArrayAdapter<String> adapter = null;
    String [] list1;
    String [] list2;
    Intent intent2;


    private com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd;
    
    
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3);

        search =(SearchView) findViewById(R.id.search);
        courseListView =(ListView)findViewById(R.id.courseListView);
      final ArrayList<String> arrayList = new ArrayList<String>();
        AssetManager assetManager;
        final Intent intent = getIntent();
        data = intent.getStringExtra("course");

getSupportActionBar().setBackgroundDrawable(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));
    //admob ads






        //StartAppSDK.enableReturnAds(true);

        //google banner

        setAdmobBannerAdView();

    //    loadInterstitialAd();






        try {






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
                    (CourseListActivity.this).adapter.getFilter().filter(newText);
                    return true;
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
      }

        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             //   Toast.makeText(view.getContext(), "clicked "+list.getItemAtPosition(i)+data+"/"+list1[i], Toast.LENGTH_SHORT).show();
           //     webView.loadUrl("file:///android_asset/"+data+"/"+i+".html");

//                if(data.contains("csharp_programs")) {
//                    url = data + "/csharp_programs.pdf";
//                }
//              else
                selectedCoursePosition =i;
                  courseAssetUrl = "file:///android_asset/"+data+"/"+ list1[i]+".html";

                intentForCourseViewer();

                // pausing the interstitialAd for now

//                if (interstitialAd != null) {
//                    interstitialAd.show(CourseListActivity.this);
//                }
//                else
//                  intentForCourseViewer();

            }
        });

        Animation anim = null;
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_top_to_bottom);
        anim.setDuration(1000);
        courseListView.startAnimation(anim);

      //  list.setBackgroundColor(Color.parseColor("#ffffff00"));



  //   list.addView(getLayoutInflater().inflate(R.drawable.c,R));
        courseListView.setAdapter(adapter);
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

    public void setAdmobBannerAdView()
    {
        adView = findViewById(R.id.adView);
        // Create an ad request.
        AdRequest adRequest = new AdRequest.Builder().build();
        // Start loading the ad in the background.
        adView.loadAd(adRequest);
    }
    public void intentForCourseViewer()
    {
      Intent intent = new Intent(CourseListActivity.this, ContentViewerActivity.class);
        intent.putExtra("url", courseAssetUrl);
        intent.putExtra("data",data);
        intent.putExtra("position",String.valueOf(selectedCoursePosition));
        startActivity(intent);
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
                        CourseListActivity.this.interstitialAd = interstitialAd;
                        Log.i("gInterstitialAd", "onAdLoaded");

                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        CourseListActivity.this.interstitialAd = null;

                                   //     loadInterstitialAd();


                                        // go to the next activity
                                        intentForCourseViewer();

                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        CourseListActivity.this.interstitialAd = null;
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

}
