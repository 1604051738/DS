package com.ruoyi.web.controller.DS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.DS.domain.LogisticsChannel;
import com.ruoyi.DS.domain.OrderSku;
import com.ruoyi.DS.domain.Warehouse;
import com.ruoyi.DS.service.ILogisticsChannelService;
import com.ruoyi.DS.service.IOrderSkuService;
import com.ruoyi.DS.service.IWarehouseService;
import com.ruoyi.DS.service.impl.RandomImpl;
import com.ruoyi.DS.utils.JsonUtil;
import com.ruoyi.DS.utils.OrderNumberBuilder;
import com.ruoyi.common.utils.security.PermissionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.DS.domain.SalesOrder;
import com.ruoyi.DS.service.ISalesOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售订单Controller
 * 
 * @author admin
 * @date 2020-04-23
 */
@Controller
@RequestMapping("/DS/sales_order")
public class SalesOrderController extends BaseController
{
    private String prefix = "DS/sales_order";

    @Autowired
    private ISalesOrderService salesOrderService;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private RandomImpl random;

    @Autowired
    private ILogisticsChannelService logisticsChannelService;

    @Autowired
    private IOrderSkuService iOrderSkuService;


    @RequiresPermissions("DS:sales_order:view")
    @GetMapping()
    public String sales_order()
    {
        return prefix + "/sales_order";
    }

    /**
     * 查询销售订单列表
     */
    @RequiresPermissions("DS:sales_order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SalesOrder salesOrder)
    {
        startPage();
        List<SalesOrder> list = salesOrderService.selectSalesOrderList(salesOrder);
        return getDataTable(list);
    }

    /**
     * 导出销售订单列表
     */
    @RequiresPermissions("DS:sales_order:export")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SalesOrder salesOrder)
    {
        List<SalesOrder> list = salesOrderService.selectSalesOrderList(salesOrder);
        ExcelUtil<SalesOrder> util = new ExcelUtil<SalesOrder>(SalesOrder.class);
        return util.exportExcel(list, "sales_order");
    }

    /**
     * 新增销售订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }


    /**
     * 新增保存销售订单
     */
    @RequiresPermissions("DS:sales_order:add")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("salesOrderMessage") String salesOrder, @RequestParam(value = "orderSkuMessage",required = false)String[] orderSku)
    {
        //salesorder（销售订单），ordersku(单品项)    json转pojo
        SalesOrder salesOrder1 = JsonUtil.fromJson(salesOrder, SalesOrder.class);
        if (orderSku != null) {
            for (int i = 1; i < orderSku.length; i++) {
                OrderSku order = JsonUtil.fromJson(orderSku[i], OrderSku.class);
                order.setSalesOrderId(salesOrder1.getId());
                iOrderSkuService.insertOrderSku(order);
            }
        }

        //salesorder订单保存
        String orderId = OrderNumberBuilder.getOrderNumber();
        OrderNumberBuilder.setRANDOMNUMER(OrderNumberBuilder.getRANDOMNUMER()+1);
        //设置订单编号
        random.setNumber(OrderNumberBuilder.getRANDOMNUMER());
        salesOrder1.setSaleId(orderId);
        //自动设置操作者用户名
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        salesOrder1.setCreateBy(userName);
        return toAjax(salesOrderService.insertSalesOrder(salesOrder1));
    }

    /**
     * 修改销售订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SalesOrder salesOrder = salesOrderService.selectSalesOrderById(id);
        mmap.put("salesOrder", salesOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售订单
     */
    @RequiresPermissions("DS:sales_order:edit")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SalesOrder salesOrder)
    {
        return toAjax(salesOrderService.updateSalesOrder(salesOrder));
    }

    /**
     * 删除销售订单
     */
    @RequiresPermissions("DS:sales_order:remove")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(salesOrderService.deleteSalesOrderByIds(ids));
    }


    /**
     * 获取仓库id， name
     * @return
     */
    @GetMapping("/refreshWareHouse")
    @ResponseBody
    public Map<Long, String> refreshWareHouse(){
        Map<Long, String> map = new HashMap<Long, String>();
        List<Warehouse> warehouses = warehouseService.selectWarehouseList(null);
        for (Warehouse i:
                warehouses) {
            map.put(i.getId(), i.getChinesename());
        }

        return map;
    }

    /**
     * 获取物流渠道id， name
     * @return
     */
    @GetMapping("/refreshChannel")
    @ResponseBody
    public Map<Long, String> refreshChannel(){
        Map<Long, String> map = new HashMap<Long, String>();
        List<LogisticsChannel> logisticsChannels = logisticsChannelService.selectLogisticsChannelList(null);
        for (LogisticsChannel i:
                logisticsChannels) {
            map.put(i.getId(), i.getLogisticsName());
        }

        return map;
    }
}
