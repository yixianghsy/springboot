package hsy.com.mybatis.mapper;

import hsy.com.mybatis.entity.OmsOrderItem;
import hsy.com.mybatis.entity.OmsOrderItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OmsOrderItemMapper {

    int insert(OmsOrderItem record);


    List<OmsOrderItem> selectByExample(OmsOrderItemExample example);

    int insertList(@Param("list") List<OmsOrderItem> list);


}