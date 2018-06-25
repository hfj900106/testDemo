import java.time.LocalDateTime;
import java.time.ZoneId;

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

        long l = System.currentTimeMillis();
        System.out.println(l);
        long l1 = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(l1);


    }

}