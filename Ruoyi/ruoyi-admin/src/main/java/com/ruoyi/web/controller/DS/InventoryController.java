package com.ruoyi.web.controller.DS;

import java.util.*;

import com.ruoyi.DS.domain.Skuproduct;
import com.ruoyi.DS.domain.Warehouse;
import com.ruoyi.DS.service.ISkuproductService;
import com.ruoyi.DS.service.IWarehouseService;
import com.ruoyi.DS.utils.DSUtils;
import com.ruoyi.common.utils.security.PermissionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.DS.domain.Inventory;
import com.ruoyi.DS.service.IInventoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库存Controller
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
@Transactional(rollbackFor = Exception.class)
@Controller
@RequestMapping("/DS/inventory")
public class InventoryController extends BaseController
{
    private String prefix = "DS/inventory";

    @Autowired
    private IInventoryService inventoryService;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private ISkuproductService skuproductService;

    @RequiresPermissions("DS:inventory:view")
    @GetMapping()
    public String inventory()
    {
        return prefix + "/inventory";
    }

    /**
     * 查询库存列表
     */
    @RequiresPermissions("DS:inventory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Inventory inventory)
    {
        startPage();
        List<Inventory> list = inventoryService.selectInventoryList(inventory);
        return getDataTable(list);
    }

    /**
     * 导出库存列表
     */
    @RequiresPermissions("DS:inventory:export")
    @Log(title = "库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Inventory inventory)
    {
        List<Inventory> list = inventoryService.selectInventoryList(inventory);
        ExcelUtil<Inventory> util = new ExcelUtil<Inventory>(Inventory.class);
        return util.exportExcel(list, "inventory");
    }

    /**
     * 新增库存
     */
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }



    /**
     * 新增保存库存
     */
    @RequiresPermissions("DS:inventory:add")
    @Log(title = "库存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Inventory inventory)
    {
        Inventory inventory1 = inventoryService.selectInventoryBySKU(inventory.getSku());
        if (inventory1 == null){
            Date date = new Date();
            inventory.setCreateTime(date);
            String userName = (String) PermissionUtils.getPrincipalProperty("userName");
            inventory = DSUtils.refreshInventory(inventory);
            inventory.setCreateBy(userName);
            inventory.setUpdateBy("暂未更新");
            return toAjax(inventoryService.insertInventory(inventory));
        }else
            return error(inventory.getSku()+"已经存在库存信息");

    }

    /**
     * 修改库存
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Inventory inventory = inventoryService.selectInventoryById(id);
        mmap.put("inventory", inventory);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存
     */
    @RequiresPermissions("DS:inventory:edit")
    @Log(title = "库存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Inventory inventory)
    {
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        inventory = DSUtils.refreshInventory(inventory);
        inventory.setUpdateBy(userName);
        return toAjax(inventoryService.updateInventory(inventory));
    }

    /**
     * 删除库存
     */
    @RequiresPermissions("DS:inventory:remove")
    @Log(title = "库存", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(inventoryService.deleteInventoryByIds(ids));
    }


    /*
        ajax获取 option列表
     */
    @GetMapping("/refreshWarehouse")
    @ResponseBody
    public List<Warehouse> refreshwarehouse(){
        List<Warehouse> data = new ArrayList<Warehouse>();
        List<Warehouse> warehouses = warehouseService.selectWarehouseList(null);
        for (Warehouse wa:
                warehouses) {
            data.add(wa);
        }
        return data;
    }

    @GetMapping("/refreshSKU")
    @ResponseBody
    public List<Skuproduct> refreshSKU(){
        List<Skuproduct> data = new ArrayList<Skuproduct>();
        List<Skuproduct> skuproducts = skuproductService.selectSkuproductList(null);
        for (Skuproduct sku:
                skuproducts) {
            data.add(sku);
        }
        return data;
    }

    @GetMapping("/getName/{id}")
    @ResponseBody
    public List<String> getName(@PathVariable Long id){
        List<String> data = new ArrayList<>();
        data.add( warehouseService.selectWarehouseById(inventoryService.selectInventoryById(id).getWarehouse()).getChinesename() );
        data.add( skuproductService.selectSkuproductById(inventoryService.selectInventoryById(id).getSku()).getCode());
        return data;
    }


    @PostMapping("/getSku")
    @ResponseBody
    public Integer getSku(@RequestParam(name = "value") String skucode){
        Skuproduct skuproduct = new Skuproduct();
        String code = skucode.split("-")[0];
        skuproduct.setCode(code);
        List<Skuproduct> skuproduct1 = skuproductService.selectSkuproductList(skuproduct);
        if (skuproduct1.size() == 0){
            throw new RuntimeException("查询的指定Sku不存在");
        }
        Integer id = skuproduct1.get(0).getId();
        return id;
    }
}
