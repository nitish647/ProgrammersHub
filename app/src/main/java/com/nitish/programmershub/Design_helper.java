package com.nitish.programmershub;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Toast;

public class Design_helper {

    int[] colors = {Color.parseColor("#008000"),Color.parseColor("#ADFF2F")};

    //create a new gradient color
  public static   GradientDrawable gd;

  public static GradientDrawable set_Colors(String color1, String color2, Float radius, GradientDrawable.Orientation orientation)
  {
      int[] colors = {Color.parseColor(color1),Color.parseColor(color2)};
       gd = new  GradientDrawable(
             orientation, colors);
       gd.setCornerRadius(radius);
       return gd;
  }
  public static void show_toast(Context context,String message)
  {
      Toast.makeText(context,message,Toast.LENGTH_LONG).show();
  }

}
