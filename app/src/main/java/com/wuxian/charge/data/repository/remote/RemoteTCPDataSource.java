package com.wuxian.charge.data.repository.remote;

import com.wuxian.charge.app.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by jianglei on 2017/2/28.
 */

public class RemoteTCPDataSource {
    private static RemoteTCPDataSource mInstance;
    private static final String HOST = Config.BASE_URL;
    private static final int PORT = 7272;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private static final int TIMEOUT = 15000;
    private ReadThread readThread;
    private boolean readable = true;
    private OnReceiveTCPData onReceiveTCPData;

    public static RemoteTCPDataSource getInstance() {
        if (mInstance == null)
            mInstance = new RemoteTCPDataSource();
        return mInstance;
    }

    private RemoteTCPDataSource() {
        startTCP();
    }

    public boolean startTCP() {
        try {
            socket = new Socket(HOST, PORT);
            socket.setKeepAlive(true);
            socket.setSoTimeout(TIMEOUT);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            readThread = new ReadThread();
            readThread.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public OnReceiveTCPData getOnReceiveTCPData() {
        return onReceiveTCPData;
    }

    public void setOnReceiveTCPData(OnReceiveTCPData onReceiveTCPData) {
        this.onReceiveTCPData = onReceiveTCPData;
    }

    public boolean write(String data) {
        if (outputStream != null) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            try {
                writer.write(data);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean closeTCP() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (readThread != null)
                readThread.interrupt();
            readable = false;
            return true;
        }
        return false;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    class ReadThread extends Thread {
        @Override
        public void run() {
            super.run();
            if (socket == null || inputStream == null) {
                readable = false;
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while (readable) {
                if (inputStream == null)
                    readable = false;
                try {
                    line = reader.readLine();
                    if (line != null && line.length() > 0 && onReceiveTCPData != null)
                        onReceiveTCPData.onReceived(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public interface OnReceiveTCPData {
        void onReceived(String data);
    }
}
