package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * @author wuchengwu
 * @date  2018/5/22
 */
@Data
public class AppVersionResponse {
    private String version;
    private String isUpdate;
    private String path;
    private String isForce;

}