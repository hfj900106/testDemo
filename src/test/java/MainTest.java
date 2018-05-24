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
       /* GlobalUser user = GlobalUser.builder().userId(97890385497825280L).mobile("13844556677").build();
        String token = JwtUtil.createToken(user);
        System.out.println(token);*/

        String userToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjoie1wibW9iaWxlXCI6XCIxMzg0NDU1NjY3N1wiLFwidXNlcklkXCI6OTc4OTAzODU0OTc4MjUyODB9IiwiZXhwIjoxNTI3NjUxNTgyfQ.QOxz1ywoXJA6Dqx-ordXGri1bL17EvwL9I0JlUGEwLc";
        GlobalUser user2 = JwtUtil.verify(userToken, GlobalUser.class);
        System.out.println(JSON.toJSONString(user2));

    }

}