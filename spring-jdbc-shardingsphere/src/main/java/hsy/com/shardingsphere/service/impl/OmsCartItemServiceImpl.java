package hsy.com.shardingsphere.service.impl;

import hsy.com.shardingsphere.dto.AddCarDTO;
import hsy.com.shardingsphere.dto.CartItemStockDTO;
import hsy.com.shardingsphere.mapper.OmsCartItemMapper;
import hsy.com.shardingsphere.model.OmsCartItem;
import hsy.com.shardingsphere.model.OmsCartItemExample;
import hsy.com.shardingsphere.service.OmsCartItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OmsCartItemServiceImpl implements OmsCartItemService {
    @Autowired
    private OmsCartItemMapper cartItemMapper;
    @Override
    public int add(OmsCartItem cartItem) {
        return 0;
    }

    @Override
    public Boolean add(AddCarDTO addCarDTO) {
        return null;
    }

    @Override
    public Long cartItemCount() {
        return null;
    }

    @Override
    public List<OmsCartItem> list(Long memberId) {
        return null;
    }

    @Override
    public Boolean updateQuantity(Long id, Integer quantity) {
        return null;
    }

    @Override
    public Boolean delete(List<Long> ids) {
        return null;
    }

    @Override
    public int updateAttr(OmsCartItem cartItem) {
        return 0;
    }

    @Override
    public int clear(Long memberId) {
        return 0;
    }

    @Override
    public Integer getCarProdutSum(Long memberId) {
        return null;
    }

    @Override
    public List<OmsCartItem> listByIds(List<Long> ids) {
        return null;
    }

    @Override
    public List<CartItemStockDTO> getList(Long memberId) {
        OmsCartItemExample example = new OmsCartItemExample();
        OmsCartItemExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(1l).andDeleteStatusEqualTo(0);
        List<OmsCartItem> list = cartItemMapper.selectByExample(example);
        List<CartItemStockDTO> cartItemStockDTO =new ArrayList<CartItemStockDTO>();
        for (OmsCartItem omsCartItem: list) {
            CartItemStockDTO cartItemStockDTO1 =new CartItemStockDTO();
            BeanUtils.copyProperties(omsCartItem,cartItemStockDTO1);
            cartItemStockDTO.add(cartItemStockDTO1);
        }
        return cartItemStockDTO;
    }

    @Override
    public List<CartItemStockDTO> getCartItemStockByIds(Long id, List<Long> itemIds) {
        return null;
    }

    @Override
    public void removeByIds(List<Long> ids) {

    }

    @Override
    public List<OmsCartItem> getCartList(Long memberId) {
        OmsCartItemExample example = new OmsCartItemExample();
        OmsCartItemExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(1l).andDeleteStatusEqualTo(0);
        List<OmsCartItem> omsCartItems = cartItemMapper.selectByExample(example);
        if (null == omsCartItems || omsCartItems.size() ==0 ){
            System.out.println("数据喂空");
            return null;
        }else {
            for (OmsCartItem cart: omsCartItems
            ) {
                System.out.println(cart);
            }
        }
        return omsCartItems;
    }
}
