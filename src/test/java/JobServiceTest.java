import com.hzed.BootApplication;
import com.hzed.easyget.application.enums.TransactionRepayEnum;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.LoanManagResponse;
import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.controller.model.TransactionVARequest;
import com.hzed.easyget.controller.model.TransactionVAResponse;
import com.hzed.easyget.infrastructure.config.PayProp;
import com.hzed.easyget.infrastructure.consts.ComConsts;
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
     * 全部借款返回
     */
    @Test
    public  void test04(){
        LoanManagResponse response=repayService.findloanManagResponse(104101327290114048L,true);
        System.out.println(response);
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
        request.setPayId(106630497559781376L);
        request.setMode(ComConsts.OTC);
        TransactionVAResponse vaTranc = repayService.findVATranc(request);
        System.out.println(vaTranc);
    }

    /**
     * 还款失败修改va码记录
     */
    @Test
    public  void test07(){
        repayRepository.updateUserepyTranState("106630497559781377hzed",TransactionRepayEnum.PROCESS_FAIL.getCode().byteValue());
    }
}
