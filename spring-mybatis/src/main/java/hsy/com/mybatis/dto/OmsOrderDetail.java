package hsy.com.mybatis.dto;

import hsy.com.mybatis.entity.OmsOrder;
import hsy.com.mybatis.entity.OmsOrderItem;
import hsy.com.mybatis.entity.OmsOrderOperateHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情信息
 * Created by macro on 2018/10/11.
 */
public class OmsOrderDetail extends OmsOrder {
    @Getter
    @Setter

    private List<OmsOrderItem> orderItemList;
    @Getter
    @Setter
    private List<OmsOrderOperateHistory> historyList;
}
