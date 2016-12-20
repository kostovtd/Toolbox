package com.toolbox.kostovtd;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

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
                    fadeOutAndTranslateYView(headerContainer, 1700, false);
                    isResized = false;
                } else {
                    scaleDownAndTranslateView(mainContainer);
                    fadeInAndTranslateYView(headerContainer, -1700, false);
                    isResized = true;
                }
            }
        });
        setSupportActionBar(toolbar);
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


    private void fadeInAndTranslateYView(View view, float translationY, boolean hasDelay) {
        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1);
        fadeInAnimator.setDuration(1500);
        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, translationY);
        translateYAnimator.setDuration(1500);

        AnimatorSet animatorSet = new AnimatorSet();

        if(hasDelay) {
            animatorSet.play(fadeInAnimator).after(100)
                    .with(translateYAnimator);
        } else {
            animatorSet.play(fadeInAnimator)
                    .with(translateYAnimator);
        }

        animatorSet.start();
    }


    private void fadeOutAndTranslateYView(View view, float translationY, boolean hasDelay) {
        ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1, 0);
        fadeOutAnimator.setDuration(1500);
        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, translationY);
        translateYAnimator.setDuration(1500);

        AnimatorSet animatorSet = new AnimatorSet();

        if(hasDelay) {
            animatorSet.play(fadeOutAnimator).after(100)
                    .with(translateYAnimator);
        } else {
            animatorSet.play(fadeOutAnimator)
                    .with(translateYAnimator);
        }

        animatorSet.start();
    }
}