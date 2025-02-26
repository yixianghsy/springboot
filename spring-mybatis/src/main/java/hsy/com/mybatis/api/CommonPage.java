package hsy.com.mybatis.api;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据封装类
 * Created by macro on 2019/4/19.
 */
public class CommonPage<T> implements Serializable {
    //当前页
    private Integer pageNum;
    // 一页多少条数据
    private Integer pageSize;
    // 总页数
    private Integer totalPage;
    // 总数据数量
    private Long total;
    // 当前页数据
    private List<T> list;
    public static <T> CommonPage<T> restPage(Page<T> page) {
        CommonPage<T> result = new CommonPage<>();
        // 当前页
        result.setPageNum(page.getCurrentPageNum());
        // 一页多少条数据
        result.setPageSize(page.getPageSize());
        // 总数据数量
        result.setTotal((long) page.getTotalRecords());
        // 总页数
        result.setTotalPage(page.getTotalPage());
        // 当前页数据
        result.setList(page.getListRecords());
        System.out.println("//第几页:"+page.getCurrentPageNum());
        System.out.println("//没页多少数据："+ page.getPageSize());
        System.out.println("//总共几页:"+page.getTotalPage());
        System.out.println("//总共多少条数据:"+page.getTotalRecords());
        System.out.println("//结果集:"+page.getListRecords().get(0).toString());

        return result;
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
