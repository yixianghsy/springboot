package hsy.com.mysql.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class DataSourceSwitchAOP {

    @Before("@annotation(hsy.com.mysql.datasource.Master) && !@annotation(hsy.com.mysql.datasource.Slave)")
    public void setWriteDataSourceType() {
        DynamicDataSourceContextHolder.setMaster();
    }

    @Before("@annotation(hsy.com.mysql.datasource.Slave) && !@annotation(hsy.com.mysql.datasource.Master)")
    public void setReadDataSourceType() {
        DynamicDataSourceContextHolder.setSlave();
    }

    @After("(@annotation(hsy.com.mysql.datasource.Slave) && !@annotation(hsy.com.mysql.datasource.Master)) || (@annotation(hsy.com.mysql.datasource.Master) && !@annotation(hsy.com.mysql.datasource.Slave))")
    public void clearDataSourceType() {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}

