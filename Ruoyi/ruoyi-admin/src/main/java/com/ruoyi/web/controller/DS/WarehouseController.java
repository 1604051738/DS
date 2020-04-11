package com.ruoyi.web.controller.DS;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.security.PermissionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.DS.domain.Warehouse;
import com.ruoyi.DS.service.IWarehouseService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 仓库Controller
 * 
 * @author ruoyi
 * @date 2019-12-25
 */
@Controller
@RequestMapping("/DS/warehouse")
@Transactional(rollbackFor = Exception.class)
public class WarehouseController extends BaseController
{
    private String prefix = "DS/warehouse";

    @Autowired
    private IWarehouseService warehouseService;

    @RequiresPermissions("DS:warehouse:view")
    @GetMapping()
    public String warehouse()
    {
        return prefix + "/warehouse";
    }

    /**
     * 查询仓库列表
     */
    @RequiresPermissions("DS:warehouse:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Warehouse warehouse)
    {
        startPage();
        List<Warehouse> list = warehouseService.selectWarehouseList(warehouse);
        return getDataTable(list);
    }

    /**
     * 导出仓库列表
     */
    @RequiresPermissions("DS:warehouse:export")
    @Log(title = "仓库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Warehouse warehouse)
    {
        List<Warehouse> list = warehouseService.selectWarehouseList(warehouse);
        ExcelUtil<Warehouse> util = new ExcelUtil<Warehouse>(Warehouse.class);
        return util.exportExcel(list, "warehouse");
    }

    /**
     * 新增仓库
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存仓库
     */
    @RequiresPermissions("DS:warehouse:add")
    @Log(title = "仓库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Warehouse warehouse)
    {
        Date date = new Date();
        warehouse.setCreateTime(date);
        warehouse.setUpdateTime(date);
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        warehouse.setCreateBy(userName);
        warehouse.setUpdateBy("暂未更新");
        return toAjax(warehouseService.insertWarehouse(warehouse));
    }

    /**
     * 修改仓库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Warehouse warehouse = warehouseService.selectWarehouseById(id);
        mmap.put("warehouse", warehouse);
        return prefix + "/edit";
    }

    /**
     * 修改保存仓库
     */
    @RequiresPermissions("DS:warehouse:edit")
    @Log(title = "仓库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Warehouse warehouse)
    {
        Date date = new Date();
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        warehouse.setUpdateBy(userName);
        warehouse.setUpdateTime(date);
        return toAjax(warehouseService.updateWarehouse(warehouse));
    }

    /**
     * 删除仓库
     */
    @RequiresPermissions("DS:warehouse:remove")
    @Log(title = "仓库", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(warehouseService.deleteWarehouseByIds(ids));
    }
}
