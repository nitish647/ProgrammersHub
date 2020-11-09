package com.nitish.programmershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.DnsResolver;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import okhttp3.ResponseBody;

public class Youtube_play extends AppCompatActivity {
    private RequestQueue mQueue;
    public AdView mAdView;

private ArrayList api2 , playlist,playlist2;
LottieAnimationView lottieAnimationView;
LinearLayout linearLayout;
    public com.facebook.ads.InterstitialAd fb_InterstitialAd;

    String playlist_id2= "PLFs4vir_WsTwEd-nJgVJCZPNL3HALHHpF";
    ArrayList <String> java_eng,java_hind,python_eng,python_hind,cpp_eng,cpp_hind,csharp_eng,csharp_hind,swift_eng,swift_hind,cprog_eng,cprog_hind,php_eng,php_hind,javascript_eng,javascript_hind;
    public String passtr = "youtube_play";
  //  String[] api =    {"https://www.googleapis.com/youtube/v3/playlists?part=snippet&id="+playlist_id2+"&key="+(String) getResources ().getText(R.string.startapps),"https://www.googleapis.com/youtube/v3/playlists?part=snippet&id=PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g-Al&key="+key,"https://www.googleapis.com/youtube/v3/playlists?part=snippet&id="+playlist_id2+"&key="+key};
    ArrayList titlelist = null, imageurl_list=null,playlist_id,itemlist;
  public   GridLayoutManager gridLayoutManager;
    String[] java={"PLFs4vir_WsTwEd-nJgVJCZPNL3HALHHpF","PLFE2CE09D83EE3E28"};
    String title, imageurl,id,itemcount;
    RecyclerView recyclerView;
    TextView textView;
    String data;
    ArrayList empty,api3;

   static String part="";
    Youtube_adapter youtube_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_play);
        mAdView = (AdView)findViewById(R.id.play_banner);
        String key =  (String) getResources ().getText(R.string.key);
        lottieAnimationView = (LottieAnimationView)findViewById(R.id.lottie_play);
        recyclerView = (RecyclerView)findViewById(R.id.playrecycler);
        linearLayout=(LinearLayout)findViewById(R.id.linear_play);
        getSupportActionBar().setBackgroundDrawable(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));


        // youtube activity 1   showoing the playlist names and thumnails









        //checking internet
if(!isNetworkAvailable())
{
lottieAnimationView.setVisibility(View.VISIBLE);}

//facebook inter
        try {
            fb_InterstitialAd = new com.facebook.ads.InterstitialAd(this,getResources().getString(R.string.fb_test_interid));
//            fb_InterstitialAd.loadAd();
//            facebook_inter_listner();

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),".",Toast.LENGTH_LONG).show();
        }


        //admob banner

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        playlist_id = new ArrayList();
empty = new ArrayList();
api2 = new ArrayList();
api3 = new ArrayList();
itemlist = new ArrayList();

empty.add("");
playlist2 = new ArrayList();

       Intent in = getIntent();
        data = in.getStringExtra("course").toLowerCase();
if(data.contains("java_eng"))

    playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.java_eng)));

if(data.contains("java_hindi"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.java_hind)));
if(data.contains("python_eng"))
{
    playlist2.add("PLS1QulWo1RIYt4e0WnBp-ZjCNq8X0FX0J");
    playlist2.add("PL-osiE80TeTt2d9bfVyTiXJA-UTHn6WwU");
    playlist2.add("PL5tcWHG-UPH112e7AN7C-fwDVPVrt0wpV");
    playlist2.add("PLd3UqWTnYXOnvJnzMN9nayiKmo5aT9Xzv");
   // playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.python_eng)));
}



if(data.contains("python_hindi"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.python_hind)));
 if(data.contains("c_eng"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.cprog_eng)));
 if(data.contains("c_hindi"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.cprog_hind)));
if(data.contains("cpp_eng"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.cpp_eng)));
if(data.contains("cpp_hindi"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.cpp_hind)));
if(data.contains("swift_eng"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.swift_eng)));
if(data.contains("swift_hindi"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.swift_hind)));
if(data.contains("csharp_eng"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.csharp_eng)));
if(data.contains("csharp_hindi"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.csharp_hind)));
if(data.contains("php_eng"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.php_eng)));
if(data.contains("php_hindi"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.php_hind)));
if(data.contains("javascript_eng"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.javascript_eng)));
if(data.contains("javascript_hindi"))

            playlist2.addAll(Arrays.asList(getResources().getStringArray(R.array.javascript_hind)));


int x = playlist2.size();
for (int i= 0;i<x;i++)
{ api2.add("https://www.googleapis.com/youtube/v3/playlists?part=snippet&id="+playlist2.get(i)+"&key="+key);
api3.add("https://www.googleapis.com/youtube/v3/playlists?part=contentDetails&id="+playlist2.get(i)+"&key="+key);}
        mQueue = Volley.newRequestQueue(this);

        titlelist = new ArrayList();
        //  titlelist.add("bbnnb");
        imageurl_list = new ArrayList();
        jsonParse2();
        jsonParse();


        youtube_adapter = new Youtube_adapter(titlelist,imageurl_list,getBaseContext(),passtr,playlist_id,empty,itemlist);
        recyclerView.setAdapter(youtube_adapter);
        gridLayoutManager = new GridLayoutManager(getBaseContext() ,2);
        recyclerView.setLayoutManager(gridLayoutManager);

    }
    private void jsonParse() {

        int x = api2.size();
        for(int i = 0;i<x;i++) {

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, (String) api2.get(i), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("items");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject employee = jsonArray.getJSONObject(i);
                                    JSONObject employee2 = employee.getJSONObject("snippet");
                                    //     JSONObject employee3 = employee2.getJSONObject("title");
                                    JSONObject employee3 = employee.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium");
                                    title = employee2.getString("title");
                                    imageurl = employee3.getString("url");

                                    id = employee.getString("id");
                                    playlist_id.addAll(Collections.singleton(id));
                                    titlelist.addAll(Collections.singleton(title));
                                    imageurl_list.addAll(Collections.singleton(imageurl));

                               //     youtube_adapter = new Youtube_adapter(titlelist,imageurl_list,getBaseContext(),passtr,playlist_id,empty,itemlist);

                                }
                                youtube_adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            mQueue.add(request);}
    }
    private void jsonParse2() {
        int x = api3.size();
        for(int i = 0;i<api3.size();i++) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, (String) api3.get(i), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("items");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject employee = jsonArray.getJSONObject(i);
                                    JSONObject employee2 = employee.getJSONObject("contentDetails");
                                    //     JSONObject employee3 = employee2.getJSONObject("title");

                                   itemcount = employee2.getString("itemCount");

                                   itemlist.addAll(Collections.singleton(itemcount));

                                //   youtube_adapter = new Youtube_adapter(titlelist,imageurl_list,getBaseContext(),passtr,playlist_id,empty,itemlist);
                                  //  Toast.makeText(getBaseContext(),"itemcount "+itemcount,Toast.LENGTH_LONG).show();

                                }
                                youtube_adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            mQueue.add(request);}
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
    public void facebook_inter_listner()
    {
        fb_InterstitialAd.loadAd((com.facebook.ads.InterstitialAd.InterstitialLoadAdConfig) fb_InterstitialAd.buildLoadAdConfig().withAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                if(!fb_InterstitialAd.isAdInvalidated())
                fb_InterstitialAd.show();

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        }));
    }

    @Override
    protected void onDestroy() {

        if(fb_InterstitialAd!=null)
            fb_InterstitialAd.destroy();
        super.onDestroy();
    }

}
