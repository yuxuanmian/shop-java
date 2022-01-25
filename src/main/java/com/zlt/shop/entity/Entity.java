package com.zlt.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 * 每个表都有修改时间和修改人。抽象出的父类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Entity {
    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 修改人
     */
    private String modifiedBy;
}
