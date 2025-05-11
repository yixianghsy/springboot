package hsy.com.shardingsphere.mapper;


import hsy.com.shardingsphere.dto.OmsOrderDetail;
import hsy.com.shardingsphere.dto.OrderDetailDTO;
import hsy.com.shardingsphere.model.OmsOrder;
import hsy.com.shardingsphere.model.OmsOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OmsOrderMapper {


    Long insert(OmsOrder record);


    List<OmsOrder> selectByExample(OmsOrderExample example);

    OrderDetailDTO getOrderDetail(Long id);

    List<OmsOrderDetail> findMemberOrderList();

}