import com.hzed.BootApplication;
import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
public class JobServiceTest {
    @Autowired
    private BidRepository bidRepository;

    @Test
    public void test(){
        LoanTransactionRequest loan=bidRepository.findLoanTransaction(1L);
        System.out.println(loan);
    }
}
