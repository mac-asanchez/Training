package com.example.user.firebaseauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.firebaseauthentication.Authentication.AuthenticationManager;
import com.example.user.firebaseauthentication.Authentication.SignInCallback;
import com.example.user.firebaseauthentication.model.MyUser;
import com.example.user.firebaseauthentication.utils.Constants;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements SignInCallback {

    private static final String TAG = HomeActivity.class.getSimpleName() + "_TAG";
    private AuthenticationManager authenticationManager;
    private TextView tvEmail;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvEmail = findViewById(R.id.tvEmail);
        tvName = findViewById(R.id.tvName);

        authenticationManager = AuthenticationManager.changeOwner(this);
        loadUser();
    }

    private void loadUser() {
        MyUser user = getIntent().getParcelableExtra(Constants.MY_USER);
        tvEmail.setText(user.getEmail());
        tvName.setText(user.getName());
    }

    @Override
    public void onLoginResults(MyUser user) {
        Log.d(TAG, "onLoginResults: ");
    }

    @Override
    public void onSignOut() {
        Log.d(TAG, "onSignOut: ");
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void onSignOutClick(View view) {
        Log.d(TAG, "onSignOutClick: ");
        authenticationManager.signOut();
    }
}
