import com.hzed.BootApplication;
import com.hzed.easyget.application.service.FileService;
import com.hzed.easyget.application.service.LoginService;
import com.hzed.easyget.controller.model.PictureCodeResponse;
import com.hzed.easyget.infrastructure.config.aliyun.AliyunService;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.utils.PicUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
public class BootTest {

    @Autowired
    private RedisService redisService;
    @Autowired
    private AliyunService aliyunService;
    @Autowired
    private FileService fileService;
    @Autowired
    private LoginService loginService;

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

}