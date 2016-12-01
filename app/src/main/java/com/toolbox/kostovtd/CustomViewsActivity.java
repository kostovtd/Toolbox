package com.toolbox.kostovtd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by todor.kostov on 1.12.2016 г..
 */
public class CustomViewsActivity extends AppCompatActivity {

    private static final String TAG = CustomViewsActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_views);
        setTitle(R.string.custom_views_screen_title);
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