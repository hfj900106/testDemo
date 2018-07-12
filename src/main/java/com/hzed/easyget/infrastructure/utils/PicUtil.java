package com.hzed.easyget.infrastructure.utils;


import com.google.common.collect.Maps;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.BaseBizException;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;


/**
 * @author guichang
 */
public class PicUtil {
    private static String basePath = PicUtil.class.getResource("/").getPath();

//    static {
//        // 初始化存放目录
//        basePath += "tempImgs";
//        new File(basePath).mkdirs();
//    }

    /**
     * 将base64编码字符串转换为图片
     *
     * @param base64ImgStr base64编码字符串
     * @param picSuffix    图片文件后缀如：jpg,png
     * @return 生成文件的完整路径
     */
    public static String uploadImage(String base64ImgStr, String picSuffix) throws Exception {
        String imgPathAbs = basePath + File.separator + IdentifierGenerator.nextId() + "." + picSuffix;
        return uploadImageAbs(base64ImgStr, imgPathAbs);

    }

    /**
     * 将base64编码字符串转换为图片
     *
     * @param base64ImgStr base64编码字符串
     * @param absFilePath  保存的绝对路径 如 c:/123.png
     * @return 保存的绝对路径
     */
    public static String uploadImageAbs(String base64ImgStr, String absFilePath) throws Exception {
        // 不存在就创建目录
        String fileDir = absFilePath.substring(0, absFilePath.lastIndexOf("/"));
        new File(fileDir).mkdirs();
        // 解密
        byte[] b = Base64.decodeBase64(base64ImgStr);
        // 处理数据
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        // 生成文件的完整路径
        OutputStream out = new FileOutputStream(absFilePath);
        out.write(b);
        out.flush();
        out.close();
        return absFilePath;

    }

    /**
     * 图片生成base64编码
     *
     * @param imgPath
     * @return
     */
    public static String picToBase64(String imgPath) throws Exception {
        InputStream in = new FileInputStream(new File(imgPath));
        byte[] data = new byte[in.available()];
        //读取图片字节数组  
        in.read(data);
        in.close();
        return Base64.encodeBase64String(data);
    }

    public static String getFileName(String imgPathAbs) {
        return imgPathAbs.substring(imgPathAbs.lastIndexOf(File.separator) + 1, imgPathAbs.length());
    }


    /**
     * 随机字符串，去掉易混淆的字符
     */
    private static String codeStr = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /**
     * 绘制字符串
     */
    private static String drowString(Graphics g, String randomString, int i) {

        Random random = new Random();
        g.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 18));
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(codeStr.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 获取随机的字符
     */
    public static String getRandomString(int num) {
        return String.valueOf(codeStr.charAt(num));
    }

    /**
     * 生成随机图片
     */
    public static Map<String, String> getPictureCode() {
        int width = 80;
        int height = 26;
        int strNum = 4;
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics graphics = image.getGraphics();
        graphics.setColor(new Color(232, 245, 243));
        graphics.fillRect(0, 0, width, height);
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= strNum; i++) {
            randomString = drowString(graphics, randomString, i);
        }
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
        Map<String, String> map = Maps.newHashMap();
        BASE64Encoder encoder = new BASE64Encoder();

        String strUTF8 = encoder.encode(bos.toByteArray());
        try {
            strUTF8 = URLEncoder.encode(strUTF8, "UTF-8");
            map.put("picStr", strUTF8);
            map.put("code", randomString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return map;
    }

}
