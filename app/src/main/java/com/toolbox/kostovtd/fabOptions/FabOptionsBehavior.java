package com.toolbox.kostovtd.fabOptions;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by todor.kostov on 2.12.2016 г..
 */

/**
 * Describes all of the different reactions of the FabOptions button to
 * elements shown on the screen.
 */
public class FabOptionsBehavior extends CoordinatorLayout.Behavior {

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        // the fabOption button reaction to a Snackbar
        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
        child.setTranslationY(translationY);
        return true;
    }
}
