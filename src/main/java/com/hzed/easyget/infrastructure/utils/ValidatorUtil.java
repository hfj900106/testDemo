package com.hzed.easyget.infrastructure.utils;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
public class ValidatorUtil {

    public static void checkComBizExcep(boolean condition, BizCodeEnum codeEnum) {
        if (condition) {
            throw new ComBizException(codeEnum);
        }
    }

    public static <T> void validateWithNull(T t) {
        // 空校验
        checkComBizExcep(t == null, BizCodeEnum.REQUEST_PARAM_ILLEGAL);
        validate(t);
    }

    public static <T> void validate(T t) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (!constraintViolations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            int i = 0;
            int size = constraintViolations.size();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage() + (++i == size ? "" : "、"));
            }
            log.warn("==========参数验证出错！==========={}", errors);
            throw new ComBizException(BizCodeEnum.REQUEST_PARAM_ILLEGAL, errors.toString());
        }
    }
}
