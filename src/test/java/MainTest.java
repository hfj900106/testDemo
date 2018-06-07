import java.math.BigDecimal;

/**
 * 暂无描述
 *
 * @author guichang
 * @since 2018/4/26
 */

public class MainTest {
    public static void main(String[] args) throws Exception {
        BigDecimal b1 = new BigDecimal(123);
        b1.add(null);
        System.out.println(b1.intValue());
    }

}