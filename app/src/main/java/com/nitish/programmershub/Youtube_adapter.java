package com.nitish.programmershub;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.nitish.programmershub.Activity.VideoViewerActivity;
import com.nitish.programmershub.Activity.YoutubePlaylistActivity;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.Random;

public class Youtube_adapter extends RecyclerView.Adapter<Youtube_adapter.ViewHolder> implements View.OnClickListener{
 //   public static  String passstr ;

    Intent intent;
    private ArrayList mtitle ;
    private ArrayList mImageUrl ;
public String mpassstr;
private  ArrayList id;
    private  ArrayList videoid;
    private ArrayList itemcount;

    private Context context;
    private Random mRandom = new Random();
    public Youtube_adapter(ArrayList mNames, ArrayList mImageUrl, Context context, String passstr, ArrayList mid, ArrayList videoid, ArrayList mitemcount) {
        this.mtitle = mNames;
        this.mImageUrl= mImageUrl;
        this.context = context;
        this.mpassstr = passstr;
        this.id= mid;
        this.videoid = videoid;
    this.itemcount = mitemcount;
    }



    @NonNull

    @Override
    public Youtube_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


      /*  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_item,parent,false);

        return new ViewHolder(view);*/
        View v;
if (mpassstr.toLowerCase().contains("exoplayer"))
    v = LayoutInflater.from(context).inflate(R.layout.recycler_view_item,parent,false);
else
    v = LayoutInflater.from(context).inflate(R.layout.youtube_adapter,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Youtube_adapter.ViewHolder holder, final int position) {
        holder.text.setText((String) mtitle.get(position));
         if (!mpassstr.toLowerCase().contains("exoplayer"))
         {
          holder.text2.setText((CharSequence) "Playlist");
           }
//        holder.image.setImageResource((Integer) mImageUrl.get(position));

          //  Toast.makeText(context , "position "+position,Toast.LENGTH_SHORT).show();
Picasso.get().load((String) mImageUrl.get(position)).fit().placeholder(R.drawable.puzzle_holder).into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                if (mpassstr.contains("youtube_play"))
                {
 intent = new Intent( view.getContext(), YoutubePlaylistActivity.class);
 intent.putExtra("plid",(String) id.get(position));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);

                }
                if (mpassstr.contains("exoplayer"))
                {   intent = new Intent(view.getContext(), VideoViewerActivity.class);
            //    Toast.makeText(view.getContext(),"passing "+videoid.get(position),Toast.LENGTH_LONG).show();
                intent.putExtra("videoid", String.valueOf(videoid.get(position )));
                intent.putExtra("title", String.valueOf(mtitle.get(position)));

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);
//                    StartAppAd.showAd(context);
              }


            }
        });

    }



    @Override
    public int getItemCount() {
        return mtitle.size();


    }

    @Override
    public void onClick(View view) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView text;
TextView text2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if(mpassstr.toLowerCase().contains("exoplayer"))
            {
                this.image= itemView.findViewById( R.id.exo_img);
                this.text= itemView.findViewById( R.id.exo_txt);
                text.setTextSize(15);
            }
            else {
            this.image= itemView.findViewById( R.id.image);
            this.text= itemView.findViewById( R.id.text);
            text2 = itemView.findViewById(R.id.text2);
            text2.setTextSize(15);
            text2.setTextColor(Color.parseColor("#F5F5F5"));
                text.setTextSize(17);}
text.setGravity(Gravity.CENTER);
text.setTextColor(Color.parseColor("#F5F5F5"));
            image.setBackgroundColor(Color.parseColor("#E91E63"));
        }

    }
}


