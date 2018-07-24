package com.example.user.firebaseauthentication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.firebaseauthentication.Authentication.AuthenticationManager;
import com.example.user.firebaseauthentication.Authentication.SignInCallback;
import com.example.user.firebaseauthentication.model.MyUser;
import com.example.user.firebaseauthentication.utils.Constants;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import static com.example.user.firebaseauthentication.utils.Constants.GOOGLE_RC_SIGN_IN;

public class LoginActivity extends AppCompatActivity implements SignInCallback {

    private static final String TAG = LoginActivity.class.getSimpleName() + "_TAG";
    private EditText etEmail;
    private EditText etPassword;
    private AuthenticationManager authenticationManager;
    private LoginButton loginButton;
    private TwitterLoginButton mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTwitterSignIn();
        setContentView(R.layout.activity_main);

        getControls();

        authenticationManager = AuthenticationManager.getInstance(this);
        setGoogleSignIn();
        setFacebookSignIn();
        setTwitterSignIn2();
    }

    //region Activity
    private void getControls() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        if (authenticationManager.isSessionValid()) {
            //redirect to different activity
            startHomeActivity(authenticationManager.getUser());
        }
    }

    @Override
    public void onLoginResults(MyUser myUser) {
        Log.d(TAG, "onLoginResults: ");
        startHomeActivity(myUser);
    }

    @Override
    public void onSignOut() {
        Log.d(TAG, "onSignOut: ");
    }
    //endregion

    private void startHomeActivity(MyUser myUser) {
        Log.d(TAG, "startHomeActivity: ");
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra(Constants.MY_USER, myUser);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode: " + String.valueOf(requestCode) + ". resultCode: " + String.valueOf(resultCode));

        //region Google
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == GOOGLE_RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        //endregion

        //region Facebook
        CallbackManager callbackManager = authenticationManager.getFacebookCallbackManager();
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //endregion

        //region Twitter
        mLoginButton.onActivityResult(requestCode, resultCode, data);
        //endregion
    }

    //region Firebase Email
    public void onLoginEmailClicked(View view) {
        authenticationManager.firebaseSignIn(etEmail.getText().toString(), etPassword.getText().toString());
    }
    //endregion

    //region Google
    private void setGoogleSignIn() {
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.btnGoogleSignIn);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(authenticationManager.getGoogleSignInIntent(), GOOGLE_RC_SIGN_IN);
            }
        });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            MyUser myUser = new MyUser(account.getEmail(), account.getDisplayName());
            startHomeActivity(myUser);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());

        }
    }
    //endregion

    //region Facebook
    private void setFacebookSignIn() {
        loginButton = findViewById(R.id.btnFacebookSignIn);
        loginButton.setReadPermissions("email", "public_profile");

        loginButton.registerCallback(authenticationManager.getFacebookCallbackManager(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess: ");
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "onError", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        authenticationManager.facebookSignIn(credential);
    }
    //endregion

    //region Twitter
    private void setTwitterSignIn() {
        // Configure Twitter SDK
        TwitterAuthConfig authConfig = new TwitterAuthConfig(
                getString(R.string.twitter_consumer_key),
                getString(R.string.twitter_consumer_secret));

        TwitterConfig twitterConfig = new TwitterConfig.Builder(this)
                .twitterAuthConfig(authConfig)
                .build();

        Twitter.initialize(twitterConfig);
    }

    private void setTwitterSignIn2() {
        // [START initialize_twitter_login]
        mLoginButton = findViewById(R.id.btnTwitterSignIn);
        mLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG, "twitterLogin:success" + result);
                handleTwitterSession(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.w(TAG, "twitterLogin:failure", exception);
            }
        });
        // [END initialize_twitter_login]
    }

    private void handleTwitterSession(TwitterSession session) {
        Log.d(TAG, "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        authenticationManager.twitterSignIn(credential);
    }
// [END auth_with_twitter]
    //endregion
}
