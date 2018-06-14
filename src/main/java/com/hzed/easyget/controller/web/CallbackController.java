//package com.hzed.easyget.controller.web;
//
//import com.alibaba.fastjson.JSONObject;
//import com.hzed.easyget.application.service.CallbackService;
//import com.hzed.easyget.application.service.TransactionService;
//import com.hzed.easyget.controller.model.BlueNotificationRequest;
//import com.hzed.easyget.controller.model.PushBidCallbackRequest;
//import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
//import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
//import com.hzed.easyget.infrastructure.consts.ComConsts;
//import com.hzed.easyget.infrastructure.enums.BluePayStatusEnum;
//import com.hzed.easyget.infrastructure.model.Response;
//import com.hzed.easyget.infrastructure.repository.TempTableRepository;
//import com.hzed.easyget.persistence.auto.entity.TempTable;
//import com.hzed.easyget.persistence.auto.entity.UserTransaction;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
///**
// * 风控回调
// * @author hfj
// * @date 2018/6/9
// */
//@ExceptionAnno
//@RestController
//@RequestMapping("/easy-get")
//@Slf4j
//public class CallbackController {
//    @Autowired
//    private CallbackService callbackService;
//    @Autowired
//    private TempTableRepository tempTableRepository;
//    @Autowired
//    private TransactionService transactionService;
//
//    @ModuleFunc("推送资产-审核回调")
//    @PostMapping("/riskCallback/pushBidCallback")
//    public Response pushBidCallback(@RequestBody PushBidCallbackRequest request) {
//        callbackService.pushBidCallback(request);
//        return Response.getSuccessResponse();
//    }
//
//    /**
//     * 放款回调接口
//     * @param bluePayRq
//     */
//    @ModuleFunc("放款回调")
//    @PostMapping("/riskCallback/ lendingCallback")
//    public void lendingCallbackController(@Valid @RequestBody BlueNotificationRequest bluePayRq){
//        log.info("放款回调，bluepay端返回信息{}", JSONObject.toJSONString(bluePayRq));
//        //判断是否交易完成
//        Long tempId=0L;
//        if(bluePayRq.getStatus().equals(BluePayStatusEnum.BLUE_PAY_COMPLETE.getKey())){
//            //获取交易id 判断是否合法
//            List<UserTransaction> transactionList=transactionService.findUserTranBypayMenid(bluePayRq.getT_id());
//            if(!ObjectUtils.isEmpty(transactionList)){
//                //判断是否交易成功 防止重复交易
//                UserTransaction userTr=transactionList.get(0);
//                if(!userTr.getIsOver()){
//                    //查询相应的推送任务信息
//                     tempId=tempTableRepository.findTempTableByBidNoAndName(userTr.getBidId(), ComConsts.PUSH_BANK_TASK);
//                    //修改交易信息
//                    transactionService.callBackupdateUserTrance(userTr,tempId);
//                }
//            }
//        }else {
//            log.info("放款回调，bluepay处理失败{}",BluePayStatusEnum.getPayStatusEnum(bluePayRq.getStatus()));
//            tempTableRepository.upDateTemp(TempTable.builder().id(tempId).createTime(LocalDateTime.now()).remark("放款失败：" + BluePayStatusEnum.getPayStatusEnum(bluePayRq.getStatus())).build());
//        }
//    }
//
//
//}
