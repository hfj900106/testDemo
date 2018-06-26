import com.hzed.BootApplication;
import com.hzed.easyget.application.service.FileService;
import com.hzed.easyget.application.service.LoginService;
import com.hzed.easyget.application.service.product.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.controller.model.PictureCodeResponse;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.utils.PicUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
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
import java.math.BigDecimal;

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

    @Test
    public void redisTest() {
//        redisService.setCache("aaa", "1234", 30L);
        System.out.println(IdentifierGenerator.nextId());
    }

    @Test
    public void uploadPicTest() throws Exception {
        String imgPath = "C:\\Users\\Administrator\\Desktop\\1.png";
        String base64String = PicUtil.picToBase64(imgPath);
//        String aliyunPath = aliyunService.uploadBase64PicStr(base64String, "png");
//        System.out.println(aliyunPath);
        System.out.println(base64String);
    }

    @Test
    public void uploadImg() throws Exception {
        String imgPath = "C:\\Users\\Administrator\\Desktop\\3.png";
        String base64String = PicUtil.picToBase64(imgPath);
        String path = fileService.uploadBase64Img(base64String, "png");
        System.out.println(path);
    }

    @Test
    public void getPicCode() throws Exception{
        OutputStream out = new FileOutputStream(new File("E:\\"+131412+".jpg"));
        PictureCodeResponse codeResponse = loginService.getPictureCode("15910086555");
        out.write(Base64.decodeBase64(codeResponse.getPicture()));
        out.flush();
        out.close();
    }

    @Test
    public void factoryTest() {
        AbstractProduct product = ProductFactory.getProduct(ProductEnum.EasyGet).createProduct(new BigDecimal(60000), 14);
        System.out.println(product.getHeadFee());
        System.out.println(product.getInerest());
    }


    @Test
    public void passwordTest() {
        // java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input=原文 password=123456 algorithm=PBEWithMD5AndDES
        String decrypt = stringEncryptor.encrypt("123456");
        System.out.println(decrypt);
    }

}