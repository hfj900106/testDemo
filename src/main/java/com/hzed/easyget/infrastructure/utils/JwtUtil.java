package com.hzed.easyget.infrastructure.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.hzed.easyget.infrastructure.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author guichang
 */
@Slf4j
public class JwtUtil {
    private static final String SECRET = "NBG)(#*!()!KLowXX#$%()(#*!()!KLf>?N<:{LWPW";
    private static final String PAYLOAD = "payload";
    private static final String ALG = "alg";
    private static final String HS256 = "HS256";
    private static final String TYP = "typ";
    private static final String TYP_JWT = "JWT";
    /**
     * 失效时间 暂定7天
     */
    private static final int EXPIRE_DAYS = 7;

    /**
     * 根据用户信息创建token
     */
    public static <T> String createToken(T t) {
        try {
            Map<String, Object> map = Maps.newHashMap();
            Date expireDate = DateUtils.addDays(new Date(), EXPIRE_DAYS);
            map.put(ALG, HS256);
            map.put(TYP, TYP_JWT);
            return JWT.create().withHeader(map).withClaim(PAYLOAD, JSON.toJSONString(t)).withExpiresAt(expireDate).sign(Algorithm.HMAC256(SECRET));
        } catch (Exception e) {
            log.error("创建token失败，用户信息：{}", JSON.toJSONString(t), e);
            return null;
        }
    }

    /**
     * 校验
     */
    public static <T> T verify(String token, Class<T> clazz) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            return JSON.parseObject(claims.get(PAYLOAD).asString(), clazz);
        } catch (Exception e) {
            log.error("校验token异常，token：{}，异常信息：", token, e);
            return null;
        }
    }

    public static void main(String[] args) {
        LoginUser user = new LoginUser();
        user.setMobile("19919878790");
        user.setUserId(1233333L);

        String token = createToken(user);
        System.out.println(token);


        user = verify(token, LoginUser.class);
        System.out.println(JSON.toJSONString(user));

    }
}
