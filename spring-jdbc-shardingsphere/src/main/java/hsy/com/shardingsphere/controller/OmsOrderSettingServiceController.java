package hsy.com.shardingsphere.controller;

import hsy.com.shardingsphere.dto.OmsOrderDetail;
import hsy.com.shardingsphere.model.OmsOrderSetting;
import hsy.com.shardingsphere.service.OmsOrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/orderSettingService")
public class OmsOrderSettingServiceController {
    @Autowired
    private OmsOrderSettingService orderSettingService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public void list() {
        List<OmsOrderSetting> omsOrderSettings = orderSettingService.selectByExample();
        for (OmsOrderSetting omsOrderDetail :omsOrderSettings
        ) {
            System.out.printf("输出结果："+omsOrderDetail);
        }
    }
}
