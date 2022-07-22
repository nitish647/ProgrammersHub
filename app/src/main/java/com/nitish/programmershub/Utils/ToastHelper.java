package com.nitish.programmershub.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.nitish.programmershub.Activity.MainActivity;
import com.nitish.programmershub.R;

public class ToastHelper {

    public static void showCustomToast(Context context, String message , String backgroundColor)
    {
        Activity activity = (Activity)context;
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_layout,
                (ViewGroup) activity.findViewById(R.id.rootLinearLayout));



        TextView toastTextView = (TextView) layout.findViewById(R.id.toastTextView);
        LinearLayout rootLinearLayout =  layout.findViewById(R.id.rootLinearLayout);

        toastTextView.setText(message);
        int[] colors = new int[] {
                Color.parseColor(backgroundColor),
                Color.parseColor(backgroundColor),
                Color.parseColor(backgroundColor),
                Color.parseColor(backgroundColor)

        };
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed}  // pressed
        };
        ColorStateList myList = new ColorStateList(states, colors);
        rootLinearLayout.setBackgroundTintList(myList);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 45, 150);

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
        // setting custom toast lenght
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              toast.cancel();
            }
        },800);

    }
}
