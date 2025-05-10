package hsy.com.shardingsphere.service;


import hsy.com.shardingsphere.dto.OrderDetailDTO;

/**
 * 订单管理Service
 * Created by macro on 2018/10/11.
 */
public interface OmsOrderService {
    OrderDetailDTO getList();
}
