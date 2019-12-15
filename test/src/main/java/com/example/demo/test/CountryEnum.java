package test.src.main.java.com.example.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * @author hfj
 * @date 2019\12\15 0015
 */

@AllArgsConstructor
public enum CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");
    @Setter
    private int index;
    @Setter
    private String name;

    public static String getName(int index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum countryEnum : values) {
            if (index == countryEnum.index) {
                return countryEnum.name;
            }
        }
        return null;
    }
}
