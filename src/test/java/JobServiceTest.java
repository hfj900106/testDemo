import com.hzed.BootApplication;
import com.hzed.easyget.application.service.ComService;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.RepayRepository;
import com.hzed.easyget.infrastructure.repository.UserTransactionRepository;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.ext.mapper.BidExtMapper;
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
    private BidExtMapper bidExtMapper;
    @Autowired
    private RepayService repayService;
    @Autowired
    private RepayRepository repayRepository;
    @Autowired
    private ComService comService;

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
        List<UserTransaction> userTranBypayMenid = userTransactionRepository.findUserTranBypayMenid("103710541138501632");
        userTranBypayMenid.stream().forEach(System.out::println);
    }
    @Test
    public  void test03(){
        UserTransaction userTransaction=new UserTransaction();
//        userTransaction.setStatus();
        userTransaction.setUpdateTime(LocalDateTime.now());
        userTransaction.setPaymentId("103710541138501632");
        bidExtMapper.updateUserTranceOverstate(userTransaction);
    }

    /**
     * 全部/部分借款返回页面详情
     */
    @Test
    public void test09(){
        RepayPartRequest request=new RepayPartRequest();
        request.setBidId(104106546290114050L);
        request.setRepayAmount(BigDecimal.valueOf(10000));
        //BigDecimal amount=comService.getBidNoRepay(104106546290114050L, LocalDateTime.now());
        LoanManagResponse managResponse=repayService.findloanManagResponse(BigDecimal.valueOf(20000),request.getBidId(),false);
        System.out.println(managResponse);
    }

    /**
     *查询va码
     */
    @Test
    public  void test05(){
        TransactionVARequest vaRequest=new TransactionVARequest();
        vaRequest.setPayId(1L);
        vaRequest.setMode(ComConsts.OTC);
        TransactionVAResponse response=repayRepository.findVATranc(vaRequest);
        System.out.println(response);
    }
    /**
     *获取va码接口
     */
    @Test
    public  void test06(){
        TransactionVARequest request=new TransactionVARequest();
        request.setPayId(108083038684323840L);
        request.setMode(ComConsts.ATM);
        TransactionVAResponse vaTranc = repayService.findVATranc(request);
        System.out.println(vaTranc);
    }

    /**
     * 还款接口
     */
    @Test
    public  void test08() throws Exception{
        RepaymentRequest request=new RepaymentRequest();
        request.setPayId(108083038684323840L);
        request.setMode(ComConsts.ATM);
        request.setAmount(BigDecimal.valueOf(20000.00));
        PayResponse vaTranc = repayService.repayment(request);
        System.out.println(vaTranc);
    }
}
