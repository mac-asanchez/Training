package com.example.user.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.user.firebase.Security.AuthManager;
import com.example.user.firebase.Security.Callback;
import com.example.user.firebase.Security.SignOutCallback;
import com.example.user.firebase.data.RemoteDatabaseManager;
import com.example.user.firebase.data.model.Person;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class HomeActivity extends AppCompatActivity implements SignOutCallback {

    private static final String TAG = HomeActivity.class.getSimpleName() + "_TAG";
    private AuthManager authManager;
    private RemoteDatabaseManager remoteDatabaseManager;
    private EditText etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etData = findViewById(R.id.etUploadData);

        authManager = AuthManager.getInstance(this);
        remoteDatabaseManager = RemoteDatabaseManager.getInstance();
    }

    public void onSignOutClick(View view) {
        Log.d(TAG, "onSignOutClick: ");
        authManager.signOut();
    }

    @Override
    public void onSignOut() {
        Log.d(TAG, "onSignOut: ");
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void onUploadDataClick(View view) {
        Log.d(TAG, "onUploadDataClick: ");
        remoteDatabaseManager.uploadData(etData.getText().toString());
    }

    public void onUploadPersonClick(View view) {
        String firstName = "Jhon" + getRandomValue();
        String lastName = "Doe" + getRandomValue();

        remoteDatabaseManager.uploadPerson(new Person(firstName, lastName));
    }

    private String getRandomValue() {
        return String.valueOf(new Random().nextInt(100));
    }

    public void onReadPeopleClick(View view) {
        remoteDatabaseManager.readPeople();
    }
}
