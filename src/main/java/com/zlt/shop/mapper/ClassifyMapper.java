package com.zlt.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.shop.entity.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ClassifyMapper extends BaseMapper<Classify> {
    //empty
}
