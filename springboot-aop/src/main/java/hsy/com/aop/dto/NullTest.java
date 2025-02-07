package hsy.com.aop.dto;

import hsy.com.aop.validator.ValidNull;
import hsy.com.aop.validator.ValidNum;
import lombok.Data;

@Data
public class NullTest {

    @ValidNull
    private String name;
}