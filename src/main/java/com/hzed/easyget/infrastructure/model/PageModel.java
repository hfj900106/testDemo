package com.hzed.easyget.infrastructure.model;

import lombok.Data;

/**
 * 分页通用model
 *
 * @author guichang
 * @date 2018/8/23
 */

@Data
public class PageModel {
    /**
     * 页码
     */
    private Integer pageNo = 0;
    /**
     * 每页条数
     */
    private Integer pageSize = 5;

    public PageModel getPageModel() {
        return this;
    }
}