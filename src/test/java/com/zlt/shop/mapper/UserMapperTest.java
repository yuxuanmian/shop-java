package com.zlt.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlt.shop.entity.UserAcc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    UserMapper mapper;

    @Test
    public void testFindUserById() {
        UserAcc uid = mapper.selectOne(new QueryWrapper<>(new UserAcc()).eq("uid", "1"));
        System.out.println(uid);
    }

}