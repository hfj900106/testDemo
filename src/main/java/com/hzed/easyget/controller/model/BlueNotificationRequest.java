package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 放款回调通知请求实体
 * <p>Title: BlueNotificationRequest</p>
 * @author  madaijun
 * @date    2018年6月11日 上午9:12:05
 */
@Data
public class BlueNotificationRequest  implements Serializable {

    private static final long serialVersionUID = 3030045793860365162L;
    @NotBlank(message = "cmd不能为空!")
    private String cmd;
    @NotBlank(message = "手机号码不能为空!")
    private String msisdn;
    @NotBlank(message = "选择的银行不能为空!")
    private String operator;
    @NotBlank(message = "partner在调用接口时传来的transactionID不能为空!")
    private String t_id;
    @NotBlank(message = "BluePay在交易过程中生成的id不能为空!")
    private String bt_id;
    @NotBlank(message = "交易状态不能为空!")
    private String status;
    @NotNull(message = "交易金额不能为null!")
    private Integer price;
    @Pattern(regexp = "^cashout|bank$", message = "值只能为cashout|bank")
    private String interfacetype;
    @NotBlank(message = "用户手机号类型不能为空!")
    private String paytype;
    @NotBlank(message = "交易币种不能为空!")
    private String currency;
    @NotNull(message = "产品id不能为null!")
    private Integer productid;
    @NotBlank(message = "加密方式不能为空!")
    private String encrypt;


}