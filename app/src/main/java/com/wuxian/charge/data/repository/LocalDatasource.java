package com.wuxian.charge.data.repository;

import com.wuxian.charge.data.entities.ChargeBox;

import java.util.List;

/**
 * Created by jianglei on 2017/2/27.
 */

public interface LocalDatasource {
    boolean openBox(String boxId);

    boolean closeBox(String boxId);

    List<ChargeBox> listChargeBox();

    ChargeBox findStatus(String boxId);


}
