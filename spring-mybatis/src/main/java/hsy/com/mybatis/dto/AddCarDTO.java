package hsy.com.mybatis.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class AddCarDTO implements Serializable {
    /**
     *
     *           productId: this.id,
     *           productSkuId: this.skuId,
     *           quantity: 1,
     */
    private Long productId;
    private Long productSkuId;
    private Integer quantity;
    private Long memberId;
    private String memberNickname;
    private BigDecimal price;
    private String sp1;
    private String sp2;
    private String sp3;
    private String pic;
    private String skuCode;
    private String productName;
    private String productBrand;
    private String productSn;
    private String productSubTitle;
    private Long productCategoryId;
}
