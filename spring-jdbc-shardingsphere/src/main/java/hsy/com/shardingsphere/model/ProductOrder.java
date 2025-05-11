package hsy.com.shardingsphere.model;
import java.io.Serializable;
import java.util.Date;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/

public class ProductOrder implements Serializable {

    //@TableId(value = "id",type = IdType.AUTO)
    //@TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    private String outTradeNo;

    private String state;

    private Date createTime;

    private Double payAmount;

    private String nickname;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                ", payAmount=" + payAmount +
                ", nickname='" + nickname + '\'' +
                ", userId=" + userId +
                '}';
    }
}
