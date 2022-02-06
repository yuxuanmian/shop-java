package com.zlt.shop.service;

import com.zlt.shop.entity.Classify;
import com.zlt.shop.entity.Good;

import java.util.List;

public interface IGoodService {
    void insertOne(Good good);


    //改
    void modifyOne(Good old, Good good);

    void deleteOne(Good good);

    List<Good> findAll(Classify classify);
}
