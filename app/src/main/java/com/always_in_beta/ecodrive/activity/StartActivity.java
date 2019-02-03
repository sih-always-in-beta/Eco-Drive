package com.always_in_beta.ecodrive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.always_in_beta.ecodrive.R;
import com.always_in_beta.ecodrive.service.UserTrackService;
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

        Intent intent = new Intent(StartActivity.this, UserTrackService.class);
        startService(intent);
    }
}
