package com.wuxian.charge.data.repository.remote;

import com.wuxian.charge.data.entities.Entity;
import com.wuxian.charge.data.entities.User;
import com.wuxian.charge.data.repository.RemoteDatasource;

import java.io.IOException;

/**
 * Created by jianglei on 2017/2/28.
 */

public class RemoteAPIDataSource implements RemoteDatasource {
    private static RemoteAPIDataSource mInstance;
    private Entity errorEntity;
    private ApiService service;

    public static RemoteAPIDataSource getInstance() {
        if (mInstance == null)
            mInstance = new RemoteAPIDataSource();
        return mInstance;
    }

    private RemoteAPIDataSource() {
        service = RetrofitProvider.create().create(ApiService.class);
        errorEntity = new Entity();
        errorEntity.status = -1;
        errorEntity.msg = "请求数据出错";
    }

    @Override
    public Entity<User> loginByAccount(String userName, String passWsord) {
        return null;
    }

    @Override
    public Entity<User> loginByFinger(String fingerData) {
        return null;
    }

    @Override
    public Entity<String> getQRCode(String bracnchId) {
        try {
            return service.getQRCode(bracnchId).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorEntity;
    }

    @Override
    public boolean uploadStatus(String brachId) {
        return false;
    }

    @Override
    public boolean uploadError(String brachId) {
        return false;
    }
}
