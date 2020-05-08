package com.ruoyi.DS.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.DS.mapper.OrderSkuMapper;
import com.ruoyi.DS.domain.OrderSku;
import com.ruoyi.DS.service.IOrderSkuService;
import com.ruoyi.common.core.text.Convert;

/**
 * 单品项Service业务层处理
 * 
 * @author admin
 * @date 2020-04-24
 */
@Service
public class OrderSkuServiceImpl implements IOrderSkuService 
{
    @Autowired
    private OrderSkuMapper orderSkuMapper;

    /**
     * 查询单品项
     * 
     * @param id 单品项ID
     * @return 单品项
     */
    @Override
    public OrderSku selectOrderSkuById(Integer id)
    {
        return orderSkuMapper.selectOrderSkuById(id);
    }

    /**
     * 查询单品项列表
     * 
     * @param orderSku 单品项
     * @return 单品项
     */
    @Override
    public List<OrderSku> selectOrderSkuList(OrderSku orderSku)
    {
        return orderSkuMapper.selectOrderSkuList(orderSku);
    }

    /**
     * 新增单品项
     * 
     * @param orderSku 单品项
     * @return 结果
     */
    @Override
    public int insertOrderSku(OrderSku orderSku)
    {
        return orderSkuMapper.insertOrderSku(orderSku);
    }

    /**
     * 修改单品项
     * 
     * @param orderSku 单品项
     * @return 结果
     */
    @Override
    public int updateOrderSku(OrderSku orderSku)
    {
        return orderSkuMapper.updateOrderSku(orderSku);
    }

    /**
     * 删除单品项对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrderSkuByIds(String ids)
    {
        return orderSkuMapper.deleteOrderSkuByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除单品项信息
     * 
     * @param id 单品项ID
     * @return 结果
     */
    @Override
    public int deleteOrderSkuById(Integer id)
    {
        return orderSkuMapper.deleteOrderSkuById(id);
    }
}
