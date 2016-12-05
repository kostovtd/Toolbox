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

public class FabOptionsButtonContainer extends RelativeLayout {

    int previousButtonId;

    public FabOptionsButtonContainer(Context context) {
        this(context, null);
    }

    public FabOptionsButtonContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FabOptionsButtonContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        if(previousButtonId != 0) {
            params.addRule(RelativeLayout.RIGHT_OF, previousButtonId);
        } else {
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
        }

        fabOptionsButton.setLayoutParams(params);

        if(index == null) {
            addView(fabOptionsButton);
        } else {
            addView(fabOptionsButton, index);
        }

        previousButtonId = buttonId;
        return fabOptionsButton;
    }
}
