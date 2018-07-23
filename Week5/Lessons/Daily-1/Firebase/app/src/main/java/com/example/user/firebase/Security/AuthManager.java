package com.example.user.firebase.Security;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class AuthManager {
    private static final String TAG = AuthManager.class.getSimpleName() + "_TAG";
    private static AuthManager authManager = null;
    FirebaseAuth auth;
    Activity activity;
    Callback callback;
    SignOutCallback signOutCallback;
    private FirebaseUser user;

    private AuthManager(Activity activity) {
        this.activity = activity;
        this.auth = FirebaseAuth.getInstance();

        if (activity instanceof Callback) {
            this.callback = (Callback) activity;
        }
        if (activity instanceof SignOutCallback) {
            this.signOutCallback = (SignOutCallback) activity;
        }
    }

    private AuthManager() {
        //defeat initialization
    }

    public static AuthManager getInstance(Activity activity) {
        if (authManager == null) {
            authManager = new AuthManager(activity);
        }
        else {
            authManager = new AuthManager(activity);
        }
        return authManager;
    }

    private void updateOwner(Activity activity){
        this.activity = activity;
        this.callback = (Callback) activity;
        this.auth = FirebaseAuth.getInstance();
    }

    public void createAccount(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            user = auth.getCurrentUser();
                            callback.onLoginResults(user);
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            callback.onLoginResults(null);
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void signIn(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            user = auth.getCurrentUser();
                            callback.onLoginResults(user);
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            callback.onLoginResults(null);
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public boolean isSessionValid() {
        FirebaseUser user = auth.getCurrentUser();
        return (user != null);
    }

    public FirebaseUser getUser() {
        FirebaseUser user = auth.getCurrentUser();
        return user;
    }

    public void signOut() {
        Log.d(TAG, "signOut: 1");
        auth.signOut();
        Log.d(TAG, "signOut: 2");
        signOutCallback.onSignOut();
    }
}
