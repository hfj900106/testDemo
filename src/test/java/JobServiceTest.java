import com.hzed.BootApplication;
import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.infrastructure.config.PayProp;
import com.hzed.easyget.infrastructure.repository.BidRepository;
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
    private PayProp payProp;

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
        userTransaction.setIsOver(false);
        userTransaction.setUpdateTime(LocalDateTime.now());
        userTransaction.setPaymentId("103710541138501632");
        bidExtMapper.updateUserTranceOverstate(userTransaction);
    }
    @Test
    public  void test04(){
        System.out.println(payProp.toString());
    }
}
