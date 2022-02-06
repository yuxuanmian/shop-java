package com.zlt.shop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户的账号信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("t_user")
public class UserAcc extends Entity implements Serializable {
    private String uid;
    private String username;
    private String userPassword;
    /**
     * 是否被删除，即销号，软删除
     */
    private int isDeleted;
    /**
     * 是否被封禁
     */
    private int isBanned;

    /**
     * 角色
     */
    private String role;
    /**
     * 盐值
     */
    private String salt;
}
