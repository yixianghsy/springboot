package hsy.com.shardingsphere.controller;

import com.mall.api.CommonResult;
import hsy.com.shardingsphere.dto.CartItemStockDTO;
import hsy.com.shardingsphere.model.OmsCartItem;
import hsy.com.shardingsphere.service.OmsCartItemService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CartController {
    @Autowired
    private OmsCartItemService cartItemService;
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public List<CartItemStockDTO>  getList(){

        List<CartItemStockDTO> list= cartItemService.getList(1l);

        return list;
    }
    @RequestMapping(value="/getCartList",method = RequestMethod.GET)
    public List<OmsCartItem>  getCartList(){

        List<OmsCartItem> list= cartItemService.getCartList(1l);

        return list;
    }
}
