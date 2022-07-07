package com.nitish.programmershub.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.nitish.programmershub.Activity.MainActivity;
import com.nitish.programmershub.Activity.StudyMaterialsActivity;
import com.nitish.programmershub.R;

import java.util.ArrayList;
import java.util.Random;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.ViewHolder> {
    private final static String Tag = "StaggeredRecyclerView";



    public static  String courseName ;

    private ArrayList<String> mNames = new ArrayList<>();
    public ArrayList<Integer> mImageUrl = new ArrayList<Integer>();
    private Context context;
    private Random mRandom = new Random();
    public MainPageAdapter(ArrayList<String> mNames, ArrayList<Integer> mImageUrl, Context context) {
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

    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {

        //holder.image.getLayoutParams().height = getRandomIntInRange(650,375);

        holder.text.setText(mNames.get(holder.getAbsoluteAdapterPosition()));

        holder.image.setImageResource(mImageUrl.get(holder.getAbsoluteAdapterPosition()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {


                courseName = mNames.get(holder.getAbsoluteAdapterPosition());
//
//Intent intent = new Intent(view.getContext(), StudyMaterialsActivity.class);
//intent.putExtra("course",mNames.get(holder.getAbsoluteAdapterPosition()));
//                context.startActivity(intent);

                ((MainActivity)context).intentForStudyMaterials();


            }
        });
    }

    @Override
    public int getItemCount() {
        return  mImageUrl.size();

    }



    public static  class ViewHolder extends RecyclerView.ViewHolder
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
