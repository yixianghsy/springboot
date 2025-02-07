package hsy.com.aop.dto;

import hsy.com.aop.validator.ValidNum;
import lombok.Data;

@Data
public class NumTest {

    @ValidNum(value = 30, message = "数值不能大于30")
    private Integer num;
}