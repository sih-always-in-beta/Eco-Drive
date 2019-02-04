package com.always_in_beta.ecodrive.receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.always_in_beta.ecodrive.constant.Constants;
import com.always_in_beta.ecodrive.service.DetectedActivityIntentService;
import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

public class AlarmReceiver extends BroadcastReceiver {
    private Context cont;
    BroadcastReceiver broadcastReceiver;

    private Intent mIntentService;
    private PendingIntent mPendingIntent;
    private ActivityRecognitionClient mActivityRecognitionClient;

    @Override
    public void onReceive(Context context, Intent intent) {
        cont = context;
        Log.d("TAG_TAG_TAG", "service running successfully");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (Objects.requireNonNull(intent.getAction()).equals("activity_intent")) {
                    int type = intent.getIntExtra("type", -1);
                    int confidence = intent.getIntExtra("confidence", 0);
                    handleUserActivity(type, confidence);
                }
            }
        };

        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver,
                new IntentFilter("activity_intent"));
        startTracking();
    }

    private void handleUserActivity(int type, int confidence) {
        Log.d("TAG_TAG_TAG", "User activity: " + type + ", Confidence: " + confidence);
    }

    private void startTracking() {
        mActivityRecognitionClient = new ActivityRecognitionClient(cont);
        mIntentService = new Intent(cont, DetectedActivityIntentService.class);
        mPendingIntent = PendingIntent.getService(cont, 1, mIntentService, PendingIntent.FLAG_UPDATE_CURRENT);
        requestActivityUpdatesButtonHandler();
    }

    public void requestActivityUpdatesButtonHandler() {
        Task<Void> task = mActivityRecognitionClient.requestActivityUpdates(
                Constants.DETECTION_INTERVAL_IN_MILLISECONDS,
                mPendingIntent);

        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void result) {
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }
}