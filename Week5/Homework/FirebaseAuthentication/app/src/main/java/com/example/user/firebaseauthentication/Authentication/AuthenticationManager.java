package com.example.user.firebaseauthentication.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.firebaseauthentication.model.MyUser;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

public class AuthenticationManager {
    private static final String TAG = AuthenticationManager.class.getSimpleName() + "_TAG";
    private static AuthenticationManager authenticationManager = null;
    private static MyUser user;
    Activity activity;
    SignInCallback signInCallback;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient mGoogleSignInClient;
    private CallbackManager mfacebookSingInClient;

    private AuthenticationManager() {
    }

    public AuthenticationManager(Activity activity) {
        this.activity = activity;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.signInCallback = (SignInCallback) activity;

        //region Google Client
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        this.mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        //endregion

        //region Facebook Client
        mfacebookSingInClient = CallbackManager.Factory.create();
        //endregion
    }

    public static AuthenticationManager getInstance(Activity activity) {
        if (authenticationManager == null) {
            authenticationManager = new AuthenticationManager(activity);
        }
        return authenticationManager;
    }

    public static AuthenticationManager changeOwner(Activity activity) {
        authenticationManager.activity = activity;
        authenticationManager.firebaseAuth = FirebaseAuth.getInstance();
        authenticationManager.signInCallback = (SignInCallback) activity;

        //region Google Client
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        authenticationManager.mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);

        return authenticationManager;
    }

    public void firebaseSignIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            signInCallback.onLoginResults(new MyUser(user));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            signInCallback.onLoginResults(null);
                        }
                    }
                });
    }

    public void facebookSignIn(AuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            signInCallback.onLoginResults(new MyUser(user));
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Toast.makeText(FacebookLoginActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public boolean isSessionValid() {
        return (user != null);
    }

    public MyUser getUser() {
        return user;
    }

    public void signOut() {
        Log.d(TAG, "signOut: ");
        firebaseAuth.signOut();
        Log.d(TAG, "signOut: firebase");
        mGoogleSignInClient.signOut();
        Log.d(TAG, "signOut: google");
        signInCallback.onSignOut();
        Log.d(TAG, "signOut: interface");
    }

    public Intent getGoogleSignInIntent() {
        return mGoogleSignInClient.getSignInIntent();
    }

    public CallbackManager getFacebookCallbackManager() {
        return mfacebookSingInClient;
    }

    public void twitterSignIn(AuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            signInCallback.onLoginResults(new MyUser(user));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

    public void phoneSignIn(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            signInCallback.onLoginResults(new MyUser(user));
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });

    }
}
