package com.toolbox.kostovtd.fabOptions;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.annotation.MenuRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.AppCompatImageView;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.toolbox.kostovtd.R;

/**
 * Created by todor.kostov on 2.12.2016 г..
 */

public class FabOptions extends FrameLayout implements View.OnClickListener, Transition.TransitionListener {

    private static final String TAG = FabOptions.class.getSimpleName();
    private static final int NO_DIMENSION = 0;
    private static final long CLOSE_MORPH_TRANSFORM_DURATION = 70;

    private boolean mIsOpen;
    private boolean mIsTransitionRunning = false;
    private OnClickListener mListener;
    private Menu menu;
    private FloatingActionButton mFloatingActionButton;

    private View mBackground;
    private FabOptionsButtonContainer mButtonContainer;


    public FabOptions(Context context) {
        this(context, null, 0);
    }

    public FabOptions(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FabOptions(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
        if (attrs != null) {
            inflateButtonsFromAttrs(context, attrs);
        }
    }


    private void initViews(Context context) {
        inflate(context, R.layout.fab_options_layout, this);
        mIsOpen = false;
        mBackground = findViewById(R.id.background);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_options_fab);
        mFloatingActionButton.setOnClickListener(this);
        mFloatingActionButton.setVisibility(GONE);
        mButtonContainer = (FabOptionsButtonContainer) findViewById(R.id.button_container);
    }


    private void inflateButtonsFromAttrs(Context context, AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FabOptions, 0, 0);
        if(attributes.hasValue(R.styleable.FabOptions_button_menu)) {
            setButtonsMenu(context, attributes.getResourceId(R.styleable.FabOptions_button_menu, 0));
        }
    }


    public void setButtonsMenu(Context context, @MenuRes int menuId) {
        menu = new MenuBuilder(context);
        SupportMenuInflater menuInflater = new SupportMenuInflater(context);
        menuInflater.inflate(menuId, menu);

        addButtonsFromMenu(context, menu);
//        close();
        open();
    }


    private void addButtonsFromMenu(Context context, Menu menu) {
        for(int i=0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            addButton(context, menuItem);
        }
    }



    private void addButton(Context context, MenuItem menuItem) {
        AppCompatImageView button = mButtonContainer.addButton(context, menuItem.getItemId(),
                menuItem.getTitle(), menuItem.getIcon());
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        // if there is a running transition
        // then do nothing
        if(mIsTransitionRunning) {
            return;
        }

        switch (v.getId()) {
            case R.id.fab_options_fab:
                if(mIsOpen) {
                    close();
                } else {
                    open();
                }
                break;
            default:
                if(mListener != null) {
                    mListener.onClick(v);
                    close();
                }
        }
    }


    public void setOnClickListener(OnClickListener mListener) {
        this.mListener = mListener;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(mIsOpen) {
            ViewGroup.LayoutParams backgroundLayoutParams = mBackground.getLayoutParams();
            backgroundLayoutParams.width = mButtonContainer.getMeasuredWidth() + mFloatingActionButton.getMeasuredWidth();
            backgroundLayoutParams.height = mButtonContainer.getMeasuredHeight();
            mBackground.setLayoutParams(backgroundLayoutParams);
        }
    }


    private void open() {
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.fab_options_ic_overflow_animatable, null);
        mFloatingActionButton.setImageDrawable(drawable);
        drawable.start();
        TransitionManager.beginDelayedTransition(this, new OpenMorphTransition(mButtonContainer).addListener(this));
        animateButtons(true);
        animateBackground(true);
        mIsOpen = true;
    }


    private void close() {
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.fab_options_ic_close_animatable, null);
        mFloatingActionButton.setImageDrawable(drawable);
        drawable.start();
        TransitionManager.beginDelayedTransition(this, new CloseMorphTransition(mButtonContainer).addListener(this));
        animateButtons(false);
        animateBackground(false);
        mIsOpen = false;
    }


    @Override
    public void onTransitionStart(Transition transition) {
        mIsTransitionRunning = true;
    }

    @Override
    public void onTransitionEnd(Transition transition) {
        mIsTransitionRunning = false;
    }

    @Override
    public void onTransitionCancel(Transition transition) {
        mIsTransitionRunning = false;
    }

    @Override
    public void onTransitionPause(Transition transition) {
        mIsTransitionRunning = false;
    }

    @Override
    public void onTransitionResume(Transition transition) {
        mIsTransitionRunning = true;
    }

    private void animateButtons(boolean isOpen) {
        for(int i=0; i < mButtonContainer.getChildCount(); i++) {
            mButtonContainer.getChildAt(i).setScaleX(isOpen ? 1 : 0);
            mButtonContainer.getChildAt(i).setScaleY(isOpen ? 1 : 0);
        }
    }


    private void animateBackground(boolean isOpen) {
        ViewGroup.LayoutParams backgroundLayoutParams = mBackground.getLayoutParams();
        backgroundLayoutParams.width = isOpen ? mButtonContainer.getMeasuredWidth() : NO_DIMENSION;
        mBackground.setLayoutParams(backgroundLayoutParams);
    }


    public boolean isOpen() {
        return mIsOpen;
    }


    private static class OpenMorphTransition extends TransitionSet {
        OpenMorphTransition(ViewGroup viewGroup) {
            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.excludeChildren(R.id.button_container, true);

            ChangeTransform changeTransform = new ChangeTransform();
            for (int i = 0; i < viewGroup.getChildCount(); i++){
                changeTransform.addTarget(viewGroup.getChildAt(i));
            }
            addTransition(changeBounds);
            addTransition(changeTransform);
            setOrdering(TransitionSet.ORDERING_SEQUENTIAL);
        }
    }

    private static class CloseMorphTransition extends TransitionSet {
        CloseMorphTransition(ViewGroup viewGroup) {
            ChangeBounds changeBound = new ChangeBounds();
            changeBound.excludeChildren(R.id.button_container, true);

            ChangeTransform changeTransform = new ChangeTransform();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                changeTransform.addTarget(viewGroup.getChildAt(i));
            }

            addTransition(changeTransform);
            addTransition(changeBound);
            setOrdering(TransitionSet.ORDERING_SEQUENTIAL);
        }
    }
}
