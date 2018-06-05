package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.PictureCodeResponse;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.BaseBizException;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 图片验证码
 *
 * @author hfj
 * @date 2018/6/4
 */
@Service
public class PictureCodeService {
    @Autowired
    private RedisService redisService;

    private Random random = new Random();
    /**
     * 随机产生的字符串，1，I,0,O几个容易混淆的字符
     */
    private String codeStr = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    /**
     * 图片宽
     */
    private int width = 80;
    /**
     * 图片高
     */
    private int height = 26;
    /**
     * 随机产生字符数量
     */
    private int stringNum = 4;

    /**
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(new Font("Fixedsys", Font.CENTER_BASELINE, 18));
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(codeStr
                .length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 获取随机的字符
     */
    public String getRandomString(int num) {
        return String.valueOf(codeStr.charAt(num));
    }


    /**
     * 生成随机图片
     */
    public PictureCodeResponse getPictureCode(String mobile) {
        PictureCodeResponse codeResponse = new PictureCodeResponse();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics graphics = image.getGraphics();
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        graphics.setColor(new Color(225, 255, 255));
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(graphics, randomString, i);
        }
        //保存到Redis，五分钟有效时间
        redisService.setCache(RedisConsts.PICTURE_CODE + RedisConsts.SPLIT + mobile, randomString, 300L);
        //销毁graphics图形界面资源
        graphics.dispose();
        //返回图片二进制
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", bos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseBizException(BizCodeEnum.SERVICE_EXCEPTION.getCode(), "获取验证码图片时，图片转二进制异常" + e.getStackTrace().toString());
        } finally {
            if (bos != null) {
                try {
                    bos.close(); // 关闭流
                } catch (IOException e) {
                    throw new BaseBizException(BizCodeEnum.SERVICE_EXCEPTION.getCode(), "获取验证码图片时，关闭流异常" + e.getStackTrace().toString());
                }
            }
        }
        codeResponse.setPicture(bos.toByteArray());
        return codeResponse;
    }

    /**
     * 验证码验证
     */
    public void checkPictureCode(String mobile, String code) {
        //获取缓存数据
        String cacheCode = redisService.getCache(RedisConsts.PICTURE_CODE + RedisConsts.SPLIT + mobile);
        if (StringUtils.isBlank(cacheCode) || !code.equals(cacheCode)) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PICTURECODE);
        }
    }
}
