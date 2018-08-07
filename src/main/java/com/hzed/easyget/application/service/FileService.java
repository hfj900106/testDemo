package com.hzed.easyget.application.service;

import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.PicUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Value("${server.context-path}")
    private String contextPath;

    /**
     * 上传base64编码的图片到服务器目录
     *
     * @param base64Img 图片
     * @param picSuffix 后缀 如png、jpg
     * @return 服务器路径
     */
    public String uploadBase64Img(String base64Img, String picSuffix) throws Exception {
        // 请求url
        StringBuffer requestURL = RequestUtil.getHttpServletRequest().getRequestURL();
        // 取有效路径 如 http://localhost:8150/hzed
        String requestPath = requestURL.substring(0, requestURL.indexOf(contextPath) + contextPath.length());
        // 文件地址 如 /20180612/3214234234234.png
        String suffixPath = "/" + DateUtil.localDateTimeToStr3(LocalDateTime.now()) + "/" + IDGenerator.nextId() + "." + picSuffix;
        // 可访问地址 如 http://localhost:8150/hzed/20180612/3214234234234.png
        String returnUrl = requestPath + suffixPath;
        // 上传至 配置的 imgUploadPath 目录
        PicUtil.uploadImageAbs(base64Img, imgUploadPath + suffixPath);
        return returnUrl;
    }
}