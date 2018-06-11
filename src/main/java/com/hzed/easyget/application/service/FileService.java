package com.hzed.easyget.application.service;

import com.hzed.easyget.infrastructure.utils.PicUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 文件操作
 *
 * @author guichang
 * @date 2018/6/11
 */

@Service
public class FileService {

    @Value("${system.imgUploadPath}")
    private String imgUploadPath;

    /**
     * 上传base64编码的图片到服务器目录
     *
     * @param base64Img 图片
     * @return 服务器路径
     */
    public String uploadBase64Img(String base64Img, String picSuffix) throws Exception {
        HttpServletRequest request = RequestUtil.getHttpServletRequest();

        StringBuffer requestURL = request.getRequestURL();
        String requestPath = requestURL.substring(0, requestURL.indexOf("easy-get"));
        String suffixPath = File.separator + "20180611" + File.separator + IdentifierGenerator.nextId() + "." + picSuffix;
        String s = requestPath + suffixPath;
        System.out.println("============="+s.replaceAll("\\\\", "/"));
        return PicUtil.uploadImageAbs(base64Img, imgUploadPath + suffixPath);
    }
}