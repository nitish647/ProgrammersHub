package com.nitish.programmershub.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.facebook.ads.AdView;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.appbar.AppBarLayout;
import com.nitish.programmershub.Adapter.CourseListAdapter;
import com.nitish.programmershub.Design_helper;
import com.nitish.programmershub.R;
import com.nitish.programmershub.Utils.ToastHelper;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import io.paperdb.Paper;

public class
CourseListActivity extends AppCompatActivity {
    SearchView search;
    AdView fb_adView;
ListView courseListView;
    CourseListAdapter courseListAdapter;
String courseName;
    public com.google.android.gms.ads.AdView adView;

    AppBarLayout appBarLayout;
    String courseAssetUrl;
    int selectedCoursePosition;
    ArrayAdapter<String> adapter = null;
    String [] courseList;
    ArrayList<String> courseListArrayList = new ArrayList<>();
    String [] list2;
    Intent intent2;

    ArcProgress circleProgressBar;


    private com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd;
    String[] solutionListArray;
    RecyclerView  courseListRecycler;
    TextView courseNameText;
    String courseTitle;

    Toolbar toolBar;
    ImageView logoImage;
    boolean backPressed=false;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list_layout);

        courseNameText = findViewById(R.id.courseNameText);
        appBarLayout = findViewById(R.id.appBarLayout);
        toolBar = findViewById(R.id.toolBar);
        circleProgressBar = findViewById(R.id.circleProgressBar);
        courseListRecycler = findViewById(R.id.courseListRecycler);
        search =(SearchView) findViewById(R.id.search);
        courseListView =(ListView)findViewById(R.id.courseListView);
        logoImage = findViewById(R.id.logoImage);

        loadInterstitialAd();
        setToolbar();

      final ArrayList<String> arrayList = new ArrayList<String>();

        final Intent intent = getIntent();

        courseName = intent.getStringExtra("course");

        appBarLayout.setBackground(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));

//Objects.requireNonNull(getSupportActionBar()).getCustomView().setVisibility(View.GONE);
    //admob ads






        //StartAppSDK.enableReturnAds(true);

        //google banner

        setAdmobBannerAdView();

    //    loadInterstitialAd();






        try {





            solutionListArray = getAssets().list(courseName);
           courseList =getAssets().list(courseName);
            Arrays.sort(courseList);
//            if(data.contains("java_programs"))
//                list1 = getResources().getStringArray(R.array.java_program);
            if(courseName.contains("java_program")) {

                courseList = getResources().getStringArray(R.array.java_program);
               courseTitle = "Java Program";
                logoImage.setImageResource(R.drawable.java7);
            }
            if(courseName.contains("cpp_program")) {
                courseList = getResources().getStringArray(R.array.cpp_programs);
                courseTitle = "C++ Program";
                logoImage.setImageResource(R.drawable.cpp4);

            }
            if(courseName.contains("python_program")) {
                courseList = getResources().getStringArray(R.array.python_program);
                courseTitle = "Python Program";


            }
            if(courseName.contains("java_course")) {
                courseList = getResources().getStringArray(R.array.java_course);
                courseTitle = "Java Course";
                logoImage.setImageResource(R.drawable.java7);
            }
            if(courseName.contains("cpp_course")) {
                courseList = getResources().getStringArray(R.array.cpp_course);

                courseTitle = "C++ Course";
                logoImage.setImageResource(R.drawable.cpp4);

            }
            if(courseName.contains("python_course")) {
                courseList = getResources().getStringArray(R.array.python_course);
                courseTitle = "Python Course";


            }
            if(courseName.contains("swift_course")) {
                courseList = getResources().getStringArray(R.array.swift_course);

                courseTitle = "Swift Course";
                logoImage.setImageResource(R.drawable.swift4);

            }
            if(courseName.contains("javascript_course")) {
                courseList = getResources().getStringArray(R.array.javascript_course);
                courseTitle = "Javascript Course";

                logoImage.setImageResource(R.drawable.javascript);


            }
            if(courseName.contains("c_course")) {
                courseList = getResources().getStringArray(R.array.c_course);

                courseTitle = "C Course";
                logoImage.setImageResource(R.drawable.c1);

            }
            if(courseName.contains("csharp_course"))
            {

                courseList = getResources().getStringArray(R.array.csharp_course);
                courseTitle = "C# Course";

                logoImage.setImageResource(R.drawable.csharp1);
            }
            if(courseName.contains("csharp_programs"))
            {

                courseList = getResources().getStringArray(R.array.csharp_programs);
                courseTitle = "C# Programs";
                logoImage.setImageResource(R.drawable.csharp1);
            }
            if(courseName.contains("php_programs"))
            {

                courseList = getResources().getStringArray(R.array.php_programs);
                courseTitle = "Php Programs";
                logoImage.setImageResource(R.drawable.php2);
            }
            if(courseName.contains("javascript_programs"))
            {

                courseList = getResources().getStringArray(R.array.javascript_programs);

                courseTitle = "Javascript Programs";
                logoImage.setImageResource(R.drawable.javascript);
            }
            if(courseName.contains("c_programs"))
            {

                courseList = getResources().getStringArray(R.array.c_programs);

                courseTitle = "C Programs";
                logoImage.setImageResource(R.drawable.c1);


            }
            if(courseName.contains("swift_programs"))
            {

                courseList = getResources().getStringArray(R.array.swift_programs);
                courseTitle = "Swift Programs";
                logoImage.setImageResource(R.drawable.swift4);
            }
            if(courseName.contains("php_course"))
            {

                courseList = getResources().getStringArray(R.array.php_course);
                courseTitle = "Php Course";
                logoImage.setImageResource(R.drawable.php2);
            }
            if(courseName.contains("java_interview"))
            {  courseList = getResources().getStringArray(R.array.java_interview);
                courseTitle = "Java Interview";
                logoImage.setImageResource(R.drawable.java7);
            }
            if(courseName.contains("python_interview"))
            {  courseList = getResources().getStringArray(R.array.python_interview);
                courseTitle = "Python Interview";

            }
            if(courseName.contains("c_interview"))
            {  courseList = getResources().getStringArray(R.array.c_interview);
                courseTitle = "C Interview";
                logoImage.setImageResource(R.drawable.c1);
            }
            if(courseName.contains("cpp_interview"))
            {  courseList = getResources().getStringArray(R.array.cpp_interview);
                courseTitle = "C++ Interview";
                logoImage.setImageResource(R.drawable.cpp4);
            }
            if(courseName.contains("php_interview"))
            {  courseList = getResources().getStringArray(R.array.php_interview);
                courseTitle = "Php Interview";
                logoImage.setImageResource(R.drawable.php2);
            }
            if(courseName.contains("javascript_interview"))
            {  courseList = getResources().getStringArray(R.array.javascript_interview);
                courseTitle = "Javascript Interview";
                logoImage.setImageResource(R.drawable.javascript);
            }
            if(courseName.contains("swift_interview"))
            {  courseList = getResources().getStringArray(R.array.swift_interview);
                courseTitle = "Swift Interview";
                logoImage.setImageResource(R.drawable.swift5);
            }
            if(courseName.contains("csharp_interview"))
            {  courseList = getResources().getStringArray(R.array.csharp_interview);
                courseTitle = "C# Interview";
                logoImage.setImageResource(R.drawable.csharp1);
            }
            courseNameText.setText(courseTitle);


            courseListArrayList = new ArrayList<String>(Arrays.asList(courseList));
            courseListAdapter = new CourseListAdapter(CourseListActivity.this,courseName,courseListArrayList);
            courseListRecycler.setAdapter(courseListAdapter);
            courseListRecycler.setLayoutManager(new LinearLayoutManager(this));


            adapter = new ArrayAdapter<String>(this,
                   R.layout.listview_item, courseList);



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
                    (CourseListActivity.this).courseListAdapter.getFilter().filter(newText);
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
                  courseAssetUrl = "file:///android_asset/"+ courseName +"/"+ courseList[i]+".html";

           //     intentForCourseViewer();

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

        courseListRecycler.startAnimation(anim);
      //  list.setBackgroundColor(Color.parseColor("#ffffff00"));



  //   list.addView(getLayoutInflater().inflate(R.drawable.c,R));
        courseListView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        backPressed = true;
        if (interstitialAd != null) {
            interstitialAd.show(this);

        }else{
            backPressed = false;
            super.onBackPressed();
        }
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

//    public void intentForCourseViewer(Intent intent)
//    {
//        backPressed= false;
//        startActivity(intent);
//    }

    public void showInterstitialAdOnItemClick()
    {
        backPressed = false;
             if (interstitialAd != null) {
                    interstitialAd.show(CourseListActivity.this);
                }
                else
             {
                 startActivity(CourseListAdapter.intentForContentViewer);

             }

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



                                        // go to back page
                                        if(backPressed) {
                                            onBackPressed();
                                        }
                                        else {
                                            // go to the next activity
                                            startActivity(CourseListAdapter.intentForContentViewer);
                                        }

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
    public ArcProgress getArchProgress()
    {

        return circleProgressBar;
    }
    public void setCircleProgressBar(ArrayList<String> completedCourseList)
    {
        float competedProgress = (float)  completedCourseList.size()/courseListArrayList.size()*100;

        Log.d("competedProgressPercent",
                "completedCourseList.size() "+ completedCourseList.size()+" courseListArrayList.size() "+courseListArrayList.size()+" competedProgress "+competedProgress);

        circleProgressBar.setProgress((int) competedProgress);
    }
    private void setToolbar() {
        Objects.requireNonNull(toolBar.getOverflowIcon()).setTint(Color.WHITE);
        toolBar.getMenu().add(Menu.NONE, 1, Menu.NONE, "Clear Progress");
        toolBar.getMenu().add(Menu.NONE, 1, Menu.NONE, "Clear Favourites");
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getTitle()=="Clear Progress"){

                    {
                        // clear the completed arraylist in paper by adding a empty recyclerview
                        ArrayList<String> arrayList = new ArrayList<>();
                        Paper.book().write(CourseListAdapter.Completed_Course_Tag,arrayList);
                        setCircleProgressBar(arrayList);

                        courseListAdapter.clearCompleteCourseArrayList();
                        ToastHelper.showCustomToast(CourseListActivity.this,"Progress cleared","#f64042");
                    }
                }
                if(item.getTitle()=="Clear Favourites"){

                    {
                        // clear the completed arraylist in paper by adding a empty recyclerview
                        ArrayList<String> arrayList = new ArrayList<>();
                        Paper.book().write(CourseListAdapter.Favourite_Course_Tag,arrayList);
                        courseListAdapter.clearFavCourseArrayList();
                        ToastHelper.showCustomToast(CourseListActivity.this,"Favourites cleared","#f64042");


                    }
                }
                return false;
            }
        });
    }

    public void setCourseTitle(String courseName)
    {

        courseNameText.setText(courseName);
    }

    public void showInterviewDialog(int position) {


        LayoutInflater factory = LayoutInflater.from(CourseListActivity.this);

        View interviewDialogLayout = factory.inflate(R.layout.interview_dialog_layout, null);

        AlertDialog interviewDialog = new AlertDialog.Builder(CourseListActivity.this).create();


        interviewDialog.setView(interviewDialogLayout);
        interviewDialog.show();


        Button closeButton = interviewDialogLayout.findViewById(R.id.closeButton);
        WebView interviewWebView = interviewDialogLayout.findViewById(R.id.interviewWebView);
        interviewDialog.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        if(courseName.contains("javascript_interview"))
            interviewWebView.loadUrl("file:///android_asset/"+courseName+"/"+String.valueOf(position+1)+".html");
        else if (courseName.contains("python_interview"))
            interviewWebView.loadUrl("file:///android_asset/"+courseName+"/"+String.valueOf(position+1)+".html");
        else
            interviewWebView.loadUrl("file:///android_asset/"+courseName+"/"+solutionListArray[position]);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interviewDialog.dismiss();
            }
        });
    }



}
