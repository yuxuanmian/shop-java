package com.zlt.shop.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlt.shop.entity.UserAcc;
import com.zlt.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 重写UserDetailsService来实现从数据库中读取用户信息
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //从数据库中匹配用户信息
        QueryWrapper<UserAcc> wrapper = new QueryWrapper<>(new UserAcc())
                .eq("username", username)
                .eq("is_deleted", "0")
                .eq("is_banned", "0");
        UserAcc userAcc = userMapper.selectOne(wrapper);


        String password = userAcc.getUserPassword();
        List<GrantedAuthority> authorities = new ArrayList<>();

        //设置角色
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userAcc.getRole()));

        return new User(username, password, authorities);


    }
}
