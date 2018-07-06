/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2015, QOS.ch. All rights reserved.
 * <p>
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 * <p>
 * or (per the licensee's choosing)
 * <p>
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package com.hzed.easyget.infrastructure.config.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * 自定义日志拦截
 *
 * @author guichang
 */
@Data
public class MyLogFilter extends Filter<ILoggingEvent> {
    /**
     * 需拦截的moduleName
     */
    private String moduleName;
    /**
     * 过滤标志
     */
    private boolean match = true;

    @Override
    public FilterReply decide(ILoggingEvent event) {
        Map<String, String> mdcPropertyMap = event.getMDCPropertyMap();
        boolean isModuleName = !ObjectUtils.isEmpty(mdcPropertyMap) && mdcPropertyMap.get(MdcUtil.MODULENAME).indexOf(moduleName) > -1;
        if (match && isModuleName) {
            return FilterReply.ACCEPT;
        } else if(!match && !isModuleName) {
            return FilterReply.ACCEPT;
        } else {
            return FilterReply.DENY;
        }
    }

}
