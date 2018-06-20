package com.hzed.easyget.controller.model;

import com.hzed.easyget.persistence.ext.entity.VaData;
import lombok.Data;

/**
 * @author dengzhenhao
 * @since 2018/6/15 16:19
 */
@Data
public class HomePageResponse {

    /**
     * type=0,无需弹框
     * type=1,弹出提示已经提交还款凭证,点击确定查看结果
     * type=2,弹出提示上次还款未完成，是否继续，点击继续到查看VA码页面
     * type=3,弹出提示有借款即将逾期或已经逾期，点击确定调到还款详情页
     */
    private String type;

    /**
     * type = 1 时表示还款记录id
     * type = 2 时表示还款记录id
     * type = 3 时表示标的id
     */
    private Long id;

    /**
     * 当type = 1 时需传出该交易是否过期
     */
    private Boolean expire;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * type = 2 时,va数据
     */
    private VaData vaData;

}
