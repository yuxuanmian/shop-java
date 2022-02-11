package com.zlt.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlt.shop.entity.UserAcc;
import com.zlt.shop.ex.BadLoginException;
import com.zlt.shop.ex.BadRegisterException;
import com.zlt.shop.mapper.UserMapper;
import com.zlt.shop.service.IUserService;
import com.zlt.shop.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder encoder;


    /**
     * 用户登录
     *
     * @param accFromWeb 用户提交表单传来的数据封装
     */
    public UserAcc login(UserAcc accFromWeb) {
        QueryWrapper<UserAcc> wrapper = new QueryWrapper<>();
        wrapper.eq(true, "username", accFromWeb.getUsername());

        UserAcc userAcc = userMapper.selectOne(wrapper);

        if (userAcc == null) {
            throw new BadLoginException("用户名或密码错误");
        }

        String rawPassword = accFromWeb.getUserPassword();
        if (!encoder.match(rawPassword, userAcc.getUserPassword(), userAcc.getSalt())) {
            throw new BadLoginException("用户名或密码错误");
        }

        return userAcc;
    }

    /**
     * 用户注册
     *
     * @param accfromWeb 用户提交表单传来的数据封装
     */
    public void register(UserAcc accfromWeb) {

        QueryWrapper<UserAcc> wrapper = new QueryWrapper<>();
        wrapper.eq(true, "username", accfromWeb.getUsername());


        UserAcc userAcc = userMapper.selectOne(wrapper);

        if (userAcc != null) {
            throw new BadRegisterException("当前用户名已经存在，请重试");
        }


        //获取uuid
        String salt = UUID.randomUUID().toString().replaceAll("-", "");

        //获取md5密码
        String md5Password = encoder.Encode(accfromWeb.getUserPassword(), salt);


        UserAcc acc = new UserAcc();
        //初始化新用户并存入数据库
        acc.setRole("USER")
                .setUsername(accfromWeb.getUsername())
                .setUserPassword(md5Password)
                .setSalt(salt)
                .setUid(salt);

        userMapper.insert(acc);


    }

}
