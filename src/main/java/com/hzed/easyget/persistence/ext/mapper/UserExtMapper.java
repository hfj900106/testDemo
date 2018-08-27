package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.persistence.ext.entity.UserExt;
import com.hzed.easyget.persistence.ext.entity.UserTransactionExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author guichang
 */
public interface UserExtMapper {

    /**
     * 查询该用户已放款的标的对应未结清的账单的对应信息
     */
    UserExt queryUnRepayment(@Param("id") Long id);

    List<UserTransactionExt> listUserTransaction(@Param("userId") Long userId, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
}


