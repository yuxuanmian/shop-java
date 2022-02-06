package com.zlt.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zlt.shop.entity.Classify;
import com.zlt.shop.mapper.ClassifyMapper;
import com.zlt.shop.service.IClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifyServiceImpl implements IClassifyService {

    @Autowired
    ClassifyMapper mapper;

    //增
    @Override
    public void insertOne(Classify classify) {
        mapper.insert(classify);
    }

    //改
    @Override
    public void modifyOne(Classify old, Classify classify) {
        UpdateWrapper<Classify> wrapper=new UpdateWrapper<>();
        wrapper.eq(true,"name",old.getName());
        mapper.update(classify,wrapper);
    }

    //删
    @Override
    public void deleteOne(Classify classify) {
        UpdateWrapper<Classify> wrapper=new UpdateWrapper<>();
        wrapper.eq(true,"name",classify.getName());
        mapper.delete(wrapper);
    }

    //查
    public List<Classify> findAll() {
        return mapper.selectList(null);
    }
}
