package com.toolbox.kostovtd.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by todor.kostov on 4.1.2017 г..
 */

public class AnimationsUtil {

    public static List<Animator> scaleXYAnimation(View view, float fromValue, float toValue, long animationDuration,
                                            long startDelay) {
        List<Animator> animatorList = new ArrayList<>();

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, fromValue, toValue);
        scaleXAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, fromValue, toValue);
        scaleYAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        animatorList.add(scaleXAnimator);
        animatorList.add(scaleYAnimator);

        return animatorList;
    }


    public static Animator scaleXAnimation(View view, float fromValue, float toValue, long animationDuration,
                                     long startDelay) {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, fromValue, toValue);
        scaleXAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        return scaleXAnimator;
    }


    public static Animator scaleYAnimation(View view, float fromValue, float toValue, long animationDuration,
                                     long startDelay) {
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, fromValue, toValue);
        scaleYAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        return scaleYAnimator;
    }


    public static List<Animator> translateXYAnimation(View view, float withValue, long animationDuration,
                                                long startDelay) {
        List<Animator> animatorList = new ArrayList<>();

        ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, withValue);
        translateXAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, withValue);
        translateYAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        animatorList.add(translateXAnimator);
        animatorList.add(translateYAnimator);

        return animatorList;
    }


    public static Animator translateXAnimation(View view, float withValue, long animationDuration,
                                         long startDelay) {
        ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, withValue);
        translateXAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        return translateXAnimator;
    }


    public static Animator translateYAnimation(View view, float withValue, long animationDuration,
                                         long startDelay) {
        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, withValue);
        translateYAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        return translateYAnimator;
    }


    public static Animator fadeAnimation(View view, float fromValue, float toValue, long animationDuration,
                                   long startDelay) {
        ObjectAnimator fadeAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, fromValue, toValue);
        fadeAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        return fadeAnimator;
    }

}
