import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hzed.easyget.infrastructure.model.AppVersionModel;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Optional;

/**
 * 暂无描述
 *
 * @author guichang
 * @since 2018/4/26
 */

@Slf4j
public class MainTest {
    public static void main(String[] args) {


//        optionalTest();
//        jsonTest();
//        stringCompareTest();
//        tokenTest();

        mapIfAbsentTest();


    }

    public static void jsonTest() {
        String json = "{\"update_url\":\"123213\",\"force_update\":\"1\",\"minimum_version\": \"5\",\"is_bom\":\"true\"}";
        AppVersionModel appVersionModel = JSONObject.parseObject(json, AppVersionModel.class);
        System.out.println(appVersionModel);

    }

    public static void stringCompareTest() {
        String s1 = "1.0.0";
        String s2 = "1.0.2";
        String s3 = "1.1.2";
        String s4 = "1.4.1";
        System.out.println(s2.compareTo(s1));
        System.out.println(s3.compareTo(s4));
        System.out.println(s4.compareTo(s1));
        System.out.println(s2.compareTo(s3));
    }

    public static void optionalTest() {
        Optional<Integer> op1 = Optional.of(1);
        op1.ifPresent(t -> System.out.println("value is " + t));
        op1.ifPresent(System.out::println);

        Optional<User> user = Optional.of(new User(null));

        String result = user.map(u -> u.getName()).map(name -> name.toUpperCase()).orElse("null string");
        System.out.println(result);

    }


    @Data
    @AllArgsConstructor
    static class User {
        private String name;
    }

    public static void tokenTest() {
//        GlobalUser before = new GlobalUser(123L, "18878787876");
//        String token = JwtUtil.createToken(before);
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjoie1wibW9iaWxlXCI6XCIwMTg3MjE1NTExMTFcIixcInVzZXJJZFwiOjEyMTA4NDczNzgwODExMzY2NH0iLCJleHAiOjE1MzMxMDUzMDV9.LWva1WL0lCbzwhifR5ZMI9IAUZ-CJXmXTe1MI0zYTT8";
        GlobalUser after = JwtUtil.verify(token, GlobalUser.class);
        System.out.println(after);
    }

    public static void mapIfAbsentTest() {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("123", "456");

//        log.info("computeIfAbsent");
//        map.computeIfAbsent("123", key -> {
//            System.out.println(key);
//            String value = key;
//            return value;
//        });
//        System.out.println(map);
        log.info("computeIfPresent");
        map.computeIfPresent("123", (key, value) -> {
            System.out.println(key + "," + value);
            String value2 = key;
            return value2;
        });
        System.out.println(map);
    }


}