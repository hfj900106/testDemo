import java.util.Locale;

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

        Locale[] ls = Locale.getAvailableLocales();

        for (Locale locale:ls) {
            System.out.println("locale :"+locale);
        }

    }

}