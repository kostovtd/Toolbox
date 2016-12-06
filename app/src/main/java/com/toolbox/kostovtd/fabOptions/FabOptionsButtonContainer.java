package com.toolbox.kostovtd.fabOptions;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.toolbox.kostovtd.R;

/**
 * Created by todor.kostov on 2.12.2016 г..
 */

public class FabOptionsButtonContainer extends LinearLayout {

    private int numberOfOptionButtons;
    private int currentButtonIndex;
    private boolean isMarginSet = false;
    private int margin = 0;

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
            params.setMargins(300, 0, 0, 0);
            isMarginSet = true;
        }

        fabOptionsButton.setLayoutParams(params);

        if(index == null) {
            addView(fabOptionsButton);
        } else {
            addView(fabOptionsButton, index);
        }

        return fabOptionsButton;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }
}
