package com.launcher.test;

import com.launcher.test.model.AppInfo;

import java.util.ArrayList;

public interface LauncherListener {

    public void appsLoaded(ArrayList<AppInfo> list);

    public void appInstalled();
}
