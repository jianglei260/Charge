package com.wuxian.charge.data.repository;

import com.wuxian.charge.data.entities.Entity;
import com.wuxian.charge.data.entities.User;

/**
 * Created by jianglei on 2017/2/27.
 */

public interface RemoteDatasource {
    public Entity<User> loginByAccount(String userName, String passWsord);

    public Entity<User> loginByFinger(String fingerData);

    public Entity<String> getQRCode(String bracnchId);

    //todo
    public boolean uploadStatus(String brachId);
    
    //// TODO: 2017/2/27  
    public boolean uploadError(String brachId);
}
