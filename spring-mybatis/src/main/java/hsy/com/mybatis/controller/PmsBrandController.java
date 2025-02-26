package hsy.com.mybatis.controller;


import hsy.com.mybatis.api.CommonPage;
import hsy.com.mybatis.api.CommonResult;
import hsy.com.mybatis.entity.PmsBrand;
import hsy.com.mybatis.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌管理Controller
 * Created by macro on 2018/4/26.
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    /**
     *  品牌数据列表
     *      在商品中进行共用
     *  url:'/brand/list',
     *     method:'get',
     *     params:params
     */
    //没有加PageHelper 插件分页
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult list(
            @RequestParam(value="keyword",defaultValue = "") String keyword,
            @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "5") Integer pageSize)
    {
        System.out.println("/brand/list");
        CommonPage list = brandService.list(keyword, pageNum, pageSize);

        return CommonResult.success(list);
    }
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public List<PmsBrand>getList() {
        return null;
    }

}
