package com.example.user.customviewlogin.custom;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.customviewlogin.HomeActivity;
import com.example.user.customviewlogin.R;
import com.example.user.customviewlogin.security.AuthManager;

public class CustomLogin extends LinearLayout {
    private static final String TAG = CustomLogin.class.getSimpleName() + "_TAG";

    //region Instances
    LayoutInflater mInflater;
    AuthManager authManager;
    //endregion

    //region Constructors
    public CustomLogin(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init(context);
    }

    public CustomLogin(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init(context);
    }

    public CustomLogin(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomLogin(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mInflater = LayoutInflater.from(context);
        init(context);
    }
    //endregion

    public void init(final Context context) {
        //region get the view and inflate my layout
        Log.d(TAG, "init: ");
        View v = mInflater.inflate(R.layout.custom_login, this, true);
        //endregion

        //region create an instance of custom auth Manager (class for login/register)
        authManager = new AuthManager(context);
        //endregion

        //region Binding controls from the custom login layout
        final EditText etEmail = v.findViewById(R.id.etEmail);
        final EditText etPassword = v.findViewById(R.id.etPassword);
        Button btnLogin = v.findViewById(R.id.btnLogin);
        Button btnRegister = v.findViewById(R.id.btnRegister);
        //endregion

        //region on Login
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //region call the Login method
                Log.d(TAG, "Login.onClick: ");
                Boolean result = authManager.Login(etEmail.getText().toString(), etPassword.getText().toString());
                //endregion

                //region start second activity
                if (result)
                    startSecondActivity(context);
                else
                    Toast.makeText(context, "There was a problem", Toast.LENGTH_SHORT).show();
                //endregion
            }
        });
        //endregion


        //region on Register
        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //region call register method
                Log.d(TAG, "Register.onClick: ");
                Boolean result = authManager.Register(etEmail.getText().toString(), etPassword.getText().toString());
                //endregion

                if (result)
                    startSecondActivity(context);
                else
                    Toast.makeText(context, "There was a problem", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion
    }

    private void startSecondActivity(Context context) {
        Log.d(TAG, "startSecondActivity: ");
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
