import com.hzed.BootApplication;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
public class BootTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void redisTest() {
//        redisService.setCache("aaa", "1234", 30L);
        System.out.println(IdentifierGenerator.nextId());
    }


}