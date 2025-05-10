package hsy.com.shardingsphere.mapper;


import hsy.com.shardingsphere.dto.OrderDetailDTO;
import hsy.com.shardingsphere.model.OmsOrder;
import hsy.com.shardingsphere.model.OmsOrderExample;
import java.util.List;

public interface OmsOrderMapper {


    Long insert(OmsOrder record);


    List<OmsOrder> selectByExample(OmsOrderExample example);

    OrderDetailDTO getOrderDetail(Long id);
    OrderDetailDTO getList();
}