package com.nitish.programmershub;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


import java.io.IOException;


public class ContentViewerActivity extends AppCompatActivity {
WebView webView;
//PDFView pdfView;
String url,data;
LottieAnimationView lottieAnimationView ;
String position;

String course;
String[] asset_list,python;
int p;

AdView adView;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        webView = (WebView) findViewById(R.id.webview1);

        lottieAnimationView = (LottieAnimationView)findViewById(R.id.lottie_viewer);
        //    pdfView =(PDFView)findViewById(R.id.pdfview) ;
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        getSupportActionBar().setBackgroundDrawable(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));


//admob inter ads
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(getResources().getString(R.string.admob_interid));
//       mInterstitialAd.loadAd(new AdRequest.Builder().build());
//        mInterstitialAd.setAdListener(new AdListener()
//        {
//            public void onAdLoaded()
//            {
//                // Call displayInterstitial() function when the Ad loads
//                displayInterstitial();
//            }
//        });


        setAdmobBannerAdView();

    //  Toast.makeText(this,"."+getResources().getString(R.string.admob_interid),Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        data = intent.getStringExtra("data");
        course = intent.getStringExtra("course");
        position = intent.getStringExtra("position");



if(course!=null)
{
   if(!isNetworkAvailable())
   {lottieAnimationView.setVisibility(View.VISIBLE);

   }


    url="";
    data="";
    p=0;
    position = "0";


    webView.setWebViewClient(new WebViewClient() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress < 100 ){


                progressBar.setVisibility(ProgressBar.VISIBLE);
            }


            if (newProgress == 100){
                progressBar.setVisibility(ProgressBar.GONE);
            }
        }
    });
}
else{
    course="";
}

        p = Integer.parseInt(position);
        p = p + 1;
        try {
            asset_list = getAssets().list(data);
          //  python = getAssets().list("python_programs");

        } catch (IOException e) {
            e.printStackTrace();
        }

     //  Toast.makeText(this, "" + p + url, Toast.LENGTH_SHORT).show();
        if (url.contains("csharp_programs")) { //pdfView.getLayoutParams().height = ActionBar.LayoutParams.WRAP_CONTENT;
            //  pdfView.setVisibility(View.INVISIBLE);
           webView.setInitialScale(200);

            webView.loadUrl("file:///android_asset/csharp_programs/" + p + ".html");
        } else if (url.contains("python_course"))
        {  webView.setInitialScale(150);

//            p=p-1;
        //    Toast.makeText(this, "data is " + data, Toast.LENGTH_SHORT).show();
            webView.loadUrl("file:///android_asset/"+data+"/" +p+ ".html");
        }
        else if (url.contains("javascript_programs"))
        {
            p=p-1;
            webView.loadUrl("file:///android_asset/javascript_programs/" + asset_list[p]);
        }
        else if (url.contains("c_programs"))
        {
            webView.loadUrl("file:///android_asset/c_programs/" + p + ".html");
        }
        else if (url.contains("swift_programs"))
        {webView.setInitialScale(150);
            webView.loadUrl("file:///android_asset/swift_programs/" + p + ".html");
        }
        else if (url.contains("php_programs"))
        {
            p=p-1;
            webView.loadUrl("file:///android_asset/php_programs/" + asset_list[p] );
        }
        else if (url.contains("python_programs"))
        {

            webView.loadUrl("file:///android_asset/python_programs/" +  p + ".html" );
        }
        else if (url.contains("php_course"))
        {
            webView.loadUrl("file:///android_asset/php_course/" + p + ".html");
        }
      //compilers
        else if (course.contains("python_compiler"))
        {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.programiz.com/python-programming/online-compiler/");
        }
        else if (course.contains("java_compiler"))
        {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.tutorialspoint.com/compile_java_online.php");
        }
        else if (course.contains("c_compiler"))
        {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.tutorialspoint.com/compile_c_online.php");
        }
        else if (course.contains("cpp_compiler"))
        {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.tutorialspoint.com/compile_cpp_online.php");
        }
        else if (course.contains("php_compiler"))
        {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.tutorialspoint.com/execute_php_online.php");
        }
        else if (course.contains("javascript_compiler"))
        {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.tutorialspoint.com/online_javascript_editor.php");
        }
        else if (course.contains("csharp_compiler"))
        {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.tutorialspoint.com/compile_csharp_online.php");
        }
        else if (course.contains("swift_compiler"))
        {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.tutorialspoint.com/compile_swift_online.php");
        }

        else

  webView.loadUrl(url);
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


//    @Override
//    public void onBackPressed() {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//            mInterstitialAd.setAdListener(new AdListener() {
//                @Override
//                public void onAdClosed() {
//                    super.onAdClosed();
//
//               onBackPressed();
//                }
//            });
//        }else{
//            super.onBackPressed();
//        }
//    }


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

}
