package hsy.com.shardingsphere.model;
/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 *
 *
 **/


public class ProductOrderItem {

    private Long id;

    private Long productOrderId;

    private Long productId;

    private String productName;

    private Integer buyNum;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ProductOrderItem{" +
                "id=" + id +
                ", productOrderId=" + productOrderId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", buyNum=" + buyNum +
                ", userId=" + userId +
                '}';
    }
}

