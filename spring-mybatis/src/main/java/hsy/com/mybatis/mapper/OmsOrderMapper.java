package hsy.com.mybatis.mapper;


import hsy.com.mybatis.dto.OmsOrderDetail;
import hsy.com.mybatis.dto.OrderDetailDTO;
import hsy.com.mybatis.entity.OmsOrder;
import hsy.com.mybatis.entity.OmsOrderExample;

import java.util.List;

public interface OmsOrderMapper {


    Long insert(OmsOrder record);


    List<OmsOrder> selectByExample(OmsOrderExample example);

    OrderDetailDTO getOrderDetail(Long id);

    List<OmsOrderDetail> findMemberOrderList();

}