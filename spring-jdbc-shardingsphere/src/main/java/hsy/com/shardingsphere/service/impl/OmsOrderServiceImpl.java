package hsy.com.shardingsphere.service.impl;

import hsy.com.shardingsphere.dto.OmsOrderDetail;
import hsy.com.shardingsphere.dto.OrderDetailDTO;
import hsy.com.shardingsphere.mapper.OmsOrderMapper;
import hsy.com.shardingsphere.model.OmsOrder;
import hsy.com.shardingsphere.service.OmsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 订单管理Service实现类
 * Created by macro on 2018/10/11.
 */
@Service
@Slf4j
public class OmsOrderServiceImpl implements OmsOrderService {
    @Autowired
    private OmsOrderMapper orderMapper;
    @Override
    public List<OmsOrderDetail> getList() {
        List<OmsOrderDetail> list = orderMapper.findMemberOrderList();
        return list;
    }

    @Override
    public void insert(OmsOrder omsOrder) {
        orderMapper.insert(omsOrder);
    }
}
