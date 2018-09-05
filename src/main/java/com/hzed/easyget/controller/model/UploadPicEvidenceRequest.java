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

    @NotBlank(message = "[va]不能为空")
    private String va;
    @Pattern(regexp = "^BNI ATM|Mandiri ATM|Permata ATM|OTC$", message = "[mode]不能为空，交易方式支持 BNI ATM、Mandiri ATM、Permata ATM、OTC")
    private String mode;
    @NotNull(message = "[bidId]不能为空")
    private Long bidId;
    @NotNull(message = "[base64Imgs]图片数组不能为空，只能传1-3张")
    @Size(min = 1,max = 3)
    private String[] base64Imgs;
    @NotNull(message = "[picSuffixs]对应的图片后缀不能为空，1-3张图片后缀")
    @Size(min = 1,max = 3)
    private String[] picSuffixs;
}
