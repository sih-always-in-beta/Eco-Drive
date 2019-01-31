package com.always_in_beta.ecodrive;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPhone;
    private EditText mEditTextPassword;
    private EditText mEditTextPasswordConf;
    private Button mButtonSignUp;
    private TextView mTextViewSignIn;

    private TextInputLayout tx1;
    private TextInputLayout tx2;
    private TextInputLayout tx3;
    private TextInputLayout tx4;
    private TextInputLayout tx5;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();

        mAuth = FirebaseAuth.getInstance();

        mEditTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx1.setError(null);
            }
        });

        mEditTextEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx2.setError(null);
            }
        });

        mEditTextPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx3.setError(null);
            }
        });

        mEditTextPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx4.setError(null);
            }
        });

        mEditTextPasswordConf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx5.setError(null);
            }
        });

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEditTextName.getText().toString();
                String email = mEditTextEmail.getText().toString();
                String phone = mEditTextPhone.getText().toString();
                String pass = mEditTextPassword.getText().toString();
                String passConf = mEditTextPasswordConf.getText().toString();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pass)) {
                    if (pass.equals(passConf)) {
                        signUp(name, email, phone, pass);
                    } else {
                        tx5.setError("Passwords don't match");
                    }
                } else {
                    if (TextUtils.isEmpty(name)) {
                        tx1.setError("Please enter name");
                    }
                    if (TextUtils.isEmpty(email)) {
                        tx2.setError("Please enter Email ID");
                    }
                    if (TextUtils.isEmpty(phone)) {
                        tx3.setError("Please enter phone number");
                    }
                    if (TextUtils.isEmpty(pass)) {
                        tx4.setError("Please enter password");
                    }
                    if (TextUtils.isEmpty(passConf)) {
                        tx5.setError("Please enter password");
                    }
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
        mEditTextPasswordConf = findViewById(R.id.edit_text_password_confirm);
        mButtonSignUp = findViewById(R.id.button_sign_up);
        mTextViewSignIn = findViewById(R.id.text_view_sign_in);

//        Text Input Layouts
        tx1 = findViewById(R.id.textInputLayout1);
        tx2 = findViewById(R.id.textInputLayout2);
        tx3 = findViewById(R.id.textInputLayout3);
        tx4 = findViewById(R.id.textInputLayout4);
        tx5 = findViewById(R.id.textInputLayout5);
    }

    private void signUp(String name, String email, String phone, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });

                            Toast.makeText(SignUpActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(SignUpActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
