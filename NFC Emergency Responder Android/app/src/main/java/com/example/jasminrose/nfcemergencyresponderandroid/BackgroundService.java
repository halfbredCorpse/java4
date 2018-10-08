package com.example.jasminrose.nfcemergencyresponderandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class BackgroundService extends Service {
    public BackgroundService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // do your jobs here
        Log.i("bgservice", "test");

        return super.onStartCommand(intent, flags, startId);
    }
}
