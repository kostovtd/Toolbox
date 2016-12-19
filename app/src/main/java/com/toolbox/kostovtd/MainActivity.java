package com.toolbox.kostovtd;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.MenuItem;
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

    @BindView(R.id.button_fade_in)
    Button bFadeIn;

    @BindView(R.id.button_fade_out)
    Button bFadeOut;

    @BindView(R.id.view_test)
    View viewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        ButterKnife.bind(this);

        setUpToolbar();

        bFadeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fadeInView(viewTest);
            }
        });

        bFadeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fadeOutView(viewTest);
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
                    isResized = false;
                } else {
                    scaleDownAndTranslateView(mainContainer);
                    isResized = true;
                }
            }
        });
        setSupportActionBar(toolbar);
    }


    //TODO NEED REFACTORING (TRY TO SEPARATE INTO SMALLER FUNCTIONS)
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

    //TODO NEED REFACTORING (TRY TO SEPARATE INTO SMALLER FUNCTIONS)
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


    private void fadeInView(View view) {
        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1);
        fadeInAnimator.setDuration(500);
        fadeInAnimator.start();
    }


    private void fadeOutView(View view) {
        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1, 0);
        fadeInAnimator.setDuration(500);
        fadeInAnimator.start();
    }
}