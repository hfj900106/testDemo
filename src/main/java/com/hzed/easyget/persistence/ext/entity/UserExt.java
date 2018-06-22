package com.hzed.easyget.persistence.ext.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author dengzhenhao
 * @since 2018/6/15 16:54
 */
@Data
public class UserExt {

    private LocalDateTime repaymentTime;

    private Long id;
}
