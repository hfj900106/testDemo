package com.hzed.easyget.infrastructure.config.aliyun;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author guichang
 * @date 2018/6/5
 */
public class AliyunConfig {

    @Autowired
    private AliyunProp aliyunPro;

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(aliyunPro.getEndpoint(), aliyunPro.getAccessKeyId(), aliyunPro.getAccessKeySecret());
    }

    @Bean
    public AliyunService ossService(OSSClient ossClient) {
        AliyunService ossService = new AliyunService();
        ossService.setOssClient(ossClient);
        ossService.setAliyunProp(aliyunPro);
        return ossService;
    }
}
