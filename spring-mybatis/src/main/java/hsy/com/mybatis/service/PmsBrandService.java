package hsy.com.mybatis.service;

import hsy.com.mybatis.api.CommonPage;
import hsy.com.mybatis.api.Page;
import hsy.com.mybatis.entity.PmsBrand;
import hsy.com.mybatis.mapper.PmsBrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 商品品牌管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;
    /**
     *
     *  品牌数据列表
     * @param keyword 商品名称
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return
     */
    //没有加PageHelper 插件分页
    public CommonPage list(String keyword, Integer pageNum, Integer pageSize) {
        // 查询分页总数
        int totalRecords = brandMapper.selectCount();
        Page page = new Page(pageNum,totalRecords,pageSize);
        //查询分页数据
        List<PmsBrand> pmsBrands = brandMapper.selectCaseDetial(keyword,page.getStartIndex(),page.getPageSize());
        page.setListRecords(pmsBrands);
        return CommonPage.restPage(page);
    }

}
