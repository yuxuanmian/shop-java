package com.zlt.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.shop.entity.Good;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GoodMapper extends BaseMapper<Good> {
}
