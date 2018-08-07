package com.freelance.ascstb.sdfilesinfo.view.main;

import com.freelance.ascstb.sdfilesinfo.model.data.FileResult;
import com.freelance.ascstb.sdfilesinfo.model.data.MyFile;
import com.freelance.ascstb.sdfilesinfo.view.base.BasePresenter;
import com.freelance.ascstb.sdfilesinfo.view.base.BaseView;

import java.util.List;

public interface MainContract {
    interface View extends BaseView {
        void onTotalFolders(int totalFolders);

        void onReportProgress(int progress);

        void onResult(FileResult result);
    }

    interface Presenter extends BasePresenter<View> {
        void scanSDFiles();
    }
}
