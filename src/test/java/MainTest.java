import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

/**
 * 暂无描述
 *
 * @author guichang
 * @since 2018/4/26
 */

public class MainTest {
    public static void main(String[] args) throws Exception {

        optionalTest();

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

}