package com.hzed.easyget.persistence.ext.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

/**
 * 神策进件、借款成功、还款业务类
 * @author wenhuaping
 * @date 2018-06-12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaExt {
    /** 神策进件 */
    /** 标的id */
    private Long bidId;
    /** 用户id */
    private Long userId;
    /** 标的状态：1-待走风控 2-待人审 3-人工审核不通过 4-审核通过 5-已放款 6-已结清 */
    private Byte bidStatus;
    /** 账单的状态：1-未结清 2-正常结清 3-逾期结清*/
    private Byte billStatus;
    /** 风控审核时间 */
    private Date handleTime;
    /** 风控审核结果 */
    private String handleResult;
    /** 用户手机号码 */
    private String telephone;
    /** 用户姓名 */
    private String userName;
    /** 银行卡代号 */
    private String inBank;

    /** 神策借款成功 */
    /** 应还时间 */
    private Date realRepaymentTime;

    /** 神策还款 */
    /** 账单的部分还款标志：1-是 0-否 */
    private Byte isPartialRepayment;

    /*** 运营商认证 */
    /** 用户手机号码 */
    private String userMobile;
    /** 是否推送成功 */
    private boolean bool;
    private String desc;
}
