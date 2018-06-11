package com.hzed.easyget.infrastructure.utils;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.FileModel;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 文件上传工具类
 *
 * @author wuchengwu
 * @data 2018/6/11
 */
@Slf4j
public class FileUtil {

    /**
     * @Description: 将base64编码字符串转换为图片
     * @param imgStr base64编码字符串
     * @param fileSuffix 图片文件后缀如：jpg,png
     * @return Response
     */

    private static String basePath;


    public static Response<FileModel> uploadByBase64(String imgStr, String fileSuffix) {

        if (imgStr == null) {
            throw new ComBizException(BizCodeEnum.FILE_NULL_ERROR);
        }
        OutputStream out = null;
        FileModel module = null;
        try {
            // 解密
            byte[] b = Base64.decodeBase64(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            String filename = IdentifierGenerator.nextId() + "." + fileSuffix;
            out = new FileOutputStream(basePath + File.separator + filename);
            out.write(b);
            out.flush();
            module = new FileModel();
            module.setFileName(filename);
            module.setFilePath(basePath + File.separator + filename);


        } catch (Exception e) {
            log.error("上传文件异常：{}",e.getMessage());
            throw new ComBizException(e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e2) {
                log.error("关闭文件流异常：{}", e2.getMessage());
                throw new ComBizException(e2.getMessage());
            }
        }

        return Response.getSuccessResponse(module);
    }
}