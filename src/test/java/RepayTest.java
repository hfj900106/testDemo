import com.hzed.BootApplication;
import com.hzed.easyget.application.enums.RepayMentEnum;
import com.hzed.easyget.application.service.BluePayService;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.application.service.SmsService;
import com.hzed.easyget.controller.model.PaymentIdResponse;
import com.hzed.easyget.controller.model.RepaymentCompleRequest;
import com.hzed.easyget.controller.model.TransactionVaRequest;
import com.hzed.easyget.controller.model.TransactionVaResponse;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
public class RepayTest {
    @Autowired
    private RepayService repayService;
    @Autowired
    private BluePayService bluePayService;
    @Autowired
    private SmsService smsService;


    /**
     * 全部结清
     */
    @Test
    public void partialRepayment(){
        PaymentIdResponse response = repayService.findloanManagResponse(BigDecimal.valueOf(100_000), 110941225972277248L, false);
        System.out.println("================================>"+response);
    }
    /**
     * 获取还款码接口
     */
    @Test
    public void vaInfoDetail(){
        TransactionVaRequest request=new TransactionVaRequest();
        request.setAmount(BigDecimal.valueOf(100_000));
        request.setBidId(110941225972277248L);
        request.setFlag(false);
        request.setMode(RepayMentEnum.OTC.getMode());
        TransactionVaResponse vaTranc = repayService.findVaTranc(request);
        System.out.println(vaTranc);

    }

    /**
     * 测试还款接口
     */
    @Test
    public void testRepayment() {
        RepaymentCompleRequest request=new RepaymentCompleRequest();
        request.setPayType(RepayMentEnum.OTC.getBlue());
        request.setTransactionId("114194023249747968hzed");
        request.setPaymentCode("8888859300920641");
        request.setPrice(BigDecimal.valueOf(10000));
        request.setCardNo("21934189228383587872");
        request.setMsisdn("8615926633889");
        request.setRequestNo(String.valueOf(IDGenerator.nextId()));
        PayResponse response = bluePayService.testRepayment(request);
        System.out.println(response);
    }

    /**
     * 测试短信通知
     */
    @Test
    public void smsTenst(){
        smsService.repaymentNotice(BigDecimal.valueOf(100000),"15926633889",null);
    }
}
