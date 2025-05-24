package hsy.com.shardingsphere;


import hsy.com.shardingsphere.dto.OmsOrderDetail;
import hsy.com.shardingsphere.dto.OrderDetailDTO;
import hsy.com.shardingsphere.model.OmsOrder;
import hsy.com.shardingsphere.service.OmsOrderService;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.util.*;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringJdbcShardingsphereApplication.class)
@Slf4j
public class DbTest {

    @Autowired
    private OmsOrderService omsOrderService;

    @Test
    public void getOrder() {
        List<OmsOrderDetail> list = omsOrderService.getList();
        for (OmsOrderDetail omsOrderDetail :list
             ) {
            System.out.printf("输出结果："+omsOrderDetail);
        }
    }
    @Test
    public void insertOrder() {
        OmsOrder order = new OmsOrder();
//        order.setId(12L);
        order.setMemberId(1L);
        order.setCouponId(2L);
        order.setOrderSn("201809150101000001");
        order.setCommentTime(new Date());
        order.setMemberUsername("test");
        order.setTotalAmount(BigDecimal.valueOf(18732.00));
        order.setPayAmount(BigDecimal.valueOf(16377.75));
        order.setFreightAmount(BigDecimal.valueOf(20.00));
        order.setPromotionAmount(BigDecimal.valueOf(2344.25));
        order.setIntegrationAmount(BigDecimal.valueOf(0.00));
        order.setCouponAmount(BigDecimal.valueOf(10.00));
        order.setDiscountAmount(BigDecimal.valueOf(10.00));
        order.setPayType(0);
        order.setSourceType(1);
        order.setStatus(0);
        order.setOrderType(0);
        order.setDeliveryCompany("顺丰快递");
        order.setDeliverySn("201707196398346");
        order.setAutoConfirmDay(15);
        order.setIntegration(13284);
        order.setGrowth(13284);
        order.setPromotionInfo("单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠");
        order.setReceiverName("大梨");
        order.setReceiverPhone("18033441849");
        order.setReceiverPostCode("江苏省");
        order.setReceiverCity("常州市");
        order.setReceiverRegion("天宁区");
        order.setReceiverDetailAddress("东晓街道");
        order.setNote("xxx");
        order.setConfirmStatus(0);
        order.setDeleteStatus(0);
        order.setUseIntegration(10000);
        order.setPaymentTime(new Date());
        order.setDeliveryTime(new Date());
        order.setReceiveTime(new Date());
        order.setModifyTime(new Date());
        omsOrderService.insert(order);

        System.out.println(order.getId());
    }


}
