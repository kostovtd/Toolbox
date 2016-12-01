package com.toolbox.kostovtd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.toolbox.kostovtd.fabOptions.FabOptionsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by todor.kostov on 1.12.2016 г..
 */
public class CustomViewsActivity extends AppCompatActivity {

    private static final String TAG = CustomViewsActivity.class.getSimpleName();

    @BindView(R.id.button_fab_option)
    Button bFabOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_views);
        setTitle(R.string.custom_views_screen_title);

        ButterKnife.bind(this);

        // FAB OPTIONS
        bFabOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navigate to the fab options example
                Intent fabOptionsIntent = new Intent(CustomViewsActivity.this, FabOptionsActivity.class);
                startActivity(fabOptionsIntent);
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