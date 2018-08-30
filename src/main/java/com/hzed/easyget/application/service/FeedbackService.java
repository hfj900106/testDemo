package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.FeedbackRequest;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.repository.FeedbackRepository;
import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import com.hzed.easyget.persistence.auto.entity.Feedback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hzed.easyget.infrastructure.utils.RequestUtil.getGlobalUser;

/**
 * 用户问题反馈
 *
 * @author hfj
 * @date 2018/8/30
 */
@Service
@Slf4j
public class FeedbackService {

    @Autowired
    private FileService fileService;
    @Autowired
    private FeedbackRepository feedbackRepository;

    public void add(FeedbackRequest request) {
        List<String> list =request.getImages();
        String picSuffix = request.getPicSuffix();
        String path="";
        for(String pathTemp : list){
            // 多张图片URL拼接到一起
            path = getPhotoPath(pathTemp, picSuffix)+";";
        }
        Feedback feedback = new Feedback();
        feedback.setId(IDGenerator.nextId());
        feedback.setUserId(getGlobalUser().getUserId());
        feedback.setPicUrl(path);
        feedback.setQuestionType(request.getQuestionType());
        feedback.setQuestionDesc(request.getQuestionDesc());

        feedbackRepository.add(feedback);
    }


    /**
     * 获取图片保存路径
     */
    private String getPhotoPath(String base64Img, String picSuffix) {
        String path;
        try {
            path = fileService.uploadBase64Img(base64Img, picSuffix);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传图片异常");
            throw new WarnException(BizCodeEnum.SERVICE_EXCEPTION);
        }
        return path;
    }

}