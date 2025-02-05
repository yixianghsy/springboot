package hsy.com.shardingsphere.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import hsy.com.shardingsphere.confg.datasource.dynamic.DataSourceConfig;
import hsy.com.shardingsphere.mapper.UmsRoleDao;
import hsy.com.shardingsphere.service.UmsRoleService;
import hsy.com.shardingsphere.user.UmsMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台角色管理Service实现类
 * Created by macro on 2018/9/30.
 */

@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Autowired
    private UmsRoleDao roleDao;
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    @DS(DataSourceConfig.SHARDING_DATA_SOURCE_NAME)
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }


}