package com.launchersdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.launcher.test.AppList;
import com.launcher.test.LauncherListener;
import com.launcher.test.model.AppInfo;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity implements LauncherListener {
    private AppList appList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        new AppList(this, this);

    }

    @Override
    public void appsLoaded(ArrayList<AppInfo> list) {

    }

    @Override
    public void appInstalled() {
        appList.reLoad();
    }
}