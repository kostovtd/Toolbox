package com.toolbox.kostovtd;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by todor.kostov on 1.12.2016 г..
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private boolean isResized = false;

    @BindView(R.id.nav_bar)
    Toolbar toolbar;

    @BindView(R.id.main_container)
    LinearLayout mainContainer;

    @BindView(R.id.header_container)
    LinearLayout headerContainer;

    @BindView(R.id.text_contacts)
    TextView textContacts;

    @BindView(R.id.text_partners)
    TextView textPartners;

    @BindView(R.id.text_settings)
    TextView textSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        ButterKnife.bind(this);

        setUpToolbar();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: hit");
        super.onResume();
    }


    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: hit");
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: hit");
        super.onDestroy();
    }

    /**
     * Set up the {@link Toolbar) the current screen
     */
    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.nav_bar);

        // set to be the same as the hamburger menu used for navigation drawer
        toolbar.setLogo(R.drawable.ic_menu_white);

        // need to set a click listener for the logo
        // in this particular case, the logo view is the
        // first (0) child of the Toolbar object
        toolbar.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // handle the scaling and translating
                // animations logic
                if(isResized) {
                    scaleUpAndTranslateView(mainContainer);
                    fadeOutAndTranslateYView(headerContainer, 200, false);
                    fadeOutAndTranslateYView(textContacts, 200, false);
                    fadeOutAndTranslateYView(textPartners, 200, false);
                    fadeOutAndTranslateYView(textSettings, 200, false);
                    isResized = false;
                } else {
//                    scaleDownAndTranslateView(mainContainer);
//                    fadeInAndTranslateYView(headerContainer, -200, false).start();
//
//                    AnimatorSet animatorSet = new AnimatorSet();
//                    List<Animator> animatorList = new ArrayList<>();
//                    animatorList.add(fadeInAndTranslateYView(textContacts, -200, false));
//                    animatorList.add(fadeInAndTranslateYView(textPartners, -200, false));
//                    animatorList.add(fadeInAndTranslateYView(textSettings, -200, false));
//                    animatorSet.playSequentially(animatorList);
//                    animatorSet.start();
                    isResized = true;
                }
            }
        });
        setSupportActionBar(toolbar);
    }



//    private ObjectAnimator constructAnimator(View view, Property<View, Float> property,
//                                             float withValue, long animationDuration) {
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, property, withValue);
//        objectAnimator.setDuration(animationDuration);
//        return objectAnimator;
//    }
//
//
//    private ObjectAnimator constructAnimator(View view, Property<View, Float> property,
//                                             float fromValue, float toValue, long animationDuration) {
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, property, fromValue, toValue);
//        objectAnimator.setDuration(animationDuration);
//        return objectAnimator;
//    }

    

    private List<Animator> scaleXYAnimation(View view, float fromValue, float toValue, long animationDuration) {
        List<Animator> animatorList = new ArrayList<>();

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, fromValue, toValue);
        scaleXAnimator.setDuration(animationDuration);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, fromValue, toValue);
        scaleYAnimator.setDuration(animationDuration);

        animatorList.add(scaleXAnimator);
        animatorList.add(scaleYAnimator);

        return animatorList;
    }


    //TODO NEED REFACTORING (TRY TO SEPARATE INTO SMALLER FUNCTIONS)
    private void scaleDownAndTranslateView(View view) {
        ObjectAnimator scaleDownXAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.8f);
        scaleDownXAnimator.setDuration(500);
        ObjectAnimator scaleDownYAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.8f);
        scaleDownYAnimator.setDuration(500);
        ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 600);
        translateXAnimator.setDuration(700);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleDownXAnimator)
                .with(scaleDownYAnimator)
                .with(translateXAnimator);
        animatorSet.start();
    }

    //TODO NEED REFACTORING (TRY TO SEPARATE INTO SMALLER FUNCTIONS)
    private void scaleUpAndTranslateView(View view) {
        ObjectAnimator scaleDownXAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.8f, 1f);
        scaleDownXAnimator.setDuration(500);
        ObjectAnimator scaleDownYAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.8f, 1f);
        scaleDownYAnimator.setDuration(500);
        ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 0);
        translateXAnimator.setDuration(700);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleDownXAnimator)
                .with(scaleDownYAnimator)
                .with(translateXAnimator);
        animatorSet.start();
    }


    private AnimatorSet fadeInAndTranslateYView(View view, float translationY, boolean hasDelay) {
        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1);
        fadeInAnimator.setDuration(800);
        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, translationY);
        translateYAnimator.setDuration(800);

        AnimatorSet animatorSet = new AnimatorSet();

        if(hasDelay) {
            animatorSet.play(fadeInAnimator).after(200)
                    .with(translateYAnimator);
        } else {
            animatorSet.play(fadeInAnimator)
                    .with(translateYAnimator);
        }

        return animatorSet;
    }


    private AnimatorSet fadeOutAndTranslateYView(View view, float translationY, boolean hasDelay) {
        ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1, 0);
        fadeOutAnimator.setDuration(800);
        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, translationY);
        translateYAnimator.setDuration(800);

        AnimatorSet animatorSet = new AnimatorSet();

        if(hasDelay) {
            animatorSet.play(fadeOutAnimator).after(200)
                    .with(translateYAnimator);
        } else {
            animatorSet.play(fadeOutAnimator)
                    .with(translateYAnimator);
        }

        return animatorSet;
    }
}