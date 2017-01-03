package com.toolbox.kostovtd;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
//                    scaleUpAndTranslateView(mainContainer);
//                    fadeOutAndTranslateYView(headerContainer, 200, false);
//                    fadeOutAndTranslateYView(textContacts, 200, false);
//                    fadeOutAndTranslateYView(textPartners, 200, false);
//                    fadeOutAndTranslateYView(textSettings, 200, false);
                    isResized = false;
                } else {
                    // scale down and translate main view
                    AnimatorSet animatorSet = new AnimatorSet();
                    List<Animator> scaleDownAndTranslateAnimatorList = new ArrayList<>();
                    scaleDownAndTranslateAnimatorList.addAll(scaleXYAnimation(mainContainer, 1f, 0.8f, 500, 0));
                    scaleDownAndTranslateAnimatorList.add(translateXAnimation(mainContainer, 600, 500, 0));
                    animatorSet.playTogether(scaleDownAndTranslateAnimatorList);
                    animatorSet.start();


                    // fade in and translate side menu
                    AnimatorSet animatorSetSideMenu = new AnimatorSet();
                    List<Animator> animatorList = new ArrayList<>();
//                    animatorList.add(fadeAnimation(textContacts, 0, 1, 800, 200));
//                    animatorList.add(translateYAnimation(textContacts, -200, 800, 0));
//                    animatorList.add(fadeAnimation(textPartners, 0, 1, 800, 200));
//                    animatorList.add(translateYAnimation(textPartners, -200, 800, 0));
//                    animatorList.add(fadeAnimation(textSettings, 0, 1, 800, 200));
//                    animatorList.add(translateYAnimation(textSettings, -200, 800, 0));

                    animatorSetSideMenu.play(fadeAnimation(textContacts, 0, 1, 800, 0)).after(500)
                            .with(translateYAnimation(textContacts, -200, 800, 0)).after(500)
                            .with(fadeAnimation(textPartners, 0, 1, 800, 0)).after(500)
                            .with(translateYAnimation(textPartners, -200, 800, 0)).after(500)
                            .with(fadeAnimation(textSettings, 0, 1, 800, 0)).after(500)
                            .with(translateYAnimation(textSettings, -200, 800, 0)).after(500);

                    animatorSetSideMenu.start();
                    isResized = true;
                }
            }
        });
        setSupportActionBar(toolbar);
    }


    private List<Animator> scaleXYAnimation(View view, float fromValue, float toValue, long animationDuration,
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


    private Animator scaleXAnimation(View view, float fromValue, float toValue, long animationDuration,
                                     long startDelay) {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, fromValue, toValue);
        scaleXAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        return scaleXAnimator;
    }


    private Animator scaleYAnimation(View view, float fromValue, float toValue, long animationDuration,
                                     long startDelay) {
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, fromValue, toValue);
        scaleYAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        return scaleYAnimator;
    }
    
    
    private List<Animator> translateXYAnimation(View view, float withValue, long animationDuration,
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
    
    
    private Animator translateXAnimation(View view, float withValue, long animationDuration,
                                         long startDelay) {
        ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, withValue);
        translateXAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        return translateXAnimator;
    }

    
    private Animator translateYAnimation(View view, float withValue, long animationDuration,
                                         long startDelay) {
        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, withValue);
        translateYAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        return translateYAnimator;
    }


    private Animator fadeAnimation(View view, float fromValue, float toValue, long animationDuration,
                                   long startDelay) {
        ObjectAnimator fadeAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, fromValue, toValue);
        fadeAnimator.setDuration(animationDuration).setStartDelay(startDelay);

        return fadeAnimator;
    }
}