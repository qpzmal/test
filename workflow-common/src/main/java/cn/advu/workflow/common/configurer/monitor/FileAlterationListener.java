package cn.advu.workflow.common.configurer.monitor;

import java.io.File;

public interface FileAlterationListener {
    void onInit(FileAlterationObserver observer);

    void onStart(FileAlterationObserver observer);

    void onDirectoryCreate(File file);

    void onDirectoryChange(File file);

    void onDirectoryDelete(File file);

    void onFileCreate(File file);

    void onFileChange(File file);

    void onFileDelete(File file);

    void onStop(FileAlterationObserver observer);
}
