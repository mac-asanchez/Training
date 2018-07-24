package com.example.user.firebaseauthentication.Authentication;

import com.example.user.firebaseauthentication.model.MyUser;

public interface SignInCallback {
    void onLoginResults(MyUser user);
    void onSignOut();
}
