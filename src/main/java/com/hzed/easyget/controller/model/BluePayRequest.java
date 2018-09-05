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
public class BluePayRequest implements Serializable {
    private static final long serialVersionUID = 3030045793860365162L;

    @NotBlank(message = "[cmd]不能为空")
    private String cmd;
    @NotBlank(message = "[msisdn]不能为空")
    private String msisdn;
    @NotBlank(message = "[operator]不能为空")
    private String operator;
    @NotBlank(message = "[t_id]不能为空")
    private String t_id;
    @NotBlank(message = "[bt_id]不能为空")
    private String bt_id;
    @NotBlank(message = "[status]不能为空")
    private String status;
    @NotNull(message = "[price]不能为空")
    private Integer price;
    @Pattern(regexp = "^cashout|bank$", message = "[interfacetype]值只能为cashout|bank")
    private String interfacetype;
    @NotBlank(message = "[paytype]不能为空")
    private String paytype;
    @NotBlank(message = "[currency]不能为空")
    private String currency;
    @NotNull(message = "[productid]不能为空")
    private Integer productid;
    @NotBlank(message = "[encrypt]不能为空")
    private String encrypt;


}