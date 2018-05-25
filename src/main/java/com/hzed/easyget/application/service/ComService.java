package com.hzed.easyget.application.service;

import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.GlobalHeadr;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.UserTokenRepository;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.persistence.auto.entity.UserToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 一些公用的方法
 *
 * @author guichang
 * @since 2018/5/24
 */

@Service
public class ComService {
    @Autowired
    private UserTokenRepository userTokenRepository;
    @Autowired
    private RedisService redisService;

    /**
     * 校验请求头非token参数
     */
    public void validateHeader(GlobalHeadr globalHeadr) {
        if (StringUtils.isBlank(globalHeadr.getAppKey())) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_APPKEY);
        }
        if (StringUtils.isBlank(globalHeadr.getPlatform())) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PLATFORM);
        }
        if (StringUtils.isBlank(globalHeadr.getVersion())) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_VERSION);
        }
        if (StringUtils.isBlank(globalHeadr.getI18n())) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_I18N);
        }
        if (StringUtils.isBlank(globalHeadr.getImei())) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_IMEI);
        }
    }

    public void validateToken(GlobalHeadr globalHeadr) {
        String imei = globalHeadr.getImei();
        String token = globalHeadr.getToken();
        if (StringUtils.isBlank(token)) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_TOKEN);
        }

        GlobalUser globalUser = JwtUtil.verify(token, GlobalUser.class);
        if (globalUser == null) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_TOKEN);
        }
        Long userId = globalUser.getUserId();

        String tokenCacheKey = RedisConsts.TOKEN + RedisConsts.SPLIT + String.valueOf(userId) + RedisConsts.SPLIT + imei;
        String tokenCache = redisService.getCache(tokenCacheKey);

        if (StringUtils.isBlank(tokenCache)) {
            // 检查库中token是否失效，未失效则放入token
            UserToken userToken = userTokenRepository.findByUserIdAndImei(userId, imei);
            if (userToken == null) {
                throw new ComBizException(BizCodeEnum.ILLEGAL_TOKEN);
            } else if (!token.equals(userToken.getToken())) {
                throw new ComBizException(BizCodeEnum.ILLEGAL_TOKEN);
            } else if (LocalDateTime.now().compareTo(userToken.getExpireTime()) > 0) {
                throw new ComBizException(BizCodeEnum.TOKEN_EXPIRE);
            } else {
                redisService.setCache(tokenCacheKey, userToken.getToken(), RedisConsts.THREE_HOUR);
            }
        } else if (!token.equals(tokenCache)) {
            // 检查redis token是否与传过来的一致
            throw new ComBizException(BizCodeEnum.ILLEGAL_TOKEN);
        }


    }
}