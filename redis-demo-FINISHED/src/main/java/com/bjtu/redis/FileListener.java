package com.bjtu.redis;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;


import java.io.File;


import org.apache.log4j.Logger;

public class FileListener extends FileAlterationListenerAdaptor {
    public static final Logger logger = Logger.getLogger(FileListener.class);
    @Override
    public void onStart(FileAlterationObserver observer) {

        super.onStart(observer);
    }

    @Override
    public void onFileChange(File file) {
        logger.info("有文件被修改：" + file.getName());
        System.out.println("Some files have been modified:" + file.getName());
        System.out.println("Reloading configuration file...");
        if (file.getName().equals("Counter.json") ) {
            RedisDemoApplication.readCounterConfig();
        }
        System.out.println("Configuration file has been reloaded.");
    }

    @Override
    public void onFileDelete(File file) {
        logger.info("有文件被删除：" + file.getName());
    }

    @Override
    public void onStop(FileAlterationObserver observer) {

        super.onStop(observer);
    }
}