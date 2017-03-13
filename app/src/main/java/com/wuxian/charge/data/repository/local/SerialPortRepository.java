package com.wuxian.charge.data.repository.local;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Created by jianglei on 2017/2/24.
 */

public class SerialPortRepository {
    private static final String TAG = "SerialPortRepository";
    private static SerialPortRepository sInstance;
    private static int BAUDRATE = 9600;
    private static String PATH = "/dev/ttyS3";
    private SerialPortDataSource.OnReceivePortData onReceivePortData;
    private SerialPortDataSource local;

    public static SerialPortRepository getInstance() {
        if (sInstance == null)
            sInstance = new SerialPortRepository();
        return sInstance;
    }

    public static void init(String path, int baudRate) {
        PATH = path;
        BAUDRATE = baudRate;
        SerialPortDataSource.init(path, baudRate);
    }


    private SerialPortRepository() {
        local=SerialPortDataSource.getInstance();
    }

}
