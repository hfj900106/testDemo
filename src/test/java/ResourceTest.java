import com.hzed.BootApplication;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

/**
 * @author Waylon
 * @date 2018/6/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
public class ResourceTest {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ApplicationContext ctx;

    @Test
    public void test() {
        String key = "bizcode." + BizCodeEnum.ILLEGAL_REQUEST.getCode();

        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(1000);

                Locale china = Locale.CHINA;
                Locale en = new Locale("en", "US");
                String chnMsg = messageSource.getMessage(key, null, china);

                System.out.println("第" + i + "次：" + chnMsg);
                String enMsg = messageSource.getMessage(key, null, en);
                System.out.println("第" + i + "次：" + enMsg);
            }
        } catch (NoSuchMessageException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
