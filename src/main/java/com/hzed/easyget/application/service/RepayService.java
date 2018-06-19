package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.*;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.PayProp;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.Arith;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.ThreadLocalUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author wuchengwu
 * @date 2018/6/4
 */
@Service
@Slf4j
public class RepayService {

    @Autowired
    private ComService comService;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private BidProgressRepository bidProgressRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillLedgerRepository billLedgerRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;
    @Autowired
    private UserRepaymentRepository userRepaymentRepository;
    @Autowired
    private RepayInfoFlowJobRepository repayInfoFlowJobRepository;
    @Autowired
    private RepayRepository repayRepository;
    @Autowired
    private RestService restService;
    @Autowired
    private PayProp prop;
    @Autowired
    private FileService fileService;

    /** 用户交易id key */
    private static final String USER_TRANCATIONID = "userTrancationId";
///    private static final String REQUEST_SEQ = "requestSeq";

    public RepayListResponse repaidList() {
        RepayListResponse repayListResponse = new RepayListResponse();

        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        List<Bid> bidList = bidRepository.findByUserIdAndStatus(userId, Lists.newArrayList(BidStatusEnum.REPAYMENT.getCode().byteValue(), BidStatusEnum.CLEARED.getCode().byteValue()));
        //没有借款记录
        if (bidList == null || bidList.isEmpty()) {
            return repayListResponse;
        }
        List<RepaymentResponse> repaymentResponseList = Lists.newArrayList();
        for (Bid bid : bidList) {
            RepaymentResponse repaymentResponse = new RepaymentResponse();
            Long bidId = bid.getId();
            BidProgress bidProgress = bidProgressRepository.findByBidIdAndType(bidId, BidProgressTypeEnum.CLEAR.getCode().byteValue());
            Bill bill = billRepository.findByBid(bidId);

            // 已结清
            if (BidStatusEnum.CLEARED.getCode().byteValue() == bid.getStatus().byteValue()) {
                repaymentResponse.setRepayTime(bidProgress.getHandleTime().toString());
                repaymentResponse.setStatus(RepayStatusEnum.CLEAR_REPAY.getCode().intValue());
            }
            // 未结清
            else {
                // 获取待还款标的的待还总额
                repayListResponse.setTotalAmount(comService.getBidNoRepay(bidId, LocalDateTime.now()));

                // 查询应还时间与当前时间对比，大于当前时间表示逾期，小于等于表示没到期
                int days = DateUtil.getBetweenDays(bill.getRepaymentTime(), LocalDateTime.now());

                // 逾期未结清
                if (days > 0) {
                    repaymentResponse.setStatus(RepayStatusEnum.OVDUE_UN_REPAY.getCode().intValue());
                    repaymentResponse.setDays(days);
                }
                // 正常未结清
                else {
                    repaymentResponse.setStatus(RepayStatusEnum.UN_REPAY.getCode().intValue());
                    int days1 = DateUtil.getBetweenDays(LocalDateTime.now(), bill.getRepaymentTime());
                    repaymentResponse.setDays(days1);
                }
            }

            repaymentResponse.setLoanAmount(bid.getLoanAmount());
            repaymentResponse.setBid(bidId);
            repaymentResponseList.add(repaymentResponse);
        }

        repayListResponse.setRepaymentInfo(repaymentResponseList);
        return repayListResponse;
    }

    /**
     * 还款详情
     *
     * @param request
     * @return
     */
    public RepayDetailResponse repayDetail(RepayDetailRequest request) {
        RepayDetailResponse repayDetailResponse = new RepayDetailResponse();

        Long bidId = request.getBidId();
        Bid bid = bidRepository.findById(bidId);
        // 账单
        Bill bill = billRepository.findByBid(bidId);
        // 放款时间
        BidProgress bidProgress = bidProgressRepository.findByBidIdAndType(bidId, BidProgressTypeEnum.LOAN.getCode().byteValue());

        // 还款时间或实际已还时间
        int status = bill.getStatus();
        if (BillStatusEnum.WAIT_CLEAR.getCode().equals(status)) {
            repayDetailResponse.setRepayTime(DateUtil.localDateTimeToStr2(bill.getRepaymentTime()));
        } else {
            repayDetailResponse.setRepayTime(DateUtil.localDateTimeToStr2(bill.getSettlementTime()));
        }

        // 还款状态
        if ((BidStatusEnum.CLEARED.getCode().toString()).equals(bid.getStatus().toString())) {
            repayDetailResponse.setStatus(RepayStatusEnum.CLEAR_REPAY.getCode());
        } else {
            int days = DateUtil.getBetweenDays(bill.getRepaymentTime(), LocalDateTime.now());
            repayDetailResponse.setStatus(days > 0 ? RepayStatusEnum.OVDUE_UN_REPAY.getCode() : RepayStatusEnum.UN_REPAY.getCode());

        }

        // 标的待还总费用
        BigDecimal totalRepayAmount = comService.getBidNoRepay(bidId, LocalDateTime.now());

        repayDetailResponse.setTotalRepayAmount(totalRepayAmount);
        repayDetailResponse.setPeriod(bid.getPeriod());
        repayDetailResponse.setLoanTime(DateUtil.localDateTimeToStr2(bidProgress.getHandleTime()));
        return repayDetailResponse;
    }

    /**
     * 结清全部
     */
    public void repayAll(RepayAllRequest request) {
        Long bidId = request.getBidId();
        BigDecimal bidNoRepay = comService.getBidNoRepay(bidId, LocalDateTime.now());

        // 提前生成转账交易后的流水号放入threadLocal中
        String requestSeq = String.valueOf(IdentifierGenerator.nextId());

        // TODO 走资金流

        // 信息流入库
        RepayInfoFlowJob jobInsert = new RepayInfoFlowJob();
        jobInsert.setId(IdentifierGenerator.nextId());
        jobInsert.setBidId(bidId);
        jobInsert.setRepaymentAmount(bidNoRepay);
        jobInsert.setRealRepaymentTime(LocalDateTime.now());
        jobInsert.setRepaymentMode(RepayModeEnum.ONLINE.getCode().byteValue());
        jobInsert.setRepaymentType(RepayTypeEnum.CLEAR.getCode().byteValue());
        jobInsert.setRequestseq(requestSeq);
        jobInsert.setCreateTime(LocalDateTime.now());
        repayInfoFlowJobRepository.insert(jobInsert);

        // 走信息流
//        repayInformationFlow(request.getBidId(), bidNoRepay, LocalDateTime.now(), requestSeq, null);


    }

    /**
     * 部分还款
     * 走信息流和资金流
     */
    public void repayPart(RepayPartRequest request) {
        Long bidId = request.getBidId();
        BigDecimal repayAmount = request.getRepayAmount();
        // 提前生成转账交易后的流水号放入threadLocal中
        String requestSeq = String.valueOf(IdentifierGenerator.nextId());

        // TODO 走资金流

        // 信息流入库
        RepayInfoFlowJob jobInsert = new RepayInfoFlowJob();
        jobInsert.setId(IdentifierGenerator.nextId());
        jobInsert.setBidId(bidId);
        jobInsert.setRepaymentAmount(repayAmount);
        jobInsert.setRealRepaymentTime(LocalDateTime.now());
        jobInsert.setRepaymentMode(RepayModeEnum.ONLINE.getCode().byteValue());
        jobInsert.setRepaymentType(RepayTypeEnum.PART.getCode().byteValue());
        jobInsert.setRequestseq(requestSeq);
        jobInsert.setCreateTime(LocalDateTime.now());
        repayInfoFlowJobRepository.insert(jobInsert);


        // 走信息流
//        repayInformationFlow(request.getBidId(), repayAmount, LocalDateTime.now(), requestSeq, null);

    }


    /**
     * 还款走信息流，包括全部结清和部分还款
     * 保存 t_loan_user_repayment 还款记录表
     * 保存 t_loan_bill_ledger 台账表
     * 保存 t_loan_bill 账单表
     * 保存 t_loan_bid_progress 标的进度表（主要更新还款信息）
     * 保存 t_loan_bid 标的表（主要是更新结清操作，部分还款不用更新）
     * 保存 t_loan_user_transaction 用户交易记录表
     *
     * @param bidId             标id
     * @param repayAmount       还款金额
     * @param realRepaymentTime 实际还款时间
     * @param job               定时任务标志，不是定时任务传null
     */
    @Transactional(rollbackFor = Exception.class)
    public void repayInformationFlow(Long bidId, BigDecimal repayAmount, LocalDateTime realRepaymentTime, String requestSeq, RepayInfoFlowJob job) {
        // 判断还款金额是否大于项目待还总额
        BigDecimal bidNoRepay = comService.getBidNoRepay(bidId, realRepaymentTime);
        if (repayAmount.compareTo(bidNoRepay) > 0) {
            throw new ComBizException(BizCodeEnum.OVER_REPAYMENT_MONEY);
        }

        Bid bid = bidRepository.findByIdWithExp(bidId);

        // 保存 t_loan_user_transaction 用户交易记录表
        long userTrancationId = IdentifierGenerator.nextId();
        // 放入threadLocal后面要用
        ThreadLocalUtil.set(USER_TRANCATIONID, userTrancationId);

        // 保存 t_loan_bid_progress 标的进度表
        BidProgress progressInsert = new BidProgress();
        progressInsert.setId(IdentifierGenerator.nextId());
        progressInsert.setBidId(bidId);
        progressInsert.setType(BidProgressTypeEnum.REPAY.getCode().byteValue());
        progressInsert.setHandleTime(LocalDateTime.now());
        progressInsert.setHandleResult("用户还款：" + repayAmount);
        bidProgressRepository.insert(progressInsert);

        // 待还账单
        Bill billBefore = billRepository.findAllBillByBidIdWithExp(bidId).get(0);
        // 还账单操作
        repayBill(billBefore, repayAmount, LocalDateTime.now());

        // 保存 t_loan_bid 标的表（主要是更新结清操作，部分还款不用更新）
        Bill billAfter = billRepository.findAllBillByBidIdWithExp(bidId).get(0);
        if (BillStatusEnum.NORMAL_CLEAR.getCode().intValue() == billAfter.getStatus().intValue()
                || BillStatusEnum.OVERDUE_CLEAR.getCode().intValue() == billAfter.getStatus().intValue()) {
            // 账单已结清 修改标的状态为结清
            Bid bidUpdate = new Bid();
            bidUpdate.setId(bidId);
            bidUpdate.setStatus(BidStatusEnum.CLEARED.getCode().byteValue());
            bidUpdate.setUpdateTime(LocalDateTime.now());
            bidRepository.update(bidUpdate);
        }

        if (job != null) {
            RepayInfoFlowJob jobUpdate = new RepayInfoFlowJob();
            jobUpdate.setId(job.getId());
            jobUpdate.setStatus(JobStatusEnum.SUCCESS.getCode().byteValue());
            jobUpdate.setTimes((byte) (job.getTimes().intValue() + 1));
            jobUpdate.setUpdateTime(LocalDateTime.now());
            repayInfoFlowJobRepository.update(jobUpdate);
        }

    }

    /**
     * 账单还款
     * 逾期费->尾款->本金
     *
     * @param bill              账单
     * @param repayAmount       总还款金额
     * @param realRepaymentTime 实际还款时间
     * @return 剩余金额
     */
    private BigDecimal repayBill(Bill bill, BigDecimal repayAmount, LocalDateTime realRepaymentTime) {
        Long billId = bill.getId();
        // 还逾期费
        BigDecimal restAmount1 = repayBillLedger(billId, BillLedgerItemEnum.OVERDUE_FEE.getCode().intValue(), repayAmount, realRepaymentTime);
        // 还尾款
        BigDecimal restAmount2 = repayBillLedger(billId, BillLedgerItemEnum.TAIL_FEE.getCode().intValue(), restAmount1, realRepaymentTime);
        // 还本金
        BigDecimal restAmount3 = repayBillLedger(billId, BillLedgerItemEnum.CORPUS.getCode().intValue(), restAmount2, realRepaymentTime);
        // 本次还款金额
        BigDecimal repayAmountNow = restAmount3.compareTo(Arith.ZERO) >= 0 ? repayAmount.subtract(restAmount3) : repayAmount;
        // 更新账单表
        Bill billUpdate = new Bill();
        billUpdate.setId(billId);
        billUpdate.setRealRepaymentAmount(bill.getRealRepaymentAmount().add(repayAmountNow));
        billUpdate.setUpdateTime(LocalDateTime.now());

        // 判断此笔账单是否结清
        BillLedger corpusLedger = billLedgerRepository.findBillLedgerItemByBillIdWithExp(billId, BillLedgerItemEnum.CORPUS.getCode().byteValue());
        // 本金没有待还表示已结清
        if (corpusLedger.getRepaymentAmount().compareTo(corpusLedger.getRealRepaymentAmount()) == 0) {
            billUpdate.setSettlementTime(LocalDateTime.now());
            // 逾期结清还是正常结清
            billUpdate.setStatus(DateUtil.compare(realRepaymentTime, LocalDateTime.now()) ?
                    BillStatusEnum.OVERDUE_CLEAR.getCode().byteValue() : BillStatusEnum.NORMAL_CLEAR.getCode().byteValue());
        } else {
            // 部分还款
            billUpdate.setIsPartialRepayment(true);
        }
        billRepository.update(billUpdate);
        return restAmount3;
    }

    /**
     * 台账还款
     *
     * @param billId            账单id
     * @param item              台账类型 本金、尾款、逾期费
     * @param repayAmount       总还款金额
     * @param realRepaymentTime 实际还款时间
     * @return 剩余金额
     */
    private BigDecimal repayBillLedger(Long billId, Integer item, BigDecimal repayAmount, LocalDateTime realRepaymentTime) {
        // 还款金额为0
        if (repayAmount == null || repayAmount.compareTo(Arith.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        // 待还总额
        BigDecimal billItemNoRepay = comService.getBillItemNoRepay(billId, item, realRepaymentTime);
        // 已结清则直接返回还款金额
        if (billItemNoRepay.compareTo(Arith.ZERO) <= 0) {
            return repayAmount;
        }
        // 剩余金额
        BigDecimal restAmount = repayAmount.subtract(billItemNoRepay);
        // 本次还款金额
        BigDecimal repayAmountNow = restAmount.compareTo(Arith.ZERO) <= 0 ? repayAmount : billItemNoRepay;

        // 保存 t_loan_user_repayment 还款记录表
        UserRepayment userRepayment = new UserRepayment();
        userRepayment.setId(IdentifierGenerator.nextId());
        // 从threadLocal里面拿
        userRepayment.setTransactionId(ThreadLocalUtil.get(USER_TRANCATIONID));
        userRepayment.setBillId(billId);
        userRepayment.setRepaymentItem(item.byteValue());
        userRepayment.setRepaymentAmount(billItemNoRepay);
        userRepayment.setRealRepaymentAmount(repayAmountNow);
        userRepaymentRepository.insert(userRepayment);

        // 新增或更新t_loan_bill_ledger 台账表
        BillLedger ledger = billLedgerRepository.findBillLedgerItemByBillId(billId, item.byteValue());
        // 逾期费单独处理
        if (BillLedgerItemEnum.OVERDUE_FEE.getCode().equals(item)) {
            // 无逾期费记录则新增
            if (ledger == null) {
                BillLedger ledgerInsert = new BillLedger();
                ledgerInsert.setId(IdentifierGenerator.nextId());
                ledgerInsert.setBillId(billId);
                ledgerInsert.setRepaymentTime(LocalDateTime.now());
                ledgerInsert.setRepaymentAmount(billItemNoRepay);
                ledgerInsert.setRepaymentItem(item.byteValue());
                ledgerInsert.setRealRepaymentTime(LocalDateTime.now());
                ledgerInsert.setRealRepaymentAmount(repayAmountNow);
                ledgerInsert.setCreateTime(LocalDateTime.now());
                billLedgerRepository.insert(ledgerInsert);
            }
            // 有逾期费记录则修改
            else {
                BillLedger ledgerUpdate = new BillLedger();
                ledgerUpdate.setId(ledger.getId());
                ledgerUpdate.setRepaymentTime(LocalDateTime.now());
                ledgerUpdate.setRepaymentAmount(billItemNoRepay);
                ledgerUpdate.setRealRepaymentTime(LocalDateTime.now());
                ledgerUpdate.setRealRepaymentAmount(repayAmountNow);
                ledgerUpdate.setUpdateTime(LocalDateTime.now());
                billLedgerRepository.update(ledgerUpdate);
            }
        }
        // 其他台账做更新操作(本金+尾款)
        else {
            if (ledger == null) {
                throw new ComBizException(BizCodeEnum.ILLEGAL_LEDGER_TYPE, billId, item);
            }

            BillLedger ledgerUpdate = new BillLedger();
            ledgerUpdate.setId(ledger.getId());
            ledgerUpdate.setRealRepaymentTime(LocalDateTime.now());
            // 实还金额累加
            ledgerUpdate.setRealRepaymentAmount(repayAmountNow.add(ledger.getRealRepaymentAmount()));
            ledgerUpdate.setUpdateTime(LocalDateTime.now());
            billLedgerRepository.update(ledgerUpdate);

        }

        return restAmount.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : restAmount;
    }

    public RepayPartDetailResponse repayPartDetail(RepayPartDetailRequest request) {
        String bidStr = request.getBidId();
        Long bidId = Long.valueOf(bidStr);
        //获取标的待还总费用
        BigDecimal bidNoRepay = comService.getBidNoRepay(bidId, LocalDateTime.now());
        //获取账单待还逾期费
        Bill bill = billRepository.findByBid(bidId);
        BigDecimal billOverFeeNoRepay = comService.getBillOverFeeNoRepay(bill.getId(), LocalDateTime.now());
        //待应还总额
        BigDecimal totalRepayAmount = bidNoRepay.add(billOverFeeNoRepay);
        Bid bid = bidRepository.findByIdWithExp(bidId);
        //todo 计算最小应还金额

        return RepayPartDetailResponse.builder().totalAmount(totalRepayAmount.toString()).inAccount(bid.getInAccount()).build();
    }

    /**
     * 还款页面标的详情
     * @param bidId 标id
     * @param flag 是否全部还清
     * @return
     */
    public LoanManagResponse findloanManagResponse(Long bidId,boolean flag) {
        return  repayRepository.findloanManagResponse(bidId,flag);
    }

    /**
     * 获取va码
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public TransactionVAResponse findVATranc(TransactionVARequest request) {
        //先查询数据库
        TransactionVAResponse vaResponse=repayRepository.findVATranc(request);
        if(ObjectUtils.isEmpty(vaResponse)){
            //当数据库为空 或者va码失效了
            PaymentCodeRequest payment=repayRepository.finduserTransBypaymentId(request.getPayId());
            payment.setMsisdn(RequestUtil.getGlobalUser().getMobile());
//            payment.setMsisdn("8613136384502");
            payment.setPayType(request.getMode());
            log.info("获取还款码，请求bluepay请求参数{}",JSONObject.toJSONString(payment));
            //String result=restService.doPostJson(prop.getAbsGetPaymentCodeUrl(), JSONObject.toJSONString(payment));
            String result="{\n" +
                    "\n" +
                    "    \"code\": \"0000\",\n" +
                    "\n" +
                    "    \"data\": \"{\\\"paymentCode\\\":\\\"8359180612539716\\\",\\\"transactionId\\\":\\\"transactionIdorder1241\\\"}\",\n" +
                    "\n" +
                    "    \"flag\": true,\n" +
                    "\n" +
                    "    \"msg\": \"success!\",\n" +
                    "\n" +
                    "    \"requestNo\": \"1246\"\n" +
                    "\n" +
                    "}";
            log.info("获取还款码，bluepay返回数据{}",result);
            if(result.equals(BizCodeEnum.TIMEOUT.getCode())){
                throw new ComBizException(BizCodeEnum.LOAN_TRANSACTION_ERROR,BizCodeEnum.TIMEOUT.getMessage());
            }
            JSONObject resu= JSON.parseObject(result);
            //解析返回信息
            if(resu.get("code").equals(BizCodeEnum.SUCCESS.getCode())){
                String paymentCode=JSON.parseObject(resu.getString("data")).getString("paymentCode");
                log.info("还款码{}",paymentCode);
                UserTransactionRepay repay=UserTransactionRepay.builder()
                        .id(IdentifierGenerator.nextId())
                        .mode(request.getMode())
                        .transactionId(request.getPayId())
                        .va(paymentCode)
                        .status(TransactionRepayEnum.TO_BE_TREATED.getCode().byteValue())
                        .vaCreateTime(LocalDateTime.now()).build();
                //插入va码到数据库
                repayRepository.insertSelective(repay);
                //组装返回信息
                vaResponse=new TransactionVAResponse();
                vaResponse.setCreateTime(repay.getVaCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                vaResponse.setMode(request.getMode());
                vaResponse.setVaCodel(paymentCode);
            }else {
                throw new ComBizException(resu.getString("code"),resu.getString("msg"));
            }
        }
        return  vaResponse;
    }

    /**
     * 根据id查询交易信息
     * @param payId
     * @return
     */
    public UserTransaction selectByKey(Long payId) {
        return userTransactionRepository.selectByKey(payId);
    }

    /**
     * 还款
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public PayResponse repayment(RepaymentRequest request,UserTransaction transaction) throws Exception {
        PayResponse response=null;
        //获取所有的va码记录
        List<UserTransactionRepay> repayList=repayRepository.finaAllVAcodeBypermas(request);
        //组装请求报文
        RepaymentCompleRequest compleRequest=new RepaymentCompleRequest();
        compleRequest.setBankType(transaction.getBank());
        compleRequest.setMsisdn(RequestUtil.getGlobalUser().getMobile());
        compleRequest.setPayType(request.getMode());
        compleRequest.setPrice(transaction.getAmount());
        compleRequest.setTransactionId(transaction.getPaymentId());
        List<String> listCode= Arrays.asList(BizCodeEnum.PROCESS_LENDING.getCode(),BizCodeEnum.SUCCESS.getCode(),BizCodeEnum.REPAYMENTS.getCode());
        for (UserTransactionRepay x:repayList){
            compleRequest.setPaymentCode(x.getVa());
            compleRequest.setRequestNo(IdentifierGenerator.nextSeqNo());
            //TODO 调用完成还款接口
            log.info("完成还款接口请求报文：{}", JSON.toJSONString(compleRequest));
            String result = restService.doPostJson(prop.getAbsReceiverTransactionUrl(), JSON.toJSONString(compleRequest));
            if(result.equals(BizCodeEnum.TIMEOUT.getCode())){
                throw new ComBizException(BizCodeEnum.LOAN_TRANSACTION_ERROR,BizCodeEnum.TIMEOUT.getMessage());
            }
            log.info("完成还款接口返回报文：{}", result);
            JSONObject resu= JSON.parseObject(result);
            //判断返回状态 0000 0001 0002
            if (!listCode.contains(resu.getString("code"))) {
                throw new ComBizException(BizCodeEnum.LOAN_TRANSACTION_ERROR, resu.getString("msg"));
            }
            String paymentCode=JSON.parseObject(resu.getString("data")).getString("paymentCode");
            //如果返回va码 则不用继续循环调用
            if(StringUtils.isNotBlank(paymentCode)){
                //保存凭证pic
                for (int i=0;i<request.getBase64Imgs().length;i++) {
                    String picUrl= fileService.uploadBase64Img(request.getBase64Imgs()[i],request.getPicSuffixs()[i]);
                    UserPic userPic=UserPic.builder()
                            .id(IdentifierGenerator.nextId())
                            .picUrl(picUrl)
                            .userId(RequestUtil.getGlobalUser().getUserId())
                            .type(request.getMode())
                            .createTime(LocalDateTime.now()).build();
                    repayRepository.userPicinsertSelective(userPic);
                    //向凭证表添加
                    UserTransactionRepayPic repayPic=UserTransactionRepayPic.builder()
                            .id(IdentifierGenerator.nextId())
                            .evidencePicUrl(picUrl)
                            .transactionRepayId(x.getId())
                            .createTime(LocalDateTime.now())
                            .build();
                    repayRepository.picinsertSelective(repayPic);
                    //修改对用的va码记录 state=处理中
                    repayRepository.updateUserTransacRepayState(x);
                }
                //直接处理成功
                if(resu.getString("code").equals(BizCodeEnum.SUCCESS.getCode())){
                    // TODO 走资金流
                    this.afterRepayment(transaction,x);
                }
                response=JSON.parseObject(result,new TypeReference<PayResponse>() {});
                return response;
            }
        }
        return response;
    }

    /**
     * 还款成功走资金流
     * @param transaction
     * @param x
     */
    public void afterRepayment(UserTransaction transaction, UserTransactionRepay x) {
        repayRepository.afterRepayment(transaction,x);
    }

    /**
     * 根据交易id获取对应的va码信息
     * @param id
     * @return
     */
    public UserTransactionRepay findRepayTrans(Long id) {
       return repayRepository.findRepayTrans(id);
    }

    /**
     * 修改处理失败
     * @param t_id
     * @param b
     */
    public void updateUserepyTranState(String t_id, byte b) {
        repayRepository.updateUserepyTranState(t_id,b);
    }
}