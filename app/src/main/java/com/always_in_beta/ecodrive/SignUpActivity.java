package com.always_in_beta.ecodrive;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPhone;
    private EditText mEditTextPassword;
    private Button mButtonSignUp;
    private TextView mTextViewSignIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();

        mAuth = FirebaseAuth.getInstance();

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEditTextName.getText().toString();
                String email = mEditTextEmail.getText().toString();
                String phone = mEditTextPhone.getText().toString();
                String pass = mEditTextPassword.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(SignUpActivity.this, "The fields can't be empty!", Toast.LENGTH_SHORT).show();
                } else {
                    signUp(name, email, phone, pass);
                }
            }
        });

        mTextViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        mEditTextName = findViewById(R.id.edit_text_name);
        mEditTextEmail = findViewById(R.id.edit_text_email);
        mEditTextPhone = findViewById(R.id.edit_text_phone);
        mEditTextPassword = findViewById(R.id.edit_text_password);
        mButtonSignUp = findViewById(R.id.button_sign_up);
        mTextViewSignIn = findViewById(R.id.text_view_sign_in);
    }

    private void signUp(String name, String email, String phone, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });

                        Toast.makeText(SignUpActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }
}
