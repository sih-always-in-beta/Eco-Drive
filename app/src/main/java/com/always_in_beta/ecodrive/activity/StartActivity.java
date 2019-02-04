package com.always_in_beta.ecodrive.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.always_in_beta.ecodrive.R;
import com.always_in_beta.ecodrive.receiver.AlarmReceiver;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FirebaseAuth auth = FirebaseAuth.getInstance();

//        if (auth.getCurrentUser() != null) {
//            startActivity(new Intent(StartActivity.this, MapsActivity.class));
//            finish();
//        } else {
//            startActivity(new Intent(StartActivity.this, SignUpActivity.class));
//            finish();
//        }

        Intent intent = new Intent(this, AlarmReceiver.class);

        // TODO: 4/2/19  add request code to constants

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this, 6969, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long repeat = 60000;
        long triggerTime = SystemClock.elapsedRealtime() + 5000;

        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeat, notifyPendingIntent);
        }
    }
}
