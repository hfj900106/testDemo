package com.hzed.easyget.infrastructure.config.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DownloadFileRequest;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.NestedException;
import lombok.Data;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 阿里云操作
 *
 * @author guichang
 * @date 2018/6/5
 */

@Data
public class AliyunService {
    private OSSClient ossClient;
    private AliyunProp aliyunProp;

    /**
     * 下载文件
     *
     * @param fileName        远程服务器文件名 如 abc.json
     * @param storedLocalFile 下载本地保存的文件路径
     * @throws Throwable
     */
    public void download(String fileName, String storedLocalFile) {
        DownloadFileRequest downloadFileRequest = new DownloadFileRequest(aliyunProp.getBucketName(), fileName);
        downloadFileRequest.setDownloadFile(storedLocalFile);
        downloadFileRequest.setTaskNum(5);
        downloadFileRequest.setPartSize(1024 * 1024 * 1L);
        downloadFileRequest.setEnableCheckpoint(true);

        try {
            ossClient.downloadFile(downloadFileRequest);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new NestedException(BizCodeEnum.ALIYUN_EXCEPTION, "下载文件失败");
        }
    }

    /**
     * 返回阿里云路径
     *
     * @param fileName      文件名 abc.json
     * @param localFilePath 本地文件绝对路径
     */
    public String upload(String fileName, String localFilePath) {
        UploadFileRequest uploadFileRequest = new UploadFileRequest(aliyunProp.getBucketName(), fileName);
        uploadFileRequest.setUploadFile(localFilePath);
        uploadFileRequest.setTaskNum(5);
        uploadFileRequest.setPartSize(1024 * 1024 * 1L);
        uploadFileRequest.setEnableCheckpoint(true);
        try {
            UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);
            return uploadResult.getMultipartUploadResult().getLocation();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new NestedException(BizCodeEnum.ALIYUN_EXCEPTION, "上传文件失败");
        }
    }

    /**
     * 普通上传,上传字符串
     *
     * @param fileName 阿里云地址域名后的部分路径 如 abc.json
     * @param content  上传的字符串内容
     * @return 阿里云文件路径
     */
    public String uploadString(String fileName, String content) {
        String bucketName = aliyunProp.getBucketName();
        this.ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(content.getBytes()));
        return getPath(fileName);
    }

    public String getPath(String fileName) {
        URI endPointUri;
        try {
            endPointUri = new URI(aliyunProp.getEndpoint());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new NestedException(BizCodeEnum.ALIYUN_EXCEPTION, "获取文件路径失败");
        }
        StringBuilder conbinedEndpoint = new StringBuilder();
        conbinedEndpoint.append(String.format("%s://", endPointUri.getScheme()));
        conbinedEndpoint.append(aliyunProp.getBucketName()).append(".").append(endPointUri.getHost());
        conbinedEndpoint.append(endPointUri.getPort() != -1 ? String.format(":%s", endPointUri.getPort()) : "");
        conbinedEndpoint.append(endPointUri.getPath());
        return conbinedEndpoint.toString() + "/" + fileName;
    }

}
