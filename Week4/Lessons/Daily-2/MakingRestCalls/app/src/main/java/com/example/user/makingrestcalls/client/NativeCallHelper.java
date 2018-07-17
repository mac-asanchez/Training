package com.example.user.makingrestcalls.client;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class NativeCallHelper {
    public static final String TAG = NativeCallHelper.class.getSimpleName() + "_TAG";
    HttpURLConnection httpURLConnection;

    public void makeCall() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "run: ");
                    String BaseUrl = "http://www.mocky.io/v2/5b4cc66831000049005eba25";
                    URL url = new URL(BaseUrl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream stream = httpURLConnection.getInputStream();
                    Scanner scanner = new Scanner(stream);

                    while (scanner.hasNext()) {
                        Log.d(TAG, "run: " + scanner.nextLine());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
