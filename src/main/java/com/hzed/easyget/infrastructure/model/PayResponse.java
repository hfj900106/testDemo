package com.hzed.easyget.infrastructure.model;

/**
 * @author guichang
 */
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayResponse {
    private String code;

    private String msg;

    private boolean flag;
    public static PayResponse getFailResponse() {
        PayResponse response = new PayResponse(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(), BizCodeEnum.UNKNOWN_EXCEPTION.getMessage(),false);
        return response;
    }

}
