import com.demo.hzed.BootApplication;
import com.demo.hzed.persistence.auto.entity.Bid;
import com.demo.hzed.persistence.auto.entity.Bill;
import com.demo.hzed.persistence.auto.mapper.BidMapper;
import com.demo.hzed.persistence.auto.mapper.BillMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
public class BootTest {

    @Autowired
    private BidMapper bidMapper;
    @Autowired
    private BillMapper billMapper;

    @Test
    public void testQueryCompensatory() throws Exception {
        String path = "C:\\Users\\Administrator\\Desktop\\还款失败数据.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {

            String[] records = line.split("\t");
            print(records[0], records[1], records[4]);

        }

        br.close();
    }

    public void print(String billId, String inAccount, String amount) {
        String sql1 = "INSERT INTO t_transaction_advance(id,bill_id,out_account,in_account,amount,corpus,interest,bid_no,busi_type,remark) ";
        Bill bill = billMapper.selectByPrimaryKey(Long.parseLong(billId));
        Bid bid = bidMapper.selectByPrimaryKey(bill.getBidId());

        String sql2 = "VALUES ('" + Math.abs(new Random().nextLong()) + "','" + billId + "','13632121428','" + inAccount + "','" + amount + "','"+amount+"','0','" + bid.getBidNo() + "','06','" + bid.getId() + "第" + bill.getPeriods() + "期垫付本金(补偿)')";
        System.out.println(sql1 + sql2);
    }


}