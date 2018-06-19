package com.hzed.easyget.infrastructure.model;

/**
 * @author guichang
 */
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayResponse {
    private String code;

    private String msg;

    private boolean flag;

    private String confirmTime;
    public static PayResponse getFailResponse() {
        PayResponse response = new PayResponse(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(), BizCodeEnum.UNKNOWN_EXCEPTION.getMessage(),false,"");
        return response;
    }

}
