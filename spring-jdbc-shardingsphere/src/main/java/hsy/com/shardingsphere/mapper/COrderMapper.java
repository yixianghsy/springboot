package hsy.com.shardingsphere.mapper;



import hsy.com.shardingsphere.user.COrder;

import java.util.List;

public interface COrderMapper {
    List<COrder> selectAll();

    COrder selectById(Long id);
}
