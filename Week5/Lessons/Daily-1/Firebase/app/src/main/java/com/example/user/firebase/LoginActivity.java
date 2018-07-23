package com.example.user.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.firebase.Security.AuthManager;
import com.example.user.firebase.Security.Callback;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements Callback {

    private static final String TAG = LoginActivity.class.getSimpleName() + "_TAG";
    private EditText etEmail;
    private EditText etPassword;
    private AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        authManager = AuthManager.getInstance(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        if (authManager.isSessionValid()) {
            //redirect to different activity
            startHomeActivity();
        }
    }

    private void startHomeActivity() {
        Log.d(TAG, "startHomeActivity: ");
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    public void onCreateAccount(View view) {
        authManager.createAccount(etEmail.getText().toString(), etPassword.getText().toString());
    }

    public void onSignIn(View view) {
        Log.d(TAG, "onSignIn: ");
        authManager.signIn(etEmail.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void onLoginResults(FirebaseUser user) {
        Log.d(TAG, "onLoginResults: ");
        if (user != null) {
            startHomeActivity();
        } else {
            Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show();
        }
    }
}
