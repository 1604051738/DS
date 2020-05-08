package com.ruoyi.web.controller.DS;

import java.util.List;

import com.ruoyi.DS.domain.Skuproduct;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.DS.domain.OrderSku;
import com.ruoyi.DS.service.IOrderSkuService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 单品项Controller
 * 
 * @author admin
 * @date 2020-04-24
 */
@Controller
@RequestMapping("/DS/order_sku")
public class OrderSkuController extends BaseController {
    private String prefix = "DS/order_sku";

    @Autowired
    private IOrderSkuService orderSkuService;

    @RequiresPermissions("DS:order_sku:view")
    @GetMapping()
    public String order_sku() {
        return prefix + "/order_sku";
    }

    /**
     * 查询单品项列表
     */
    @RequiresPermissions("DS:order_sku:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrderSku orderSku) {
        startPage();
        List<OrderSku> list = orderSkuService.selectOrderSkuList(orderSku);
        return getDataTable(list);
    }

    /**
     * 导出单品项列表
     */
    @RequiresPermissions("DS:order_sku:export")
    @Log(title = "单品项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrderSku orderSku) {
        List<OrderSku> list = orderSkuService.selectOrderSkuList(orderSku);
        ExcelUtil<OrderSku> util = new ExcelUtil<OrderSku>(OrderSku.class);
        return util.exportExcel(list, "order_sku");
    }

    /**
     * 新增单品项
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存单品项
     */
    @RequiresPermissions("DS:order_sku:add")
    @Log(title = "单品项", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OrderSku orderSku) {
        return toAjax(orderSkuService.insertOrderSku(orderSku));
    }

    /**
     * 修改单品项
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        OrderSku orderSku = orderSkuService.selectOrderSkuById(id);
        mmap.put("orderSku", orderSku);
        return prefix + "/edit";
    }

    /**
     * 修改保存单品项
     */
    @RequiresPermissions("DS:order_sku:edit")
    @Log(title = "单品项", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OrderSku orderSku) {
        return toAjax(orderSkuService.updateOrderSku(orderSku));
    }

    /**
     * 删除单品项
     */
    @RequiresPermissions("DS:order_sku:remove")
    @Log(title = "单品项", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(orderSkuService.deleteOrderSkuByIds(ids));
    }




}