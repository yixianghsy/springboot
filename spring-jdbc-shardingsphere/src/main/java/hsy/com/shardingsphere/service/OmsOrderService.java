package hsy.com.shardingsphere.service;


import hsy.com.shardingsphere.dto.OmsOrderDetail;
import hsy.com.shardingsphere.dto.OrderDetailDTO;
import hsy.com.shardingsphere.model.OmsOrder;

import java.util.List;

/**
 * 订单管理Service
 * Created by macro on 2018/10/11.
 */
public interface OmsOrderService {
    List<OmsOrderDetail> getList();
    void  insert(OmsOrder omsOrder);


}
