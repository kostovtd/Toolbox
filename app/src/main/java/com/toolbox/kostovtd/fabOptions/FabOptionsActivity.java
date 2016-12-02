package com.toolbox.kostovtd.fabOptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.toolbox.kostovtd.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by todor.kostov on 1.12.2016 г..
 */
public class FabOptionsActivity extends AppCompatActivity {

    private static final String TAG = FabOptionsActivity.class.getSimpleName();


    @BindView(R.id.fab_options)
    FabOptions fabOptionsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_options);
        setTitle(R.string.fab_options_screen_title);

        ButterKnife.bind(this);

        fabOptionsButton.setButtonsMenu(this, R.menu.menu_fab_options);
        fabOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}