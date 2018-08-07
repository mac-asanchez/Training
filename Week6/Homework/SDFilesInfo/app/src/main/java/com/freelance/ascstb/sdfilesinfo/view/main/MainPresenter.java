package com.freelance.ascstb.sdfilesinfo.view.main;

import android.Manifest;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.freelance.ascstb.sdfilesinfo.model.data.FileResult;
import com.freelance.ascstb.sdfilesinfo.model.manager.FileManager;
import com.freelance.ascstb.sdfilesinfo.model.permission.PermissionManager;

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getSimpleName() + "_TAG";
    private PermissionManager permissionManager;
    private MainContract.View view;
    private FileManager fileManager;

    public MainPresenter(Context context) {
        Log.d(TAG, "MainPresenter: ");
        this.permissionManager = new PermissionManager(context);
        permissionManager.setPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        fileManager = new FileManager();
    }

    public void checkPermission() {
        permissionManager.checkPermission(new PermissionManager.Callback() {
            @Override
            public void onPermissionResults(int requestCode, boolean isGranted) {
                Log.d(TAG, "onPermissionResults: ");
            }
        });
    }


    @Override
    public void scanSDFiles() {
        Log.d(TAG, "scanSDFiles: ");
        fileManager.scanFiles(Environment.getExternalStorageDirectory(), new FileManager.Callback() {
            @Override
            public void onTotalFolders(int totalFolders) {
                Log.d(TAG, "onTotalFolders: " + totalFolders);
                view.onTotalFolders(totalFolders);
            }

            @Override
            public void onReportProgress(int progress) {
                //Log.d(TAG, "onReportProgress: " + progress);
                view.onReportProgress(progress);
            }

            @Override
            public void onResults(FileResult results) {
                Log.d(TAG, "onResults: " + results.getFileList().size());
                view.onResult(results);
            }
        });
    }

    @Override
    public void attachView(MainContract.View view) {
        Log.d(TAG, "attachView: ");
        this.view = view;
    }

    @Override
    public void detachView() {
        Log.d(TAG, "detachView: ");
        this.view = null;
    }

    public void stopScan() {
        Log.d(TAG, "stopScan: ");
    }
}
