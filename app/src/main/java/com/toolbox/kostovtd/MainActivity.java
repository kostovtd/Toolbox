package com.toolbox.kostovtd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @BindView(R.id.root_container)
    LinearLayout root_container;

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


        bTestScaleXY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TransitionManager.beginDelayedTransition(root_container, new CloseMorphTransition(mainContainer));

                if(isResized) {
                    AnimationSet animationSet = new AnimationSet(true);
                    animationSet.setFillAfter(true);
                    animationSet.setDuration(700);

                    ScaleAnimation scaleDownAnimation =  new ScaleAnimation(0.7f, 1f, 0.7f, 1f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
                    animationSet.addAnimation(scaleDownAnimation);

                    TranslateAnimation rightTranslationAnimation = new TranslateAnimation(600, 0, 0, 0);
                    animationSet.addAnimation(rightTranslationAnimation);

                    mainContainer.startAnimation(animationSet);
                    isResized = false;
                } else {
                    AnimationSet animationSet = new AnimationSet(true);
                    animationSet.setFillAfter(true);
                    animationSet.setDuration(700);

                    ScaleAnimation scaleDownAnimation =  new ScaleAnimation(1f, 0.7f, 1f, 0.7f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
                    animationSet.addAnimation(scaleDownAnimation);

                    TranslateAnimation rightTranslationAnimation = new TranslateAnimation(0, 600, 0, 0);
                    animationSet.addAnimation(rightTranslationAnimation);

                    mainContainer.startAnimation(animationSet);
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

    private static class CloseMorphTransition extends TransitionSet {
        CloseMorphTransition(ViewGroup viewGroup) {
            ChangeBounds changeBounds = new ChangeBounds();

            ChangeTransform changeTransform = new ChangeTransform();
            for (int i = 0; i < viewGroup.getChildCount(); i++){
                changeTransform.addTarget(viewGroup.getChildAt(i));
            }

            addTransition(changeTransform);
            addTransition(changeBounds);
            setOrdering(TransitionSet.ORDERING_TOGETHER);
        }
    }
}