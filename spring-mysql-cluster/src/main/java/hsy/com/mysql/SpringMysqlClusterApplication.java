package hsy.com.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SpringMysqlClusterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMysqlClusterApplication.class, args);
    }

}
