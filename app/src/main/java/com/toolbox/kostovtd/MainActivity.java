package com.toolbox.kostovtd;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
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

    @BindView(R.id.button_test)
    Button bTestScaleXY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setUpToolbar();

        bTestScaleXY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isResized) {
                    scaleUpAndTranslateView(mainContainer);
                    isResized = false;
                } else {
                    scaleDownAndTranslateView(mainContainer);
                    isResized = true;
                }

            }
        });

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
        setSupportActionBar(toolbar);
    }

    private void scaleDownAndTranslateView(View view) {
        ObjectAnimator scaleDownXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.8f);
        scaleDownXAnimator.setDuration(500);
        ObjectAnimator scaleDownYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.8f);
        scaleDownYAnimator.setDuration(500);
        ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(view, "translationX", 600);
        translateXAnimator.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleDownXAnimator)
                .with(scaleDownYAnimator)
                .with(translateXAnimator);
        animatorSet.start();
    }

    private void scaleUpAndTranslateView(View view) {
        ObjectAnimator scaleDownXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1f);
        scaleDownXAnimator.setDuration(500);
        ObjectAnimator scaleDownYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1f);
        scaleDownYAnimator.setDuration(500);
        ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(view, "translationX", 0);
        translateXAnimator.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleDownXAnimator)
                .with(scaleDownYAnimator)
                .with(translateXAnimator);
        animatorSet.start();
    }
}