package com.hzed.easyget.controller.model;

import com.hzed.easyget.infrastructure.model.PageModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author dengzhenhao
 * @since 2018/6/28 16:20
 */
@Data
public class VaHistoryRequest extends PageModel {

    @NotNull(message = "{param.repay.bidId.isNotEmpty}")
    private Long bidId;

}
