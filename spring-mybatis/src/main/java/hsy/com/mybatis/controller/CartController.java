package hsy.com.mybatis.controller;

import hsy.com.mybatis.api.CommonResult;
import hsy.com.mybatis.dto.CartItemStockDTO;
import hsy.com.mybatis.service.OmsCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CartController {
    @Autowired
    private OmsCartItemService omsCartItemService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public CommonResult getList(){
        List<Long> itemIds = new ArrayList<>();
        itemIds.add(1131375739308871680l);
        List<CartItemStockDTO> list= omsCartItemService.getCartItemStockByIds(1l,itemIds);
        return CommonResult.success(list);
    }
}
