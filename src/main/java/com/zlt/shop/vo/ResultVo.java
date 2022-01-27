package com.zlt.shop.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回的的统一格式
 */
@Data
public class ResultVo implements Serializable {
    private String status;
    private String message;
    private Object object;

    public ResultVo(String status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public ResultVo(String status,String message) {
        this.status = status;
        this.message=message;
    }

    public ResultVo(String message, Object object) {
        this.message = message;
        this.object = object;
        this.status = "200";
    }
}
