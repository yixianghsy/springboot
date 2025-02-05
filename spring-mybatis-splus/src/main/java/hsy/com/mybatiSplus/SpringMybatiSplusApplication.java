package hsy.com.mybatiSplus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class SpringMybatiSplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMybatiSplusApplication.class, args);
        log.info("druid 监控页面：localhost:8081/druid");
    }

}
