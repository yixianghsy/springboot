package hsy.com.mybatis.service.ipml;

import hsy.com.mybatis.dto.AddCarDTO;
import hsy.com.mybatis.dto.CartItemStockDTO;
import hsy.com.mybatis.entity.OmsCartItem;
import hsy.com.mybatis.mapper.OmsCartItemMapper;
import hsy.com.mybatis.service.OmsCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OmsCartItemServiceImpl  implements OmsCartItemService {

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
        return null;
    }

    @Override
    public List<CartItemStockDTO> getCartItemStockByIds(Long id, List<Long> itemIds) {
        return cartItemMapper.getCartItemStockByIds(id, itemIds);
    }

    @Override
    public void removeByIds(List<Long> ids) {

    }

    @Override
    public List<OmsCartItem> getCartList(Long memberId) {
        return null;
    }
}
