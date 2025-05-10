package hsy.com.shardingsphere.mapper;

import hsy.com.shardingsphere.dto.OrderDetailDTO;
import hsy.com.shardingsphere.model.OmsOrder;
import hsy.com.shardingsphere.model.OmsOrderItem;
import hsy.com.shardingsphere.model.OmsOrderItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OmsOrderItemMapper {

    int insert(OmsOrderItem record);


    List<OmsOrderItem> selectByExample(OmsOrderItemExample example);

    int insertList(@Param("list") List<OmsOrderItem> list);


}