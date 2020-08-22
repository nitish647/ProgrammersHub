package com.nitish.programmershub;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.List;

public class Youtube extends YouTubeBaseActivity {
    YouTubeThumbnailView youTubeThumbnailView;
    RecyclerView recyclerView;
  YouTubePlayerView  youTubePlayerView;
  View v;
    WebView webView;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LinearLayout linearLayout = new LinearLayout(this);
//        ScrollView scrollView = new ScrollView(this);
//linearLayout.setOrientation(LinearLayout.VERTICAL);
//
//        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
//                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
//                LinearLayoutCompat.LayoutParams.MATCH_PARENT
//        );
//        scrollView.setLayoutParams(param);
//scrollView.removeAllViews();
//        linearLayout.setLayoutParams(param);
//        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
//                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
//                900
//
//        );
//        LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(
//                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
//                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
//
//        );
//        final ArrayList arrayList = new ArrayList();
//        arrayList.add("https://www.google.com/");
//        arrayList.add("https://abhiandroid.com/createandroidapp/youtube-app");
//        arrayList.add("https://www.wap.in/");
//ListView listView = new ListView(this);
//listView.setLayoutParams(param);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this,linearLayout.getLayoutMode(),arrayList );
//listView.setAdapter(arrayAdapter);
//
//        setContentView(linearLayout);
//LinearLayout linearLayout1 = new LinearLayout(this);
//linearLayout1.setOrientation(LinearLayout.VERTICAL);
//linearLayout1.setLayoutParams(param);
//linearLayout.addView(scrollView);
//scrollView.addView(linearLayout1);
//
//
//       // youTubePlayerView =(YouTubePlayerView)findViewById(R.id.youtube);\
//
//
////for (int i = 0 ; i<= 2 ; i++)
////{  webView = new WebView(this);
////
////        webView.loadUrl((String) arrayList.get(i));
////        webView.setBackgroundColor(Color.parseColor("#E64A19"));
////
////       webView.setLayoutParams(param2);
////    linearLayout1.addView(webView);
////        }
//        final ArrayList arrayList1 = new ArrayList();
//        arrayList1.add("PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g");
//        arrayList1.add("PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g");
//        arrayList1.add("PL4E272D49F6209D23");
//        for (int i = 0 ; i< arrayList1.size() ; i++)
//
//
//
//        {  TextView textView = new TextView(this);
//            youTubeThumbnailView= new YouTubeThumbnailView(this);
//            textView.setLayoutParams(param3);
//            youTubeThumbnailView.setLayoutParams(param2);
//            final int finalI = i;
//
//            textView.setText((CharSequence) arrayList.get(i));
//            youTubeThumbnailView.initialize("AIzaSyBPINTET7N3HpcU8F9nlLuElMv8SBqykWY", new YouTubeThumbnailView.OnInitializedListener() {
//                @Override
//                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
//                    youTubeThumbnailLoader.setPlaylist((String) arrayList1.get(finalI));
//
//
//                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
//                        @RequiresApi(api = Build.VERSION_CODES.O)
//                        @Override
//                        public void onThumbnailLoaded(final YouTubeThumbnailView youTubeThumbnailView, String s) {
//
//
//youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public void onClick(View view) {
//        Toast.makeText(view.getContext(),"dffgfd  "+arrayList1.get(finalI),Toast.LENGTH_LONG).show();
//    }
//});
//                        }
//                        @RequiresApi(api = Build.VERSION_CODES.O)
//                        @Override
//                        public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
//                            youTubeThumbnailView.setTooltipText("failed");
//
//                        }
//                    });
//                }
//
//                @Override
//                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
//
//                }
//            });
//
//
//            linearLayout1.addView(youTubeThumbnailView);
//            linearLayout1.addView(textView);
//        }

//            YouTubePlayerView  youTubePlayerView = new YouTubePlayerView(this);
//
//youTubePlayerView.initialize("AIzaSyBPINTET7N3HpcU8F9nlLuElMv8SBqykWY", new YouTubePlayer.OnInitializedListener() {
//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//        youTubePlayer.cueVideo("4Y0N43i2RNs");
//
//        Toast.makeText(getApplicationContext(), " succcess", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//    }
//});
    }



}