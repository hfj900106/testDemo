package com.hzed.easyget.infrastructure.utils;

import com.google.common.collect.Maps;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

/**
 * AES对称加密和解密
 *
 * @author Administrator
 */
public class AesUtil {
    private static final String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAN9YUHebhTYvS8QsI"
            + "zrhpojC9CXzHhk3vNVO/KQgXIMzkF1wBloIHEyTeKX817dqWgwahDed/TPYjZV4noBs1er+G7YzfPRr"
            + "Cr4bihCkWWmdwEfjMFtA7oL/LsrpuZKbpBqu8YJBc/x0PBFnID8mFtj9ONdgErVXeHC1FCpzrP7nAgM"
            + "BAAECgYEAyEC9/VAjE586ds0AVYYHNG5gTDp8zS2NkVA988bUTAsKsMyF1WmaPgqnzWQz4k9oJ5wK2n"
            + "c8lKTg5Zj9vLZY4an8yQeEJaHi+BxA80RIk1vchr84P1afjwvjW7Fg/biTQi+HiuXR8pT7psEkxNcSF"
            + "fXIrPz2xlB0PjOt1rpUfPkCQQD5uP0C1pmA82FiIFzGNLPg8DL9r+B2aNEPd8j5C2SwKTWJM0VHCsbc"
            + "dfKHfuv+6dsPrNMX+0tvts9bCz8QdUT9AkEA5PWUvJ5wQMf/WhtissYZOCUrGDnlO53RtRThuVkjMzd"
            + "oVu9dtlo0D0Q96lqai7xfc6DSFOtkoOA7leLVAKFqswJAODYLvbfQldYy9YOTXoo+c0OeNryAKict5+"
            + "1ur6CA6aZdBqUj0vn6CWYDUbygIqFBTiX43k0SjfL4eeXuLCY5gQI/IDJouB2Pq4x0XDd9qaYx5bNUX"
            + "BPIKJoI424MUAQ47vrbsV33z6zfpUXzbwFOulfDFH+JC2gOvpDJ0EoVqgEJAkAKw7TY0O9oE306MQUI"
            + "jujgbqGL7OyMX2/fTUtHLwgLpDEqii3M4b3hHwixKxIkrQ9DWhVqWg22NQrWPmYEsM00";

    /**
     * 加密
     * 1.构造密钥生成器
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    public static String aesEncode(String encodeRules, String content) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encodeRules.getBytes());
            keygen.init(128, random);
            ///keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
            SecretKey originalKey = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = originalKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteEncode = content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byteAes = cipher.doFinal(byteEncode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            String aesEncode = new String(new BASE64Encoder().encode(byteAes));
            //11.将字符串返回
            return aesEncode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }

    /**
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String aesDncode(String encodeRules, String content) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            ///keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encodeRules.getBytes());
            keygen.init(128, random);

            //3.产生原始对称密钥
            SecretKey originalKey = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = originalKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte[] byteContent = new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte[] byteDecode = cipher.doFinal(byteContent);
            String aesDecode = new String(byteDecode, "utf-8");
            return aesDecode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }


    /**
     * 加密
     * @param userId
     * @param timeStamp
     * @return
     */
    public static String aesEncode(Long userId, Long timeStamp) {
        Map<String, Long> param = Maps.newHashMap();
        param.put("userId", userId);
        param.put("timeStamp", timeStamp);
        return aesEncode(PRIVATE_KEY, param.toString());

    }

    public static void main(String[] args) {
        System.out.println(aesEncode("easyget","18825196282"));
        System.out.println(aesDncode("easyget","doZORjkcml8U03J1Ct+9xw=="));

    }

}