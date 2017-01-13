package com.toolbox.kostovtd.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by todor.kostov on 4.1.2017 г..
 */

public class AnimationsUtil {

    // VISIBILITY CONSTANTS
    public static final float FULLY_VISIBLE = 1f;
    public static final float FULLY_TRANSPARENT = 0f;

    // ANIMATION DURATION CONSTANTS
    public static final long ANIMATION_DURATION_500_MS = 500;
    public static final long ANIMATION_DURATION_400_MS = 400;
    public static final long ANIMATION_DURATION_350_MS = 350;
    public static final long ANIMATION_DURATION_300_MS = 300;
    public static final long ANIMATION_DURATION_250_MS = 250;
    public static final long ANIMATION_DURATION_200_MS = 200;
    public static final long ANIMATION_DURATION_150_MS = 150;


    public static List<Animator> scaleXYAnimation(View view, float fromValue, float toValue, long animationDuration) {
        List<Animator> animatorList = new ArrayList<>();

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, fromValue, toValue);
        scaleXAnimator.setDuration(animationDuration);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, fromValue, toValue);
        scaleYAnimator.setDuration(animationDuration);

        animatorList.add(scaleXAnimator);
        animatorList.add(scaleYAnimator);

        return animatorList;
    }


    public static Animator scaleXAnimation(View view, float fromValue, float toValue, long animationDuration) {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, fromValue, toValue);
        scaleXAnimator.setDuration(animationDuration);

        return scaleXAnimator;
    }


    public static Animator scaleYAnimation(View view, float fromValue, float toValue, long animationDuration) {
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, fromValue, toValue);
        scaleYAnimator.setDuration(animationDuration);

        return scaleYAnimator;
    }


    public static List<Animator> translateXYAnimation(View view, float withValue, long animationDuration) {
        List<Animator> animatorList = new ArrayList<>();

        ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, withValue);
        translateXAnimator.setDuration(animationDuration);

        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, withValue);
        translateYAnimator.setDuration(animationDuration);

        animatorList.add(translateXAnimator);
        animatorList.add(translateYAnimator);

        return animatorList;
    }


    public static Animator translateXAnimation(View view, float withValue, long animationDuration) {
        ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, withValue);
        translateXAnimator.setDuration(animationDuration);

        return translateXAnimator;
    }


    public static Animator translateYAnimation(View view, float withValue, long animationDuration) {
        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, withValue);
        translateYAnimator.setDuration(animationDuration);

        return translateYAnimator;
    }


    public static Animator fadeAnimation(View view, float fromValue, float toValue, long animationDuration) {
        ObjectAnimator fadeAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, fromValue, toValue);
        fadeAnimator.setDuration(animationDuration);

        return fadeAnimator;
    }


    public static float convertPxToDip(Context context, int pxValue) {
        Resources resources = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxValue, resources.getDisplayMetrics());
    }
}
