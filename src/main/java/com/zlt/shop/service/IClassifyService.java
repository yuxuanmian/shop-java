package com.zlt.shop.service;

import com.zlt.shop.entity.Classify;

import java.util.List;

public interface IClassifyService {
    void insertOne(Classify classify);


    //改
    void modifyOne(Classify old, Classify classify);

    void deleteOne(Classify classify);

    List<Classify> findAll();
}
