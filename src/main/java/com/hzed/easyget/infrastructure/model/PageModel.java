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
    private Integer pageNo;
    /**
     * 每页条数
     */
    private Integer pageSize;

    public Integer getPageNo() {
        return (pageNo == null || pageNo < 1) ? 1 : pageNo;
    }

    public Integer getPageSize() {
        return (pageSize == null || pageSize < 0) ? 5 : pageSize;
    }

    public PageModel getPageModel() {
        return this;
    }

    public Integer getMysqlPageNo() {
        return getPageNo() - 1;
    }

    public Integer getOffset() {
        return (getPageNo() - 1) * getPageSize();
    }
}