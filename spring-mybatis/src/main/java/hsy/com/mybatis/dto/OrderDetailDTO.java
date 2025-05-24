package hsy.com.mybatis.dto;

import hsy.com.mybatis.entity.OmsOrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderDetailDTO implements Serializable {

    private Long id;


    private String receiverProvince;


    private String receiverCity;


    private String receiverRegion;

    private String receiverDetailAddress;


    private BigDecimal payAmount;

    private String orderSn;


    private List<OmsOrderItem> orderItemList;


    private Date createTime;

    private Integer status;

    private Integer normalOrderOvertime;
}
