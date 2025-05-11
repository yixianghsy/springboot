package hsy.com.shardingsphere.service.impl;

import hsy.com.shardingsphere.mapper.OmsOrderSettingMapper;
import hsy.com.shardingsphere.model.OmsOrderSetting;
import hsy.com.shardingsphere.model.OmsOrderSettingExample;
import hsy.com.shardingsphere.service.OmsOrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单设置管理Service实现类
 * Created by macro on 2018/10/16.
 */
@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {
    @Autowired
    private OmsOrderSettingMapper orderSettingMapper;
    @Override
    public OmsOrderSetting getItem(Long id) {
        return null;
    }

    @Override
    public int update(Long id, OmsOrderSetting orderSetting) {
        return 0;
    }

    @Override
    public OmsOrderSetting getById(long l) {
        return null;
    }

    @Override
    public List<OmsOrderSetting> selectByExample() {
        OmsOrderSettingExample example1 = new OmsOrderSettingExample();
        return orderSettingMapper.selectByExample(example1);
    }
}
