package com.hzed.easyget.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户登录信息，存放在token中，有需要可以新增
 *
 * @author guichang
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalUser implements Serializable {
    @NotNull(message = "[userId]不可为空")
    private Long userId;
    @NotBlank(message = "[mobile]不可为空")
    private String mobile;
}
