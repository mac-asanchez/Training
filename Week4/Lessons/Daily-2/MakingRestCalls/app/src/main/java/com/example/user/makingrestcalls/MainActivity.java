package com.example.user.makingrestcalls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.user.makingrestcalls.client.NativeCallHelper;

public class MainActivity extends AppCompatActivity {

    private NativeCallHelper nativeCallHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nativeCallHelper = new NativeCallHelper();
    }

    public void onNativeCall(View view) {
        nativeCallHelper.makeCall();
    }
}
