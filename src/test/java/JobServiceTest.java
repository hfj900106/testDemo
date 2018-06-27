import com.hzed.BootApplication;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.RepayRepository;
import com.hzed.easyget.infrastructure.repository.UserTransactionRepository;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.entity.UserTransactionRepay;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionRepayExample;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionRepayMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
public class JobServiceTest {
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;
    @Autowired
    private RepayService repayService;
    @Autowired
    private RepayRepository repayRepository;
    @Autowired
    private UserTransactionRepayMapper userTransactionRepayMapper;

    @Test
    public void test(){
        LoanTransactionRequest loan=bidRepository.findLoanTransaction(1L);
        System.out.println(loan);
    }
    @Test
    public  void test01(){
        String paternStr = "^cashout$";
        Pattern pattern = Pattern.compile(paternStr);
        Matcher matcher = pattern.matcher("cashout");
        System.out.println(matcher.matches());
    }
    @Test
    public  void test02(){
         UserTransaction userTranBypayMenid = userTransactionRepository.findUserTranByPaymentId("103710541138501632");
    }
    /**
     * 全部/部分借款返回页面详情
     */
    @Test
    public void test09(){
        RepayPartRequest request=new RepayPartRequest();
        request.setBidId(104094428696027136L);
        PaymentIdResponse idResponse=repayService.findloanManagResponse(BigDecimal.valueOf(20000),request.getBidId(),false);
        System.out.println(idResponse);
    }

    /**
     *查询va码
     */
    @Test
    public  void test05(){
        UserTransactionRepay response=repayRepository.findVaTranc(108432325318418432L,ComConsts.OTC);
        System.out.println(response);
    }
    /**
     *获取va码接口
     */
    @Test
    public  void test06(){
        TransactionVARequest request=new TransactionVARequest();
        request.setPayId(108428379602427904L);
        request.setMode(ComConsts.ATM);
        TransactionVAResponse vaTranc = repayService.findVaTranc(request);
        System.out.println(vaTranc);
    }

    /**
     * 还款接口
     */
    @Test
    public  void test08() throws Exception{
        RepaymentRequest request=new RepaymentRequest();
        request.setPayId(108428379602427904L);
        request.setMode(ComConsts.ATM);
        request.setAmount(BigDecimal.valueOf(20000.00));
        PayResponse vaTranc = repayService.testRepayment(request);
        System.out.println(vaTranc);
    }
    @Test
    public void test10(){
        UserTransactionRepayExample repayExample=new UserTransactionRepayExample();
        repayExample.createCriteria()
                .andTransactionIdEqualTo(108432517484650496L)
                .example().orderBy("va_create_time desc").limit(1);
        List<UserTransactionRepay> repay= userTransactionRepayMapper.selectByExample(repayExample);
        System.out.println("----------------------->"+repay);
    }
    @Test
    public void test11(){
       int repay=LocalDateTime.now().compareTo(LocalDateTime.now().minusHours(4));
        System.out.println("----------------------->"+LocalDateTime.now().minusHours(4));
    }

    /**
     * 查看还款信息
     */
    @Test
    public void test12(){
        LoanManagResponse response=repayService.loanManagInfo(108476457604751361L);
        System.out.println("----------------------->"+response);
    }

    /**
     * 测试图片上传
     * @throws Exception
     */
    @Test
    public void test13() throws Exception {
        RepaymentRequest request =new RepaymentRequest();
        request.setAmount(BigDecimal.valueOf(10000));
        request.setMode(ComConsts.ATM);
        request.setPayId(109162069676662784L);
        request.setBase64Imgs(new String[]{"data:image/png;base64,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"});
        request.setPicSuffixs(new String[]{"png"});
        PayResponse response=repayService.repayment(request);
        System.out.println("----------------------->"+response);
    }
}
