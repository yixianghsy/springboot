package hsy.com.shardingsphere.service;


import hsy.com.shardingsphere.model.OmsOrderSetting;
import hsy.com.shardingsphere.model.OmsOrderSettingExample;

import java.util.List;

/**
 * 订单设置管理Service
 * Created by macro on 2018/10/16.
 */
public interface OmsOrderSettingService {
    /**
     * 获取指定订单设置
     */
    OmsOrderSetting getItem(Long id);

    /**
     * 修改指定订单设置
     */
    int update(Long id, OmsOrderSetting orderSetting);

    OmsOrderSetting getById(long l);

    List<OmsOrderSetting > selectByExample();
}
