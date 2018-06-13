package com.hzed.easyget.infrastructure.utils;

import com.hzed.easyget.controller.model.PictureCodeResponse;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.BaseBizException;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;


/**
 * @author guichang
 */
public class PicUtil {
    private static String basePath = PicUtil.class.getResource("/").getPath();

    static {
        // 初始化存放目录
        basePath += "tempImgs";
        new File(basePath).mkdirs();
    }

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
    private static void drowString(Graphics graphics, String code, int num) {
        Random random = new Random();
        graphics.setFont(new Font("Fixedsys", Font.CENTER_BASELINE, 18));
        graphics.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        graphics.translate(random.nextInt(3), random.nextInt(3));
        graphics.drawString(code, 13 * num, 16);
    }

    public static String getCode(int codeNum) {
        StringBuffer randomString = new StringBuffer();
        for (int i = 1; i <= codeNum; i++) {
            randomString.append(String.valueOf(getRandomString(new Random().nextInt(codeStr.length()))));
        }
        return randomString.toString();
    }

    /**
     * 获取随机的字符
     */
    private static String getRandomString(int num) {
        return String.valueOf(codeStr.charAt(num));
    }

    /**
     * 生成随机图片
     */
    public static byte[] getPictureCode(String code, int num) {
        PictureCodeResponse codeResponse = new PictureCodeResponse();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(80, 26, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics graphics = image.getGraphics();
        graphics.fillRect(0, 0, 80, 26);
        graphics.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        graphics.setColor(new Color(138, 255, 174));
        // 绘制随机字符
        drowString(graphics, code, num);

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
        return bos.toByteArray();
    }


    public static void main(String[] args) throws Exception {
        String imgPath = "C:\\Users\\Administrator\\Desktop\\jifei.png";
        System.out.println(getFileName(imgPath));
//        String base64String = picToBase64(imgPath);
//        generateImage(base64String, "png");
    }
}
