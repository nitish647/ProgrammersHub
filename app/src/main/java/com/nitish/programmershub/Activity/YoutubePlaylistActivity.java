package com.nitish.programmershub.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;

import com.google.android.gms.ads.AdRequest;
import com.nitish.programmershub.Design_helper;
import com.nitish.programmershub.R;
import com.nitish.programmershub.Youtube_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class YoutubePlaylistActivity extends AppCompatActivity  {
    private TextView mTextViewResult;
   // String key = "AIzaSyA-dlBUjVQeuc4a6ZN4RkNUYDFddrVLxrA",key2="AIzaSyCW14_b6iJYRlFskseXTp_GelzAVYlGiqY",key3="AIzaSyA-dlBUjVQeuc4a6ZN4RkNUYDFddrVLxrA";
    private RequestQueue mQueue;
    public RecyclerView recyclerView;
    static int j;
    Intent intent2;
    LinearLayout linear_advcontain;
    public Youtube_adapter youtube_adapter;
    public static ArrayList arrayList;
    public static ArrayList arrayList2;
    public  ArrayList videoid;
    public static ArrayList<String> url;
    public LottieAnimationView lottieAnimationView,lottieAnimationView2;
    public ArrayList playlistid;
 public static    LinearLayoutManager linearLayoutManager;

    ListView testlist;
    public  String title = null,imageurl =null, Playlist_id = "", tok = "", murl = "", en = "&pageToken=";


    String playlist_id = "";
//    String playlist_id2= "PLFs4vir_WsTwEd-nJgVJCZPNL3HALHHpF";
private AdView fb_banner;
    public String passtr = "exoplayer";
    ArrayAdapter arrayAdapter;
  public    String nextPageToken = "";
    ImageLoader imageLoader;
    public Context context = getBaseContext();
    public static Context baseContext;

    public com.google.android.gms.ads.AdView adView;

ArrayList empty;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_playlist);
        String key =  (String) getResources ().getText(R.string.startapps);
empty = new ArrayList();
        lottieAnimationView2 = (LottieAnimationView)findViewById(R.id.exo_internet_lottie);

linear_advcontain=(LinearLayout)findViewById(R.id.linear_adview);
        baseContext = getBaseContext();
        recyclerView = (RecyclerView) findViewById(R.id.recycler1);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.exo_lottie);
        arrayList = new ArrayList();
        arrayList2 = new ArrayList();
        videoid = new ArrayList();
        getSupportActionBar().setBackgroundDrawable(Design_helper.set_Colors("#00BFA5","#00C853", (float) 0, GradientDrawable.Orientation.LEFT_RIGHT));
        Intent intent = getIntent();
        playlist_id =intent.getStringExtra("plid");
        //checking for internet
if(!isNetworkAvailable())
    lottieAnimationView2.setVisibility(View.VISIBLE);



// youtube activity 2  showing the video list of selected playlist




        //set google admob
        setAdmobBannerAdView();



        //setting recyclerview
        youtube_adapter = new Youtube_adapter(arrayList, arrayList2, getApplicationContext(), passtr,playlistid,videoid,empty);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(youtube_adapter);
        Object view = null;


        url = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isScrolling = false;

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState ==  1) {

                    this.isScrolling = true;
                }
            }
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentItem = linearLayoutManager.getChildCount();
                int totlItem = linearLayoutManager.getItemCount();

                int scrolledOutItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (currentItem + scrolledOutItem == totlItem) {



                    this.isScrolling = false;
                    jsonParse();
                }
            }
        });
        jsonParse();








    }


    private void fetchdata() {
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
             //  jsonParse();
           }
       },5000);
    }


    public void jsonParse() {

        murl = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet%2CcontentDetails&maxResults=7&playlistId=" + playlist_id + "&key=" + (String) getResources ().getText(R.string.key);

        lottieAnimationView.setVisibility(View.VISIBLE);
if (this.nextPageToken!= null)
{
    murl = murl+nextPageToken;
}
        if(nextPageToken == null){
            lottieAnimationView.setVisibility(View.GONE);
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, murl, null,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {

lottieAnimationView.setVisibility(View.GONE);
                        try {
                            //
                          nextPageToken =  "&pageToken="+response.getString("nextPageToken");

                            Toast.makeText(getBaseContext(),"tok "+tok,Toast.LENGTH_SHORT);
                            JSONArray jsonArray = response.getJSONArray("items");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject employee = jsonArray.getJSONObject(i);
                                JSONObject employee2 = employee.getJSONObject("snippet");
                                //   JSONObject employee3 = employee2.getJSONObject("title");
                                JSONObject employee3 = employee.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("default");
                                JSONObject vid_id=employee.getJSONObject("snippet").getJSONObject("resourceId");
                                title = employee2.getString("title");
                                imageurl = employee3.getString("url");
                                String vid=vid_id.getString("videoId");
                                videoid.addAll(Collections.singleton(vid));
                                arrayList2.addAll(Collections.singleton(imageurl));
                                arrayList.addAll(Collections.singleton(title));




                            }

                            setrecycler();




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext()," error"+error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });





        mQueue.add(request);      }



    public void setrecycler()
    {

        youtube_adapter.notifyDataSetChanged();

      //  youtube_adapter.notifyItemRangeChanged(0, youtube_adapter.getItemCount());









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


    public void onClickCalled() {
        Toast.makeText(this,"running",Toast.LENGTH_LONG).show();
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
}
