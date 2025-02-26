package hsy.com.mybatis.entity;
public class Donation {

    /**
     * id int
     * name string
     * user_id int
     * detail string
     * status  int
     * total float
     * current float
     */
    private  Integer  id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    private  String   name;
    private  Integer user_id;
    private  String detail;
    private   Integer  status;
    private  float total;
    private  float current;

}
