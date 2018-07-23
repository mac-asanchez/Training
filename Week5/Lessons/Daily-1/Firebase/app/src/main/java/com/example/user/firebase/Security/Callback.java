package com.example.user.firebase.Security;

import com.google.firebase.auth.FirebaseUser;

public interface Callback {
    void onLoginResults(FirebaseUser user);
}
