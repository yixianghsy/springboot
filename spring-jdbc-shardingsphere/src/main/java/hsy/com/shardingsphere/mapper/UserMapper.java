package hsy.com.shardingsphere.mapper;
import hsy.com.shardingsphere.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author zhengqingya
 * @description
 * @date 2021/01/13 10:11
 */
public interface UserMapper {

    /**
     * 列表分页
     *
     * @param page   分页数据
     * @param filter 查询过滤参数
     * @return 查询结果
     * @author zhengqingya
     * @date 2021/01/13 10:11
     */
//    IPage<User> selectDataList(IPage page, @Param("filter") User filter);
//    selectDataList();
    /**
     * 批量插入数据
     *
     * @param list list
     * @return void
     * @author zhengqingya
     * @date 2021/5/28 14:28
     */
    void insertBatch(@Param("list") List<User> list);


    List<User>  select();

}
