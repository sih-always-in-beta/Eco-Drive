package com.always_in_beta.ecodrive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.always_in_beta.ecodrive.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonSignIn;
    private TextView mTextViewSignUp;

    private TextInputLayout tx1;
    private TextInputLayout tx2;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initView();

        mAuth = FirebaseAuth.getInstance();

        mEditTextEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx1.setError(null);
            }
        });

        mEditTextPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx2.setError(null);
            }
        });

        mButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditTextEmail.getText().toString();
                String pass = mEditTextPassword.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
                    signIn(email, pass);
                } else {
                    if (TextUtils.isEmpty(email)) {
                        tx1.setError("Please enter Email ID");
                    }
                    if (TextUtils.isEmpty(pass)) {
                        tx2.setError("Please enter password");
                    }
                }
            }
        });

        mTextViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        mEditTextEmail = findViewById(R.id.edit_text_email);
        mEditTextPassword = findViewById(R.id.edit_text_password);
        mButtonSignIn = findViewById(R.id.button_sign_in);
        mTextViewSignUp = findViewById(R.id.text_view_sign_up);

//        Text Input Layouts
        tx1 = findViewById(R.id.textInputLayout1);
        tx2 = findViewById(R.id.textInputLayout2);
    }

    private void signIn(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignInActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(SignInActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
