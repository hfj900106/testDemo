import com.alibaba.fastjson.JSON;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.utils.JwtUtil;

/**
 * 暂无描述
 *
 * @author guichang
 * @since 2018/4/26
 */

public class MainTest {
    public static void main(String[] args) throws Exception {
//        GlobalUser user = GlobalUser.builder().userId(123123L).mobile("123213213213").build();
//        String token = JwtUtil.createToken(user);
//        System.out.println(token);

        String userToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjoie1wibW9iaWxlXCI6XCIxMjMyMTMyMTMyMTNcIixcInVzZXJJZFwiOjEyMzEyM30iLCJleHAiOjE1Mjc1NjAwNDJ9.AWHcDWdV0UggM1OnXbAI5thWeG9v3uuiUdNQpAv0yOE";
        GlobalUser user2 = JwtUtil.verify(userToken, GlobalUser.class);
        System.out.println(JSON.toJSONString(user2));

    }

}