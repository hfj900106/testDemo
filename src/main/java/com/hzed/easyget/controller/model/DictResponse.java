package com.hzed.easyget.controller.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典返回参数
 *
 * @author wuchengwu
 * @date 2018/6/19
 */
@Data
public class DictResponse implements Serializable {
    private String dictCode;
    private String dictValue;
}