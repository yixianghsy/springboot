package hsy.com.shardingsphere.dto;

import hsy.com.shardingsphere.model.OmsCartItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CartItemStockDTO extends OmsCartItem {

    private Integer stock;
}
