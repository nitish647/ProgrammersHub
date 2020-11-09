package com.nitish.programmershub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public AdView mAdView;
    ActionBarDrawerToggle t;
  static   Context context;
  static String string;
  static HashMap hashMap = new HashMap();

    ListView navigaton_list;
 static   AsyncTask<Void,Void, HashMap> asyncTask;
    private com.google.android.gms.ads.InterstitialAd mInterstitialAd;
//
    ArrayList mImageUrl = new ArrayList<>();
    ArrayList mNames= new ArrayList<>();

    private InterstitialAd fb_interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mAdView = (AdView)findViewById(R.id.main_banner);
navigaton_list =(ListView)findViewById(R.id.main_navigation_list);
context = getApplicationContext();
setNavigaton_listview();
        String appid = (String) getResources ().getText(R.string.startapps);

//set_asynctask();
        set_data();
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


                if(id ==R.id.about_us) {
                    webView.loadUrl("file:///android_asset/about_us.html");

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
    public void setNavigaton_listview()

    {
        ArrayList arrayList = new ArrayList();
        arrayList.add("Share");
        arrayList.add("More Apps");
        arrayList.add("Privacy policy");
        arrayList.add("About us");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.listview_text,arrayList);
        navigaton_list.setAdapter(arrayAdapter);
navigaton_list.setScrollContainer(false);
        navigaton_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WebView webView = new WebView(view.getContext());

                if(i==0)
                {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            "Hey check out my app at: https://play.google.com/store/apps/details?id=com.nitish.programmershub");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
                if(i==1)
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=GLASSWEB"));
                    startActivity(intent);
                }
                if(i==2)
                {
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
                if(i==3)
                {
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


            }
        });
    }
    public static void  set_asynctask()
    {
       final String url = "https://5eebcc365e298b0016b6946c.mockapi.io/nitish/ads";
        final String[] text = new String[1];
        hashMap.put("hjsdfhdsf","kjsdfkjs");
        asyncTask= new AsyncTask<Void,Void, HashMap>() {



            @Override
            protected HashMap doInBackground(Void... strings) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray  jsonArray = new JSONArray(response);
                            string = jsonArray.toString();
                            hashMap.put("urdl","aajad");
                            hashMap.put("url",jsonArray.toString());

                        } catch (JSONException e) {
                            Design_helper.show_toast(context,"exception "+e.toString());
                            e.printStackTrace();
                        }
                 }

                }, new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                      Design_helper.show_toast(context,"volley error "+error.getMessage());

                    }
                });

                RequestQueue requestQueue = Volley.newRequestQueue(context);

                //adding the string request to request queue
                requestQueue.add(stringRequest);
                return hashMap;
            }



            @Override
            protected void onPostExecute(HashMap hashMap2) {

                super.onPostExecute(hashMap2);
                Toast.makeText(context,hashMap2.toString(),Toast.LENGTH_LONG).show();
            }
        }.execute();
    }
    public void set_data()
    {



        View view= getLayoutInflater().inflate(R.layout.main_ads,null);

      final ImageView ad_img = (ImageView)findViewById(R.id.ads_img);
        final TextView text_name=(TextView)findViewById(R.id.ads_name) ;
        final TextView text_description=(TextView)findViewById(R.id.ads_description) ;
        final Button button =(Button)findViewById(R.id.ads_install_btn);
        final ConstraintLayout ads_constraintLayout =(ConstraintLayout)findViewById(R.id.ads_contatint);
        ad_img.setImageResource(R.drawable.java7);
        final String url = "https://5eebcc365e298b0016b6946c.mockapi.io/nitish/ads";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray  jsonArray = new JSONArray(response);
JSONObject jsonObject = new JSONObject();
JSONObject jsonObject1 ;
jsonObject = jsonArray.getJSONObject(0);
jsonObject1 = jsonObject.getJSONArray("main").getJSONObject(0);

final String url = jsonObject1.getString("url");
final String img_file = jsonObject1.getString("img_file");
String name = jsonObject1.getString("name");
String desc = jsonObject1.getString("description");
button.setBackground(Design_helper.set_Colors("#2CA839","#27B636", (float) 20, GradientDrawable.Orientation.LEFT_RIGHT));
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
});

text_name.setText(name);
text_description.setText(desc);
Picasso.get().load(img_file).into(ad_img);
                    string = jsonArray.toString();

                    hashMap.put("url",jsonObject.getJSONArray("main").getJSONObject(0).get("url"));
                } catch (JSONException e) {
                    Design_helper.show_toast(context,"exception "+e.toString());
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error) {
                ads_constraintLayout.setVisibility(View.GONE);
                Design_helper.show_toast(context,"welcome  ");

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //adding the string request to request queue
        requestQueue.add(stringRequest);


    }
}
