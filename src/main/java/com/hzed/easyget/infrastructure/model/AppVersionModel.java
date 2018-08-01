package com.hzed.easyget.infrastructure.model;

import lombok.Data;

/**
 * 版本号更新
 *
 * @author guichang
 * @date 2018/7/23
 */

@Data
public class AppVersionModel {
    String update_url;
    Integer force_update;
    Integer minimum_version;
    Boolean is_bom;
}