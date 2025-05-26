package hsy.com.shardingsphere;


import hsy.com.shardingsphere.dto.OmsOrderDetail;
import hsy.com.shardingsphere.dto.OrderDetailDTO;
import hsy.com.shardingsphere.mapper.OmsOrderItemMapper;
import hsy.com.shardingsphere.mapper.OmsOrderMapper;
import hsy.com.shardingsphere.model.OmsOrder;
import hsy.com.shardingsphere.model.OmsOrderExample;
import hsy.com.shardingsphere.model.OmsOrderItem;
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
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;
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
        for (int i = 0; i < 10; i++) {
        OmsOrder order = new OmsOrder();
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
        List<OmsOrderItem> list = new ArrayList<>();
        OmsOrderItem omsOrderItem = new OmsOrderItem();
        omsOrderItem.setOrderId(order.getId());
        omsOrderItem.setOrderSn("201809150101000001");
        omsOrderItem.setProductId(26L);
        omsOrderItem.setProductPic("shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg");
        omsOrderItem.setProductName("华为 HUAWEI P20");
        omsOrderItem.setProductBrand("华为");
        omsOrderItem.setProductSn("6946605");
        omsOrderItem.setProductPrice(BigDecimal.valueOf(3788.00));
        omsOrderItem.setProductQuantity(1);
        omsOrderItem.setProductSkuId(90L);
        omsOrderItem.setProductSkuCode("201806070026001");
        omsOrderItem.setProductCategoryId(19l);
        omsOrderItem.setSp1("金色");
        omsOrderItem.setSp2("16G");
        omsOrderItem.setSp3(null);
        omsOrderItem.setProductName("单品促销");
        omsOrderItem.setPromotionAmount(BigDecimal.valueOf(200.00));
        omsOrderItem.setCouponAmount(BigDecimal.valueOf(2.02));
        list.add(omsOrderItem);
        omsOrderItemMapper.insertList(list);
        }
    }
    @Test
    public  void  selectByExample(){
        OmsOrderExample example = new OmsOrderExample();
        OmsOrderExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(1133194136472522752l);
        List<OmsOrder> omsOrders = orderMapper.selectByExample(example);
        for (OmsOrder order:omsOrders
             ) {
            System.out.println(order);
        }
    }
}
