package com.zlt.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zlt.shop.entity.Classify;
import com.zlt.shop.entity.Good;
import com.zlt.shop.mapper.GoodMapper;
import com.zlt.shop.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements IGoodService {

    @Autowired
    GoodMapper mapper;

    @Override
    public void insertOne(Good good) {
        mapper.insert(good);
    }

    @Override
    public void modifyOne(Good old, Good good) {
        UpdateWrapper<Good> wrapper = new UpdateWrapper<>();
        wrapper.eq(true, "name", old.getName());
        mapper.update(good, wrapper);
    }

    @Override
    public void deleteOne(Good good) {
        UpdateWrapper<Good> wrapper = new UpdateWrapper<>();
        wrapper.eq(true, "name", good.getName());
        mapper.delete(wrapper);
    }

    @Override
    public List<Good> findAll(Classify classify) {
        QueryWrapper<Good> wrapper=new QueryWrapper<>();
        wrapper.eq(true,"good_classid", classify.getId());
        return mapper.selectList(null);
    }
}
