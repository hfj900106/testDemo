package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author dengzhenhao
 * @since 2018/6/28 17:01
 */
@Data
public class UploadPicEvidenceRequest {

    /**
     * va码
     */
    @NotBlank(message = "{param.repay.va.isNotEmpty}")
    private String va;

    /**
     * 交易方式 ATM OTC
     */
    @Pattern(regexp = "^BNI ATM|Mandiri ATM|Permata ATM|OTC$", message = "{param.repay.mode.must}")
    private String mode;

    /**
     * 标的id
     */
    @NotNull(message = "{param.repay.bidId.isNotEmpty}")
    private Long bidId;
    /**
     * base64Img 图片数组
     */
    @NotNull(message = "{param.repay.base64Imgs.isNotEmpty}")
    @Size(min = 1,max = 3)
    private String[] base64Imgs;
    /**
     * 对应的图片后缀
     */
    @NotNull(message = "{param.repay.picSuffixs.isNotEmpty}")
    @Size(min = 1,max = 3)
    private String[] picSuffixs;
}
