package com.example.projectapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.projectapp.R;

import java.util.Objects;

/**
 * Class that contains AppInfo activity stuffs
 * @author Joonas Niemi
 */
public class AppInfoActivity extends AppCompatActivity {
    private static final String TAG = "MyLog";

    /**
     * Happens when activity is created
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
    }

    /**
     * Happens when activity is started
     */
    @Override
    protected void onStart() {
        super.onStart();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Action bar back arrow stuff
     * @return Returns back pressed boolean
     */
    @Override
    public boolean onSupportNavigateUp() {
        Log.i(TAG, "appInfoActivity actionBar back toimii");
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    /**
     * Normal back action stuff
     */
    @Override
    public void onBackPressed() {
        Log.i(TAG, "appInfoActivity back toimii");
        super.onBackPressed();
    }
}
