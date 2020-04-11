package com.ruoyi.web.controller.DS;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.DS.domain.LogisticsChannel;
import com.ruoyi.DS.domain.Warehouse;
import com.ruoyi.DS.service.ILogisticsChannelService;
import com.ruoyi.DS.service.IWarehouseService;
import com.ruoyi.DS.service.impl.RandomImpl;
import com.ruoyi.DS.utils.OrderNumberBuilder;
import com.ruoyi.common.utils.security.PermissionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
 * @author ruoyi
 * @date 2019-12-30
 */
@Controller
@Transactional(rollbackFor = Exception.class)

@RequestMapping("/DS/order")
public class SalesOrderController extends BaseController
{
    private String prefix = "DS/order";

    @Autowired
    private ISalesOrderService salesOrderService;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private RandomImpl random;

    @Autowired
    private ILogisticsChannelService logisticsChannelService;


    @RequiresPermissions("DS:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询销售订单列表
     */
    @RequiresPermissions("DS:order:list")
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
    @RequiresPermissions("DS:order:export")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SalesOrder salesOrder)
    {
        List<SalesOrder> list = salesOrderService.selectSalesOrderList(salesOrder);
        ExcelUtil<SalesOrder> util = new ExcelUtil<SalesOrder>(SalesOrder.class);
        return util.exportExcel(list, "order");
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

    @RequiresPermissions("DS:order:add")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SalesOrder salesOrder)
    {
        String orderId = OrderNumberBuilder.getOrderNumber();
        OrderNumberBuilder.setRANDOMNUMER(OrderNumberBuilder.getRANDOMNUMER()+1);
        random.setNumber(OrderNumberBuilder.getRANDOMNUMER());
        salesOrder.setSaleId(orderId);
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        salesOrder.setCreateBy(userName);
        return toAjax(salesOrderService.insertSalesOrder(salesOrder));
    }

    /**
     * 修改销售订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SalesOrder salesOrder = salesOrderService.selectSalesOrderById(id);
        mmap.put("salesOrder", salesOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售订单
     */
    @RequiresPermissions("DS:order:edit")
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
    @RequiresPermissions("DS:order:remove")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(salesOrderService.deleteSalesOrderByIds(ids));
    }


//    @GetMapping("/refreshClient")
//    @ResponseBody
//    public Map<Long, String> refreshClient(){
//        Map<Long, String> map = new HashMap<Long, String>();
//        List<Client> clients = clientService.selectClientList(null);
//        for (Client i:
//             clients) {
//            map.put(i.getId(), i.getName());
//        }
//
//        return map;
//    }

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
