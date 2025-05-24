package hsy.com.shardingsphere.service;

import hsy.com.shardingsphere.dto.AddCarDTO;
import hsy.com.shardingsphere.dto.CartItemStockDTO;
import hsy.com.shardingsphere.model.OmsCartItem;

import java.util.List;

/**
 * 购物车管理Service
 * Created on 2018/8/2.
 */
public interface OmsCartItemService {
    /**
     * 查询购物车中是否包含该商品，有增加数量，无添加到购物车
     */

    int add(OmsCartItem cartItem);


    Boolean add(AddCarDTO addCarDTO);
    /**
     * 购物车产品数量
     */
    Long cartItemCount();

    /**
     * 根据会员编号获取购物车列表
     */
    List<OmsCartItem> list(Long memberId);

    /**
     * 获取被选择的包含促销活动信息的购物车列表
     * add by yangguo
     * @param memberId
     * @param itemIds
     * @return
     */
//    List<CartPromotionItem> listSelectedPromotion(Long memberId, List<Long> itemIds) throws BusinessException;

    /**
     * 获取包含促销活动信息的购物车列表
     */
//    List<CartPromotionItem> listPromotion(Long memberId);

    /**
     * 修改某个购物车商品的数量
     */
    Boolean updateQuantity(Long id, Integer quantity);

    /**
     * 批量删除购物车中的商品
     */
    Boolean delete(List<Long> ids);

    /**
     *获取购物车中用于选择商品规格的商品信息
     */
//    CartProduct getCartProduct(Long productId);

    /**
     * 修改购物车中商品的规格
     */

    int updateAttr(OmsCartItem cartItem);

    /**
     * 清空购物车
     */
    int clear(Long memberId);

    Integer getCarProdutSum(Long memberId);

    List<OmsCartItem> listByIds(List<Long> ids);

    public List<CartItemStockDTO> getList(Long memberId);

    List<CartItemStockDTO> getCartItemStockByIds(Long id, List<Long> itemIds);

    void removeByIds(List<Long> ids);

    List<OmsCartItem> getCartList(Long memberId);
}
