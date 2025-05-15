package hsy.com.elk.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class TestController {

        private final static Logger logger= LoggerFactory.getLogger(TestController.class);
//    @RequestMapping("/myTest")
//    public void test(){
//        logger.info("日志开始"+System.currentTimeMillis());
//        logger.info("日志结束"+System.currentTimeMillis());
//    }
//    /**
//     * 测试输出log的访问方法
//     */
//    @GetMapping("/myTestLog")
//    public String testLog() {
//        logger.error("测试输出一个日志");
//        return "success";
//    }
//http://localhost:8088/user/get?userId=001
    @GetMapping("get")
    public void getuserInfo(String userId) {
        logger.info("getuserInfo userId:【{}】", userId);
    }

    @RequestMapping("/myTest")
    public void test(String userId) {
        logger.info("getuserInfo userId:【{}】", userId);
    }
}



