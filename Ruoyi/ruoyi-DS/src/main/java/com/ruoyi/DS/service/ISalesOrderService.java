package com.ruoyi.DS.service;

import com.ruoyi.DS.domain.SalesOrder;
import java.util.List;

/**
 * 销售订单Service接口
 * 
 * @author admin
 * @date 2020-04-23
 */
public interface ISalesOrderService 
{
    /**
     * 查询销售订单
     * 
     * @param id 销售订单ID
     * @return 销售订单
     */
    public SalesOrder selectSalesOrderById(Integer id);

    /**
     * 查询销售订单列表
     * 
     * @param salesOrder 销售订单
     * @return 销售订单集合
     */
    public List<SalesOrder> selectSalesOrderList(SalesOrder salesOrder);

    /**
     * 新增销售订单
     * 
     * @param salesOrder 销售订单
     * @return 结果
     */
    public int insertSalesOrder(SalesOrder salesOrder);

    /**
     * 修改销售订单
     * 
     * @param salesOrder 销售订单
     * @return 结果
     */
    public int updateSalesOrder(SalesOrder salesOrder);

    /**
     * 批量删除销售订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSalesOrderByIds(String ids);

    /**
     * 删除销售订单信息
     * 
     * @param id 销售订单ID
     * @return 结果
     */
    public int deleteSalesOrderById(Integer id);



    /**
     * 查询时间段订单
     * @param firstDay
     * @param lastDay
     * @return
     */
    List<SalesOrder> queryProfit(String firstDay, String lastDay);
}
