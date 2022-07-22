package com.nitish.programmershub.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nitish.programmershub.Activity.ContentViewerActivity;
import com.nitish.programmershub.Activity.CourseListActivity;
import com.nitish.programmershub.Activity.InterviewActivity;
import com.nitish.programmershub.CommonMethods;
import com.nitish.programmershub.Design_helper;
import com.nitish.programmershub.R;
import com.nitish.programmershub.Utils.GetDataHelper;
import com.nitish.programmershub.Utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.MyViewHolder> implements Filterable {

    Context context;
    String courseName;
    ArrayList<String> courseListArrayList;
    public static Intent intentForContentViewer;
    ArrayList<String> favCourseArrayList;
    ArrayList<String> completedCourseArrayList;
    ArrayList<String> courseListArrayListFull;
    public  static  String Completed_Course_Tag ;// key to save the complete course arraylist in paper
    public  static  String Favourite_Course_Tag ;// key to save the Favourite course arraylist in paper
    public CourseListAdapter(Context context, String courseName, ArrayList<String> courseListArrayList) {
        this.context = context;
        this.courseName = courseName;
        this.courseListArrayList = courseListArrayList;
        courseListArrayListFull = courseListArrayList;
        Paper.init(context);



        Completed_Course_Tag ="CompletedCourse_"+courseName;
        Favourite_Course_Tag = "FavouriteCourse_"+courseName;

        if(!Paper.book().contains(Favourite_Course_Tag))
        {
            ArrayList<String> emptyArrayList = new ArrayList<>();
            Paper.book().write(Favourite_Course_Tag,emptyArrayList);
            favCourseArrayList = new ArrayList<>();



        }
        else {
            favCourseArrayList = Paper.book().read(Favourite_Course_Tag);
//
//            if(context.getClass()== CourseListActivity.class)
//                ((CourseListActivity) context).setCircleProgressBar(completedCourseArrayList);

        }

        // for completed course
        if(!Paper.book().contains(Completed_Course_Tag))
        {
            ArrayList<String> completedArrayList = new ArrayList<>();
            Paper.book().write(Completed_Course_Tag,completedArrayList);
            completedCourseArrayList = new ArrayList<>();



        }
        else {
            completedCourseArrayList = Paper.book().read(Completed_Course_Tag);

            if(context.getClass()== CourseListActivity.class)
                ((CourseListActivity) context).setCircleProgressBar(completedCourseArrayList);

        }

    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(context).inflate(R.layout.course_list_layout,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        String courseUrl = GetDataHelper.courseAssetUrl(courseName,courseListArrayList.get(holder.getAbsoluteAdapterPosition()));
        holder.courseItemTextview.setText(courseListArrayList.get(holder.getAbsoluteAdapterPosition()));



        if(completedCourseArrayList.contains(courseUrl))
        {
            holder.readImageview.setImageResource(R.drawable.green_tick_verified);
        }
        else {
            holder.readImageview.setImageResource(R.drawable.white_tick_unverified_icon);
        }

        holder.readImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(completedCourseArrayList.contains(courseUrl))
                {
                    holder.readImageview.setImageResource(R.drawable.white_tick_unverified_icon);
                    completedCourseArrayList.remove(courseUrl);
                    ToastHelper.showCustomToast(context,"Progress Unmarked","#f64042");

                }
                else {
                    ToastHelper.showCustomToast(context,"Progress Marked","#00c197");

                    holder.readImageview.setImageResource(R.drawable.green_tick_verified);
                    completedCourseArrayList.add(courseUrl);
                }

                if(context.getClass()==CourseListActivity.class)
                ((CourseListActivity) context).setCircleProgressBar(completedCourseArrayList);


                Paper.book().write(Completed_Course_Tag,completedCourseArrayList);


            }
        });

        if(favCourseArrayList.contains(courseUrl))
        {
            holder.favImageView.setImageResource(R.drawable.heart_like);
        }
        else {
            holder.favImageView.setImageResource(R.drawable.heart_unlike);
        }

        holder.itemView.setBackground(Design_helper.set_Colors("#FFFFFF","#FFFFFF", (float) 10, GradientDrawable.Orientation.LEFT_RIGHT));

        holder.favImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(favCourseArrayList.contains(courseUrl))
                {
                    // remove from favourites
                    ToastHelper.showCustomToast(context,"Unmarked from Favourites","#f64042");

                    holder.favImageView.setImageResource(R.drawable.heart_unlike);
                    favCourseArrayList.remove(courseUrl);
                }
                else {
                    // add into  favourites
                    ToastHelper.showCustomToast(context,"Marked as Favourites","#00c197");

                    holder.favImageView.setImageResource(R.drawable.heart_like);
                    favCourseArrayList.add(courseUrl);

                }

                Paper.book().write(Favourite_Course_Tag,favCourseArrayList);
            }
        });



        if(context.getClass()== CourseListActivity.class) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // mark the item as read
                    holder.readImageview.setImageResource(R.drawable.green_tick_verified);

                    completedCourseArrayList.add(courseUrl);
                    ((CourseListActivity) context).setCircleProgressBar(completedCourseArrayList);

                    // show the dialog box for the interview
                    if(courseName.toLowerCase().contains("interview"))
                    {
                        ((CourseListActivity) context).showInterviewDialog(holder.getAbsoluteAdapterPosition());
                    }
                    // else go to the content viewer page
                    else {
                        Intent intent = new Intent(context, ContentViewerActivity.class);
                        intent.putExtra("url", courseUrl);
                        intent.putExtra("data", courseName);
                        intent.putExtra("position", String.valueOf(holder.getAbsoluteAdapterPosition()));
                        intentForContentViewer = intent;
                        ((CourseListActivity) context).showInterstitialAdOnItemClick();
                    }
                }
            });
        }else
        // for interview activity
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((InterviewActivity)context).showInterviewDialog(holder.getAbsoluteAdapterPosition());

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return courseListArrayList.size();
    }

    public static class MyViewHolder  extends  RecyclerView.ViewHolder{

        TextView courseItemTextview;
        ImageView favImageView,readImageview;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            readImageview = itemView.findViewById(R.id.readImageview);
            courseItemTextview = itemView.findViewById(R.id.courseItemTextview);
            favImageView = itemView.findViewById(R.id.favImageView);

        }
    }
    public void checkForFavorites()
    {

    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            List<String> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0|| constraint.toString().trim().isEmpty()) {
//                filterResults.count = allProductArrayList.size();
//                filterResults.values = allProductArrayList;
                filteredList.addAll(courseListArrayListFull);

            } else {

                String filterPattern = constraint.toString().toLowerCase().trim();
                for (String item : courseListArrayListFull) {
                    // match the pattern if searched for the product name
                    if (item.toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(item);
                    }
                }
            }

            filterResults.values = filteredList;
            filterResults.count = filteredList.size();
            return filterResults;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            courseListArrayList = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }

    };
    public void clearCompleteCourseArrayList()
    {
        completedCourseArrayList.clear();
        notifyDataSetChanged();
    }

    public void clearFavCourseArrayList()
    {
        favCourseArrayList.clear();
        notifyDataSetChanged();
    }
}
