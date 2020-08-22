package com.nitish.programmershub;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final static String Tag = "StaggeredRecyclerView";



    public static  String passstr ;

    private ArrayList<String> mNames = new ArrayList<>();
    public ArrayList<Integer> mImageUrl = new ArrayList<Integer>();
    private Context context;
    private Random mRandom = new Random();
    public RecyclerViewAdapter(ArrayList<String> mNames, ArrayList<Integer> mImageUrl, Context context) {
        this.mNames = mNames;
        this.mImageUrl= mImageUrl;
        this.context = context;

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


      /*  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_item,parent,false);

        return new ViewHolder(view);*/
        View v = LayoutInflater.from(context).inflate(R.layout.layout_grid_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //holder.image.getLayoutParams().height = getRandomIntInRange(650,375);

        holder.text.setText(mNames.get(position));

        holder.image.setImageResource(mImageUrl.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

Intent intent = new Intent(view.getContext(), Activity2.class);
intent.putExtra("course",mNames.get(position));
                context.startActivity(intent);
//Toast.makeText(context,mNames.get(position),Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return  mImageUrl.size();

    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView text;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image= itemView.findViewById( R.id.image_view);
            this.text= itemView.findViewById( R.id.name);
            image.setClipToOutline(true);

        }

    }
}
