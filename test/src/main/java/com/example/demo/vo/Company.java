package com.example.demo.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author hfj
 * @date 2019/3/30
 */

@Data
@Component
public class Company {
    private String name = "工厂";
    private int employees = 100;

}
