package com.test.launcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.test.launcher.model.AppInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppList {
    private Context context;
    private ArrayList<AppInfo> list;


    public AppList(Context context) {
        this.context = context;
        new MyThread().execute();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_INSTALL);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        context.registerReceiver(br, intentFilter);
    }

    private final BroadcastReceiver br = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("Enter", "Enters here");
            Toast.makeText(context, "App Installed!!!!.", Toast.LENGTH_LONG).show();
        }
    };


    public class MyThread extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... Params) {
            PackageManager pm = context.getPackageManager();

            Intent i = new Intent(Intent.ACTION_MAIN, null);
            i.addCategory(Intent.CATEGORY_LAUNCHER);

            List<PackageInfo> apps = pm.getInstalledPackages(PackageManager.GET_META_DATA);
            for (PackageInfo info : apps) {
                if (!info.packageName.startsWith("com.android")) {
                    AppInfo app = new AppInfo();
                    app.label = String.valueOf(info.applicationInfo.loadLabel(pm));
                    app.icon = info.applicationInfo.loadIcon(pm);
                    app.packageName = info.packageName;
                    app.versionName = info.versionName;
                    app.versionCode = info.versionCode;
                    app.targetActivity = info.applicationInfo.className;
                    if (!app.packageName.equals(context.getPackageName()))
                        list.add(app);
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            Collections.sort(list, (object1, object2) -> object1.label.compareTo(object2.label));

        }
    }
}
