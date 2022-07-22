package com.nitish.programmershub.Utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.nitish.programmershub.R;

public class AnimationHelper {

    public static Animation topToButtonAnimation(Context context)
    {
        Animation anim = null;
        anim = AnimationUtils.loadAnimation(context, R.anim.anim_top_to_bottom);
        anim.setDuration(1000);
        return anim;
    }
}
