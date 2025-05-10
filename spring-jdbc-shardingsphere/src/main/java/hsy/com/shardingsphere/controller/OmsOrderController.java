package hsy.com.shardingsphere.controller;

import com.mall.api.CommonPage;
import com.mall.api.CommonResult;
import hsy.com.shardingsphere.dto.OmsOrderDetail;
import hsy.com.shardingsphere.model.OmsOrder;
import hsy.com.shardingsphere.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 订单管理Controller
 * Created by macro on 2018/10/11.
 */
@Controller
@RequestMapping("/order")
public class OmsOrderController {
    @Autowired
    private OmsOrderService orderService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public void list() {
        List<OmsOrderDetail> list = orderService.getList();
        for (OmsOrderDetail omsOrderDetail :list
        ) {
            System.out.printf("输出结果："+omsOrderDetail);
        }
    }
}
