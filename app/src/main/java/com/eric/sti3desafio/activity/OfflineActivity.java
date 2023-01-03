package com.eric.sti3desafio.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.eric.sti3desafio.R;
import com.google.android.material.snackbar.Snackbar;

public class OfflineActivity extends AppCompatActivity{

    private Button btnRefresh, btnWifi;
    private boolean doubleBackPressed = false;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        relativeLayout = findViewById(R.id.relativeLayout);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnWifi = findViewById(R.id.btnWifi);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else {
                    Snackbar snackbar = Snackbar.make(relativeLayout, R.string.message_offline, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        btnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (doubleBackPressed){
            super.onBackPressed();
            this.finishAffinity();
        }
        this.doubleBackPressed = true;
        Snackbar snackbar = Snackbar.make(relativeLayout, R.string.message_backpressed, Snackbar.LENGTH_LONG);
        snackbar.show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackPressed = false;
            }
        },2000);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

}