package com.example.user.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
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
import com.example.user.firebase.utils.Constants;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class HomeActivity extends AppCompatActivity implements SignOutCallback {

    private static final String TAG = HomeActivity.class.getSimpleName() + "_TAG";
    private AuthManager authManager;
    private RemoteDatabaseManager remoteDatabaseManager;
    private EditText etData;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etData = findViewById(R.id.etUploadData);

        authManager = AuthManager.getInstance(this);
        remoteDatabaseManager = RemoteDatabaseManager.getInstance();
    }

    //region Authentication
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
    //endregion

    //region Database
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
    //endregion

    //region Cloud Messaging
    @Override
    protected void onStart() {
        super.onStart();
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_RECEIVED_POST);
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }

    private void showDialog(final String postId, final String postMessage) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("New incoming post")
                .setMessage("New post: " + postMessage)
                .setNegativeButton("Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onLaterButtonClicked: ");
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Open", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                        intent.putExtra("postId", postId);
                        intent.putExtra("postMessage", postMessage);
                        startActivity(intent);
                    }
                })
                .create();

        alertDialog.show();
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() == Constants.ACTION_RECEIVED_POST) {
                String postId = intent.getStringExtra(Constants.POST_ID);
                String postMessage = intent.getStringExtra(Constants.POST_MESSAGE);

                showDialog(postId, postMessage);
            }
        }
    }
    //endregion
}
