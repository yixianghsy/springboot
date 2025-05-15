package hsy.com.elk.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * <p>
 * App定时任务
 * </p>
 *
 * @author zhengqingya
 * @description
 * @date 2021/8/2 14:01
 */
@Component
@EnableScheduling
public class AppScheduledJobs {
    private final static Logger logger= LoggerFactory.getLogger(AppScheduledJobs.class);
    /**
     * 每5秒执行一次
     *
     * @return void
     * @author zhengqingya
     * @date 2021/8/2 8:10 下午
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void test() {
        logger.debug("==================================================================================");
        logger.error("<<<<<< hsy.com.elk.tools error Start: 【{}】 >>>>>>", LocalDateTime.now());
        logger.warn("<<<<<< hsy.com.elk.tools warn Start: 【{}】 >>>>>>", LocalDateTime.now());
        logger.info("<<<<<< hsy.com.elk.tools info Start: 【{}】 >>>>>>", LocalDateTime.now());
        logger.debug("<<<<<< hsy.com.elk.tools debug Start: 【{}】 >>>>>>", LocalDateTime.now());
    }

}
