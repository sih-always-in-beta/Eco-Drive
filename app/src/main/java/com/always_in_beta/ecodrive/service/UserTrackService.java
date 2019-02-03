package com.always_in_beta.ecodrive.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class UserTrackService extends Service {
    
    public Handler handler = null;
    public Runnable runnable = null;
    public Context context = this;
    private Intent mIntentService;

    public UserTrackService() {

    }

    @Override
    public void onCreate() {
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                Toast.makeText(context, "adasd", Toast.LENGTH_SHORT).show();
            }
        };
        handler.postDelayed(runnable, 4000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(context, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        
        return null;
    }
}