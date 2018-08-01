package com.example.user.customviewlogin.security;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.user.customviewlogin.R;
import com.example.user.customviewlogin.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthManager {
    private static final String TAG = AuthManager.class.getSimpleName() + "_TAG";
    private static final String KEY_USER = "USER";

    Activity activity;
    Context context;
    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AuthManager(Context context) {
        Log.d(TAG, "AuthManager: ");
        this.context = context;
        this.activity = (Activity) context;

        this.sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public Boolean Login(String email, String password) {
        Log.d(TAG, "Login: email: " + email + " - password: " + password);
        String strUser = sharedPreferences.getString(KEY_USER, "");
        Log.d(TAG, "Login: " + strUser);
        try {
            JSONObject jsonObject = new JSONObject(strUser);
            String savedEmail = jsonObject.getJSONObject("user").getString("email");
            String savedPassword = jsonObject.getJSONObject("user").getString("password");
            Log.d(TAG, "Login: savedEmail: " + savedEmail);
            Log.d(TAG, "Login: savedPassword: " + savedPassword);

            return (email.equals(savedEmail) && password.equals(savedPassword));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Boolean Register(String email, String password) {
        Log.d(TAG, "Register: ");
        editor = sharedPreferences.edit();

        User user = new User(email, password);
        editor.putString(KEY_USER, user.toString());
        return editor.commit();
    }
}
