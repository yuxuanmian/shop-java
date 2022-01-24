package com.zlt.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.shop.entity.UserAcc;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserAcc> {

    

}
