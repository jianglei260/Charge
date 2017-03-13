package com.wuxian.charge.data.repository.local;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by jianglei on 2017/2/28.
 */

public class SerialPortDataSource {
    private static SerialPortDataSource mInstance;
    private static int BAUDRATE = 9600;
    private static String PATH = "/dev/ttyS3";
    private SerialPort serialPort;
    private boolean readable = true;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ReadThread readThread;
    private OnReceivePortData onReceivePortData;

    public static SerialPortDataSource getInstance() {
        if (mInstance == null)
            mInstance = new SerialPortDataSource();
        return mInstance;
    }

    public static void init(String path, int baudRate) {
        PATH = path;
        BAUDRATE = baudRate;
    }

    private SerialPortDataSource() {
        startPort();
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public OnReceivePortData getOnReceivePortData() {
        return onReceivePortData;
    }

    public void setOnReceivePortData(OnReceivePortData onReceivePortData) {
        this.onReceivePortData = onReceivePortData;
    }

    public boolean write(byte[] cmd) {
        if (outputStream != null)
            try {
                outputStream.write(cmd);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        return false;
    }

    public boolean startPort() {
        try {
            serialPort = new SerialPort(new File(PATH), BAUDRATE, 0);
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
            readThread = new ReadThread();
            readThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean closePort() {
        if (serialPort != null) {
            serialPort.close();
            if (readThread != null)
                readThread.interrupt();
            readable = false;
            return true;
        } else {
            return false;
        }
    }

    private class ReadThread extends Thread {
        @Override
        public void run() {
            super.run();
            if (serialPort == null) {
                readable = false;
                return;
            }
            while (readable) {
                int size = 0;
                byte[] buffer = new byte[2048];
                if (inputStream == null)
                    readable = false;
                try {
                    size = inputStream.read(buffer);
                    if (size>0){
                        byte[] content = new byte[size];
                        System.arraycopy(buffer, 0, content, 0, size);
                        if (onReceivePortData != null)
                            onReceivePortData.onReceived(content);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public interface OnReceivePortData {
        void onReceived(byte[] data);
    }
}
