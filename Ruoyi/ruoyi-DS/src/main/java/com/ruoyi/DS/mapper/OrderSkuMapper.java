package com.ruoyi.DS.mapper;

import com.ruoyi.DS.domain.OrderSku;
import java.util.List;

/**
 * 单品项Mapper接口
 * 
 * @author admin
 * @date 2020-04-24
 */
public interface OrderSkuMapper 
{
    /**
     * 查询单品项
     * 
     * @param id 单品项ID
     * @return 单品项
     */
    public OrderSku selectOrderSkuById(Integer id);

    /**
     * 查询单品项列表
     * 
     * @param orderSku 单品项
     * @return 单品项集合
     */
    public List<OrderSku> selectOrderSkuList(OrderSku orderSku);

    /**
     * 新增单品项
     * 
     * @param orderSku 单品项
     * @return 结果
     */
    public int insertOrderSku(OrderSku orderSku);

    /**
     * 修改单品项
     * 
     * @param orderSku 单品项
     * @return 结果
     */
    public int updateOrderSku(OrderSku orderSku);

    /**
     * 删除单品项
     * 
     * @param id 单品项ID
     * @return 结果
     */
    public int deleteOrderSkuById(Integer id);

    /**
     * 批量删除单品项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderSkuByIds(String[] ids);
}
