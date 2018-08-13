package com.hzed.easyget.persistence.ext.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wuchengwu
 * @since 2018/7/31
 */
@Data
public class UserMessageExt {
    private String title;
    private LocalDateTime createTime;
    private Long userId;
    private boolean hasRead;
    private Long id;
}