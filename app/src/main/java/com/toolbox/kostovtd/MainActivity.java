package com.toolbox.kostovtd;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.toolbox.kostovtd.util.AnimationsUtil;

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
    RelativeLayout mainContainer;

    @BindView(R.id.header_container)
    LinearLayout headerContainer;

    @BindView(R.id.text_contacts)
    TextView textContacts;

    @BindView(R.id.text_partners)
    TextView textPartners;

    @BindView(R.id.text_settings)
    TextView textSettings;

    @BindView(R.id.text_erase_database)
    TextView textEraseDatabase;

    @BindView(R.id.text_erase_tags)
    TextView textEraseTags;

    @BindView(R.id.text_log_out)
    TextView textLogOut;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        ButterKnife.bind(this);

        setUpRecyclerViewAndAdapter();

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


    private void setUpRecyclerViewAndAdapter() {
        List<Integer> data = new ArrayList<>();
        data.add(R.drawable.ic_account_balance_white);
        data.add(R.drawable.ic_backup_white);
        data.add(R.drawable.ic_build_white);
        data.add(R.drawable.ic_fingerprint_white);
        data.add(R.drawable.ic_menu_white);
        data.add(R.drawable.ic_account_balance_white);
        data.add(R.drawable.ic_backup_white);
        data.add(R.drawable.ic_build_white);
        data.add(R.drawable.ic_fingerprint_white);
        data.add(R.drawable.ic_menu_white);
        data.add(R.drawable.ic_account_balance_white);
        data.add(R.drawable.ic_backup_white);
        data.add(R.drawable.ic_build_white);
        data.add(R.drawable.ic_fingerprint_white);
        data.add(R.drawable.ic_menu_white);
        CustomAdapter adapter = new CustomAdapter(MainActivity.this, data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
                    fadeOutAndTranslateHeaderContainer();
                    scaleUpAndTranslateMainContainer();
                    fadeOutAndTranslateSideMenu();
                    isResized = false;
                } else {
                    fadeInAndTranslateHeaderContainer();
                    scaleDownAndTranslateMainContainer();
                    fadeInAndTranslateSideMenu();
                    isResized = true;
                }
            }
        });
        setSupportActionBar(toolbar);
    }


    private void scaleDownAndTranslateMainContainer() {
        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> scaleDownAndTranslateAnimatorList = AnimationsUtil.scaleXYAnimation(mainContainer, 1f, 0.8f, 500, 0);
        Animator translateXAnimator =  AnimationsUtil.translateXAnimation(mainContainer, 600, 500, 0);
        scaleDownAndTranslateAnimatorList.add(translateXAnimator);
        animatorSet.playTogether(scaleDownAndTranslateAnimatorList);
        animatorSet.start();
    }


    private void scaleUpAndTranslateMainContainer() {
        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> scaleUpAndTranslateAnimatorList = AnimationsUtil.scaleXYAnimation(mainContainer, 0.8f, 1f, 500, 0);
        Animator translateXAnimator =  AnimationsUtil.translateXAnimation(mainContainer, 0, 500, 0);
        scaleUpAndTranslateAnimatorList.add(translateXAnimator);
        animatorSet.playTogether(scaleUpAndTranslateAnimatorList);
        animatorSet.start();
    }


    private void fadeInAndTranslateSideMenu() {
        AnimatorSet animatorSetContacts = new AnimatorSet();
        Animator fadeInContactsAnimator = AnimationsUtil.fadeAnimation(textContacts, 0, 1, 400, 0);
        Animator translateYContactsAnimator = AnimationsUtil.translateYAnimation(textContacts, -200, 400, 0);
        animatorSetContacts.play(fadeInContactsAnimator)
                .with(translateYContactsAnimator);


        AnimatorSet animatorSetPartners = new AnimatorSet();
        Animator fadeInPartnersAnimator = AnimationsUtil.fadeAnimation(textPartners, 0, 1, 350, 0);
        Animator translateYPartnersAnimator = AnimationsUtil.translateYAnimation(textPartners, -200, 350, 0);
        animatorSetPartners.play(fadeInPartnersAnimator).after(200)
                .with(translateYPartnersAnimator);


        AnimatorSet animatorSetSettings = new AnimatorSet();
        Animator fadeInSettingsAnimator = AnimationsUtil.fadeAnimation(textSettings, 0, 1, 300, 0);
        Animator translateYSettingsAnimator = AnimationsUtil.translateYAnimation(textSettings, -200, 300, 0);
        animatorSetSettings.play(fadeInSettingsAnimator).after(400)
                .with(translateYSettingsAnimator);


        AnimatorSet animatorSetEraseDatabase = new AnimatorSet();
        Animator fadeInDatabaseAnimator = AnimationsUtil.fadeAnimation(textEraseDatabase, 0, 1, 250, 0);
        Animator translateYDatabaseAnimator = AnimationsUtil.translateYAnimation(textEraseDatabase, -100, 250, 0);
        animatorSetEraseDatabase.play(fadeInDatabaseAnimator).after(600)
                .with(translateYDatabaseAnimator);


        AnimatorSet animatorSetEraseTags = new AnimatorSet();
        Animator fadeInTagsAnimator = AnimationsUtil.fadeAnimation(textEraseTags, 0, 1, 200, 0);
        Animator translateYTagsAnimator = AnimationsUtil.translateYAnimation(textEraseTags, -100, 200, 0);
        animatorSetEraseTags.play(fadeInTagsAnimator).after(800)
                .with(translateYTagsAnimator);


        AnimatorSet animatorSetLogOut = new AnimatorSet();
        Animator fadeInLogOutAnimator = AnimationsUtil.fadeAnimation(textLogOut, 0, 1, 150, 0);
        Animator translateYLogOutAnimator = AnimationsUtil.translateYAnimation(textLogOut, -100, 150, 0);
        animatorSetLogOut.play(fadeInLogOutAnimator).after(1000)
                .with(translateYLogOutAnimator);


        animatorSetContacts.start();
        animatorSetPartners.start();
        animatorSetSettings.start();
        animatorSetEraseDatabase.start();
        animatorSetEraseTags.start();
        animatorSetLogOut.start();
    }


    private void fadeOutAndTranslateSideMenu() {
        AnimatorSet animatorSetContacts = new AnimatorSet();
        Animator fadeOutContactsAnimator = AnimationsUtil.fadeAnimation(textContacts, 1, 0, 400, 0);
        Animator translateYContactsAnimator = AnimationsUtil.translateYAnimation(textContacts, 0, 400, 0);
        animatorSetContacts.play(fadeOutContactsAnimator)
                .with(translateYContactsAnimator);


        AnimatorSet animatorSetPartners = new AnimatorSet();
        Animator fadeOutPartnersAnimator = AnimationsUtil.fadeAnimation(textPartners, 1, 0, 400, 0);
        Animator translateYPartnersAnimator = AnimationsUtil.translateYAnimation(textPartners, 0, 400, 0);
        animatorSetPartners.play(fadeOutPartnersAnimator)
                .with(translateYPartnersAnimator);


        AnimatorSet animatorSetSettings = new AnimatorSet();
        Animator fadeOutSettingsAnimator = AnimationsUtil.fadeAnimation(textSettings, 1, 0, 400, 0);
        Animator translateYSettingsAnimator = AnimationsUtil.translateYAnimation(textSettings, 0, 400, 0);
        animatorSetSettings.play(fadeOutSettingsAnimator)
                .with(translateYSettingsAnimator);


        AnimatorSet animatorSetEraseDatabase = new AnimatorSet();
        Animator fadeOutDatabaseAnimator = AnimationsUtil.fadeAnimation(textEraseDatabase, 1, 0, 400, 0);
        Animator translateYDatabaseAnimator = AnimationsUtil.translateYAnimation(textEraseDatabase, 0, 400, 0);
        animatorSetEraseDatabase.play(fadeOutDatabaseAnimator)
                .with(translateYDatabaseAnimator);


        AnimatorSet animatorSetEraseTags = new AnimatorSet();
        Animator fadeOutTagsAnimator = AnimationsUtil.fadeAnimation(textEraseTags, 1, 0, 400, 0);
        Animator translateYTagsAnimator = AnimationsUtil.translateYAnimation(textEraseTags, 0, 400, 0);
        animatorSetEraseTags.play(fadeOutTagsAnimator)
                .with(translateYTagsAnimator);


        AnimatorSet animatorSetLogOut = new AnimatorSet();
        Animator fadeOutLogOutAnimator = AnimationsUtil.fadeAnimation(textLogOut, 1, 0, 400, 0);
        Animator translateYLogOutAnimator = AnimationsUtil.translateYAnimation(textLogOut, 0, 400, 0);
        animatorSetLogOut.play(fadeOutLogOutAnimator)
                .with(translateYLogOutAnimator);


        animatorSetLogOut.start();
        animatorSetEraseTags.start();
        animatorSetEraseDatabase.start();
        animatorSetSettings.start();
        animatorSetPartners.start();
        animatorSetContacts.start();
    }


    private void fadeOutAndTranslateHeaderContainer() {
        AnimatorSet animatorSetHeaderContainer = new AnimatorSet();
        Animator fadeOutHeaderContainerAnimator = AnimationsUtil.fadeAnimation(headerContainer, 1, 0, 400, 0);
        Animator translateYHeaderContainerAnimator = AnimationsUtil.translateYAnimation(headerContainer, 0, 400, 0);
        animatorSetHeaderContainer.play(fadeOutHeaderContainerAnimator)
                .with(translateYHeaderContainerAnimator);

        animatorSetHeaderContainer.start();
    }


    private void fadeInAndTranslateHeaderContainer() {
        AnimatorSet animatorSetHeaderContainer = new AnimatorSet();
        Animator fadeInHeaderContainerAnimator = AnimationsUtil.fadeAnimation(headerContainer, 0, 1, 400, 0);
        Animator translateYHeaderContainerAnimator = AnimationsUtil.translateYAnimation(headerContainer, -200, 400, 0);
        animatorSetHeaderContainer.play(fadeInHeaderContainerAnimator)
                .with(translateYHeaderContainerAnimator);

        animatorSetHeaderContainer.start();
    }
}