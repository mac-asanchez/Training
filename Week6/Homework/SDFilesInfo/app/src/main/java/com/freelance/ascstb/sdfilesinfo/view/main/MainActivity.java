package com.freelance.ascstb.sdfilesinfo.view.main;

import android.Manifest;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.freelance.ascstb.sdfilesinfo.R;
import com.freelance.ascstb.sdfilesinfo.model.data.FileResult;
import com.freelance.ascstb.sdfilesinfo.model.data.MyFile;
import com.freelance.ascstb.sdfilesinfo.model.manager.FileManager;
import com.freelance.ascstb.sdfilesinfo.model.permission.PermissionManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    MainPresenter presenter;
    private ProgressBar pbScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        pbScan = findViewById(R.id.pbScan);
        pbScan.setProgress(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
        presenter.checkPermission();
    }

    public void onScanFiles(View view) {
        pbScan.setProgress(0);
        presenter.scanSDFiles();
    }

    public void onStopScan(View view) {
        presenter.stopScan();
    }

    @Override
    public void showError(String error) {
        Log.d(TAG, "showError: ");
    }

    @Override
    public void onTotalFolders(int totalFolders) {
        pbScan.setMax(totalFolders);
    }

    @Override
    public void onReportProgress(int progress) {
        int totalProgress = pbScan.getProgress() + progress;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pbScan.setProgress(totalProgress, true);
        } else {
            pbScan.setProgress(totalProgress);
        }
    }

    @Override
    public void onResult(FileResult result) {
        Log.d(TAG, "onResult: ");
        List<MyFile> biggestFiles = result.getBiggestFiles(10);
        Log.d(TAG, "onResult: biggestFiles: " + biggestFiles.toString());
        Log.d(TAG, "onResult: averageSize: " + String.valueOf(result.getAverageFileSize()));
    }
}
