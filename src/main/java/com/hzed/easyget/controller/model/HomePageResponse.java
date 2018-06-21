package com.hzed.easyget.controller.model;

import com.hzed.easyget.persistence.ext.entity.VaData;
import lombok.Data;

/**
 * @author dengzhenhao
 * @since 2018/6/15 16:19
 */
@Data
public class HomePageResponse {


    private Long id;


    /**
     * code=2004 时还款申请是否过期
     */
    private Boolean expire;

    /**
     * code=2005 时va数据
     */
    private VaData vaData;

}
