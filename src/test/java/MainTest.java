import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hzed.easyget.infrastructure.utils.ComUtil;

import java.util.Map;

/**
 * 暂无描述
 *
 * @author guichang
 * @since 2018/4/26
 */

public class MainTest {
    public static void main(String[] args) throws Exception {

//        String path = "http://localhost:8150/hzed/easy-get/login/loginByCode";
//        System.out.println(path.substring(0, path.indexOf("/hzed")+"/hzed".length()));

//        Locale[] ls = Locale.getAvailableLocales();
//
//        for (Locale locale:ls) {
//            System.out.println("locale :"+locale);
//        }


//        String imgPath = "C:\\Users\\Administrator\\Desktop\\3.png";
//        String base64String = PicUtil.picToBase64(imgPath);
//        System.out.println(base64String);

//        long l = System.currentTimeMillis();
//        System.out.println(l);
//        long l1 = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//        System.out.println(l1);

        Map map = Maps.newHashMap();
        map.put("name", "guichang");
        map.put("age",123);
        map.put("img","jhefswjkfjkwekhfwkejfkhwekhwfkhewhf1111qewqewq11111");

        System.out.println(JSON.toJSONString(map));
        System.out.println(ComUtil.subJsonString(JSON.toJSONString(map), 10));

        System.out.println(ComUtil.subJsonString("sfsadf3424324324324324324sd", 10));


    }

}