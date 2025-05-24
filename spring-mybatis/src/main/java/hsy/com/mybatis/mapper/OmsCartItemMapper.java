package hsy.com.mybatis.mapper;


import hsy.com.mybatis.dto.CartItemStockDTO;
import hsy.com.mybatis.entity.OmsCartItem;
import hsy.com.mybatis.entity.OmsCartItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OmsCartItemMapper {
    long countByExample(OmsCartItemExample example);

    int deleteByExample(OmsCartItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsCartItem record);

    int insertSelective(OmsCartItem record);

    List<OmsCartItem> selectByExample(OmsCartItemExample example);

    OmsCartItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsCartItem record, @Param("example") OmsCartItemExample example);

    int updateByExample(@Param("record") OmsCartItem record, @Param("example") OmsCartItemExample example);

    int updateByPrimaryKeySelective(OmsCartItem record);

    int updateByPrimaryKey(OmsCartItem record);

    /**
     * 更新购物车数量
     * @param id
     * @return
     */
    List<Map<String, Object>> selectMaps(Long id);

    List<CartItemStockDTO> getCartItemStock(Long id);

    List<CartItemStockDTO> getCartItemStockByIds(@Param("id") Long id, @Param("itemIds")List<Long> itemIds);

    void removeByIds(@Param("ids")List<Long> ids);

    List<OmsCartItem> listByIds(@Param("ids")List<Long> ids);

    List<OmsCartItem> getCartItem(Long id);
}