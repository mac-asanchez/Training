package com.freelance.ascstb.sdfilesinfo.model.manager;

import android.util.Log;

import com.freelance.ascstb.sdfilesinfo.model.data.FileResult;
import com.freelance.ascstb.sdfilesinfo.model.data.MyFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String TAG = FileManager.class.getSimpleName() + "_TAG";

    public FileManager() {
    }

    public void scanFiles(File file, final Callback callback) {
        Log.d(TAG, "scanFiles: ");
        //walkdir(Environment.getExternalStorageDirectory());
        getTotalFolders(file, callback);

        scanAll(file, callback);
    }

    //region Folders
    private void getTotalFolders(final File file, final Callback callback) {
        Log.d(TAG, "getTotalFolders: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: ");
                int totalFolders = scanFolders(file, 0);
                callback.onTotalFolders(totalFolders);
            }
        }).start();
    }

    private int scanFolders(File directory, int count) {
        count++;
        File[] files = directory.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    count = scanFolders(files[i], count);
                }
            }
        }

        return count;
    }
    //endregion

    private void scanAll(final File file, final Callback callback) {
        Log.d(TAG, "scanAll: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: ");
                List<MyFile> files = scanForResult(file, callback, 0);
                FileResult fileResult = new FileResult();
                fileResult.setFileList(files);
                callback.onResults(fileResult);
            }
        }).start();
    }

    private List<MyFile> scanForResult(File directory, Callback callback, int count) {
        count++;
        callback.onReportProgress(count);

        List<MyFile> result = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isDirectory()) {
                    result.addAll(scanForResult(files[i], callback, count));
                } else if (file.getName().contains(".")) {
                    try {
                        String name = file.getName();
                        long size = file.getTotalSpace();
                        String extension = file.getName().substring(file.getName().length() - 4);
                        if(extension.startsWith(".")) {
                            MyFile myFile = new MyFile(name, size, extension);
                            result.add(myFile);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return result;
    }

    public interface Callback {
        void onTotalFolders(int totalFolders);

        void onReportProgress(int progress);

        void onResults(FileResult results);
    }
}
