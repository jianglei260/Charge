package com.wuxian.charge.data.repository.remote;

/**
 * Created by jianglei on 2017/2/27.
 */

public class ServerReppsitory {
    private static ServerReppsitory mInstance;
    private RemoteTCPDataSource tcpDataSource;
    private RemoteAPIDataSource apiDataSource;

    public static ServerReppsitory getInstance() {
        if (mInstance==null)
            mInstance=new ServerReppsitory();
        return mInstance;
    }
    private ServerReppsitory(){
        tcpDataSource=RemoteTCPDataSource.getInstance();
    }
}
