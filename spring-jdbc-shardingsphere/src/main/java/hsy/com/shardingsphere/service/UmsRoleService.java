package hsy.com.shardingsphere.service;



import hsy.com.shardingsphere.user.UmsMenu;

import java.util.List;


public interface UmsRoleService {


    /**
     * 根据管理员ID获取对应菜单
     */
    List<UmsMenu> getMenuList(Long adminId);

}
