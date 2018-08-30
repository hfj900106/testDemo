import com.hzed.BootApplication;
import com.hzed.easyget.application.service.FileService;
import com.hzed.easyget.application.service.LoginService;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.ProductService;
import com.hzed.easyget.controller.model.PictureCodeResponse;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.utils.PicUtil;
import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.Bill;
import com.hzed.easyget.persistence.auto.entity.BillLedger;
import org.apache.commons.codec.binary.Base64;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
public class BootTest {

    @Autowired
    private RedisService redisService;
    @Autowired
    private FileService fileService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private StringEncryptor stringEncryptor;
    @Autowired
    private BidRepository bidRepository;

    @Test
    public void redisTest() {
//        redisService.setCache("aaa", "1234", 30L);
        System.out.println(IDGenerator.nextId());
    }

    @Test
    public void uploadPicTest() throws Exception {
        String imgPath = "C:\\imgUpload\\adminPic\\20180804\\7713984226503770112.png";
        String base64String = PicUtil.picToBase64(imgPath);
//        String aliyunPath = aliyunService.uploadBase64PicStr(base64String, "png");
//        System.out.println(aliyunPath);
        System.out.println(base64String);
    }

    @Test
    public void uploadImg() throws Exception {
        String imgPath = "C:\\Users\\Administrator\\Desktop\\jifei.png";
        String base64String = PicUtil.picToBase64(imgPath);
        String path = fileService.uploadBase64Img(base64String, "png");
        System.out.println(path);
    }

    @Test
    public void getPicCode() throws Exception {
        OutputStream out = new FileOutputStream(new File("E:\\" + 131412 + ".jpg"));
        PictureCodeResponse codeResponse = loginService.getPictureCode("15910086555");
        out.write(Base64.decodeBase64(codeResponse.getPicture()));
        out.flush();
        out.close();
    }

    @Test
    public void factoryTest() {
        Bid bid = bidRepository.findById(113107770437672967L);
        ProductService product = ProductFactory.getProduct();
        List<Bill> bills = product.createBills(bid);
        System.out.println(bills);
        List<BillLedger> billLedger = product.createBillLedger(bills, bid.getLoanAmount(), bid.getPeriod());
        System.out.println(billLedger);
    }


    @Test
    public void passwordTest() {
        // java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input=原文 password=123456 algorithm=PBEWithMD5AndDES
        System.out.println(stringEncryptor.encrypt("root"));
        System.out.println(stringEncryptor.encrypt("rootroot"));
    }

}