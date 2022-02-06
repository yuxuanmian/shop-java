package com.zlt.shop.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Component
public class PasswordEncoder {

    private static final int TIME = 3;

    /**
     * 对明文密码进行加密
     *
     * @param rawPassword 明文密码
     * @param salt        盐值
     * @return 加密后的密码
     */
    public String Encode(String rawPassword, String salt) {

        for (int i = 0; i < TIME; i++) {
            rawPassword = DigestUtils.md5DigestAsHex((salt+rawPassword+salt).toUpperCase()
                    .getBytes(StandardCharsets.UTF_8));
        }

        return rawPassword;

    }


    /**
     *
     * @param rawPassword 用户输入的密码
     * @param md5Password 数据库中的md5密码
     * @param salt 盐值
     * @return 是否匹配
     */
    public boolean match(String rawPassword,String md5Password,String salt){
        String password=Encode(rawPassword,salt);
        return password.equals(md5Password);
    }
}
