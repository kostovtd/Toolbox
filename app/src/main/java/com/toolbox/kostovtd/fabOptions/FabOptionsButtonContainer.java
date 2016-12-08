package com.toolbox.kostovtd.fabOptions;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.toolbox.kostovtd.R;

/**
 * Created by todor.kostov on 2.12.2016 г..
 */

public class FabOptionsButtonContainer extends LinearLayout {

    private static final int OPTION_BUTTON_MARGIN = 35;  // holds the margin which needs to be included between
                                                    // each option button in the left and in the right section

    private int numberOfOptionButtons;
    private int currentButtonIndex;
    private boolean isMarginSet = false;
    private int sectionMargin = 0; // holds the margin which needs to be included between the
                                    // left and the right sections of the FabOptionsButton

    public FabOptionsButtonContainer(Context context) {
        this(context, null);
    }

    public FabOptionsButtonContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FabOptionsButtonContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getNumberOfOptionButtons() {
        return numberOfOptionButtons;
    }

    public void setNumberOfOptionButtons(int numberOfOptionButtons) {
        this.numberOfOptionButtons = numberOfOptionButtons;
    }

    public AppCompatImageView addButton(Context context, int buttonId, CharSequence title, Drawable drawableIcon) {
        return addButton(context, buttonId, title, drawableIcon, null);
    }


    public AppCompatImageView addButton(Context context, int buttonId, CharSequence title, Drawable drawableIcon, Integer index) {
        AppCompatImageView fabOptionsButton =
                (AppCompatImageView) LayoutInflater.from(context).inflate(R.layout.fab_options_button, this, false);
        fabOptionsButton.setImageDrawable(drawableIcon);
        fabOptionsButton.setContentDescription(title);
        fabOptionsButton.setId(buttonId);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        currentButtonIndex++;

        if(!isMarginSet && currentButtonIndex > numberOfOptionButtons / 2) {
            params.setMargins(sectionMargin, 0, 0, 0);
            isMarginSet = true;
        } else if(getChildCount() > 0){
            params.setMargins(OPTION_BUTTON_MARGIN, 0, 0, 0);
        }

        fabOptionsButton.setLayoutParams(params);

        if(index == null) {
            addView(fabOptionsButton);
        } else {
            addView(fabOptionsButton, index);
        }

        return fabOptionsButton;
    }

    public void setSectionMargin(int sectionMargin) {
        // add some additional margin, because otherwise some option buttons
        // will be too close to the fab button
        boolean isEvenNumberOfButtons = numberOfOptionButtons % 2 == 0;
        // need different margins for odd and even number of menu element,
        // otherwise UI bugs appear
        if(isEvenNumberOfButtons) {
            this.sectionMargin = sectionMargin + (OPTION_BUTTON_MARGIN * 2);
        } else {
            this.sectionMargin = sectionMargin + (OPTION_BUTTON_MARGIN * 5);
        }

    }
}
