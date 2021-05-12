package com.launchersdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.launcher.test.AppList;
import com.launcher.test.LauncherListener;

public class TestActivity extends AppCompatActivity implements LauncherListener {
    private AppList appList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        new AppList(this, this);

    }

    @Override
    public void appsLoaded() {

    }

    @Override
    public void appInstalled() {
        appList.reLoad();
    }
}