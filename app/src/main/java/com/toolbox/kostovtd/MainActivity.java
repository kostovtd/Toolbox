package com.toolbox.kostovtd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by todor.kostov on 1.12.2016 г..
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.button_custom_views)
    Button bCustomViews;

    @BindView(R.id.button_transitions)
    Button bTransitions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_screen_title);

        ButterKnife.bind(this);


        // CUSTOM VIEWS
        bCustomViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navigate to Custom Views Screen
                Intent customViewsIntent = new Intent(MainActivity.this, CustomViewsActivity.class);
                startActivity(customViewsIntent);
            }
        });


        // TRANSITIONS
        bTransitions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navigate to Transitions Screen
                Intent transitionIntent = new Intent(MainActivity.this, TransitionsActivity.class);
                startActivity(transitionIntent);
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