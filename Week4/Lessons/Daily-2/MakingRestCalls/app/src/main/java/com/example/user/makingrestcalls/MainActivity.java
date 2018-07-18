package com.example.user.makingrestcalls;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.makingrestcalls.client.NativeCallHelper;
import com.example.user.makingrestcalls.client.OkhttpHelper;
import com.example.user.makingrestcalls.client.RetrofitHelper;
import com.example.user.makingrestcalls.model.CustomUser;
import com.example.user.makingrestcalls.model.Result;
import com.example.user.makingrestcalls.utils.HandlerCallBack;
import com.example.user.makingrestcalls.utils.HandlerUtils;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private NativeCallHelper nativeCallHelper;
    private TextView tvResult;
    private OkhttpHelper okhttpHelper;
    private RetrofitHelper retrofitHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        initClients();
    }

    @Override
    protected void onStart() {
        super.onStart();
        HandlerUtils.getDefault().setOwner(new HandlerCallBack(this, tvResult));
    }

    @Override
    protected void onStop() {
        super.onStop();
        HandlerUtils.getDefault().unregisterOwner();
    }

    private void initClients() {
        nativeCallHelper = new NativeCallHelper();
        okhttpHelper = new OkhttpHelper();
        retrofitHelper = new RetrofitHelper();
    }

    public void onNativeCall(View view) {
        nativeCallHelper.makeCall();
    }

    public void onOkhttp(View view) throws IOException {
        switch (view.getId()) {
            case R.id.btnOkhttpSync:
                okhttpHelper.executeSyncCall();
                break;
            case R.id.btnOkhttpAsync:
                okhttpHelper.executeAsyncCall();
                break;
        }

    }

    public void onRetrofit(View view) {
        final String results = "20";

        switch (view.getId()) {
            case R.id.btnRetroSync:
                Log.d(TAG, "onRetrofit: btnRetroSync");
                retrofitHelper.makeCallSync(results);
                break;

            case R.id.btnRetroAsync:
                Log.d(TAG, "onRetrofit: btnRetroAsync");
                retrofitHelper.makeCallAsync(results);
                break;

            case R.id.btnRetroRxJava:
                retrofitHelper.makeCallRxJava(results);
                break;

            case R.id.btnRetroRxJavaCustom:
                retrofitHelper.makeCallCustomRx(results, new RetrofitHelper.RetrofitCallBack() {
                    @Override
                    public void onResults(CustomUser customUser) {
                        tvResult.setText(customUser.getDepartment());
                    }
                });
                break;

            case R.id.btnRetroRxJavaCustomUser:
                retrofitHelper.makeCallCustomUserRx(results, new RetrofitHelper.ListCallBack() {
                    @Override
                    public void onResults(List<Result> resultList) {
                        StringBuilder sb = new StringBuilder();
                        for (Result result: resultList) {
                            sb.append(result.getName() + ",\n");
                        }

                        tvResult.setText(sb.toString());
                    }
                });
                break;
        }
    }
}
