package com.ruoyi.DS.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.DS.mapper.SalesOrderMapper;
import com.ruoyi.DS.domain.SalesOrder;
import com.ruoyi.DS.service.ISalesOrderService;
import com.ruoyi.common.core.text.Convert;

/**
 * 销售订单Service业务层处理
 * 
 * @author admin
 * @date 2020-04-23
 */
@Service
public class SalesOrderServiceImpl implements ISalesOrderService 
{
    @Autowired
    private SalesOrderMapper salesOrderMapper;

    /**
     * 查询销售订单
     * 
     * @param id 销售订单ID
     * @return 销售订单
     */
    @Override
    public SalesOrder selectSalesOrderById(Integer id)
    {
        return salesOrderMapper.selectSalesOrderById(id);
    }

    /**
     * 查询销售订单列表
     * 
     * @param salesOrder 销售订单
     * @return 销售订单
     */
    @Override
    public List<SalesOrder> selectSalesOrderList(SalesOrder salesOrder)
    {
        return salesOrderMapper.selectSalesOrderList(salesOrder);
    }

    /**
     * 新增销售订单
     * 
     * @param salesOrder 销售订单
     * @return 结果
     */
    @Override
    public int insertSalesOrder(SalesOrder salesOrder)
    {
        return salesOrderMapper.insertSalesOrder(salesOrder);
    }

    /**
     * 修改销售订单
     * 
     * @param salesOrder 销售订单
     * @return 结果
     */
    @Override
    public int updateSalesOrder(SalesOrder salesOrder)
    {
        return salesOrderMapper.updateSalesOrder(salesOrder);
    }

    /**
     * 删除销售订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderByIds(String ids)
    {
        return salesOrderMapper.deleteSalesOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除销售订单信息
     * 
     * @param id 销售订单ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderById(Integer id)
    {
        return salesOrderMapper.deleteSalesOrderById(id);
    }

    @Override
    public List<SalesOrder> queryProfit(String firstDay, String lastDay) {
        return salesOrderMapper.queryProfit(firstDay, lastDay);
    }
}
