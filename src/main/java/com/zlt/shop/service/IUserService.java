package com.zlt.shop.service;

import com.zlt.shop.entity.UserAcc;

public interface IUserService {
    void login(UserAcc accFromWeb);
    void register(UserAcc accfromWeb);
}
