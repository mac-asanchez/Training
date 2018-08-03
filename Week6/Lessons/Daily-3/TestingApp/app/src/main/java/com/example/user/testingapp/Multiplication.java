package com.example.user.testingapp;

import android.util.Log;

public class Multiplication {
    private static final String TAG = Multiplication.class.getSimpleName() + "_TAG";
    public int multiply(int a, int b) {
        return a * b;
    }

    public void doSomething() {
        Log.d(TAG, "doSomething: ");
    }
}
