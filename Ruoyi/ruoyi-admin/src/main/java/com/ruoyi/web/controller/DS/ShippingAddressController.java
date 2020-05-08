package com.ruoyi.web.controller.DS;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.DS.domain.ShippingAddress;
import com.ruoyi.DS.service.IShippingAddressService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收货人信息Controller
 * 
 * @author ruoyi
 * @date 2020-05-06
 */
@Controller
@RequestMapping("/DS/shopping_address")
public class ShippingAddressController extends BaseController
{
    private String prefix = "DS/shopping_address";

    @Autowired
    private IShippingAddressService shippingAddressService;

    @RequiresPermissions("DS:shopping_address:view")
    @GetMapping()
    public String shopping_address()
    {
        return prefix + "/shopping_address";
    }

    /**
     * 查询收货人信息列表
     */
    @RequiresPermissions("DS:shopping_address:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ShippingAddress shippingAddress)
    {
        startPage();
        List<ShippingAddress> list = shippingAddressService.selectShippingAddressList(shippingAddress);
        return getDataTable(list);
    }

    /**
     * 导出收货人信息列表
     */
    @RequiresPermissions("DS:shopping_address:export")
    @Log(title = "收货人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ShippingAddress shippingAddress)
    {
        List<ShippingAddress> list = shippingAddressService.selectShippingAddressList(shippingAddress);
        ExcelUtil<ShippingAddress> util = new ExcelUtil<ShippingAddress>(ShippingAddress.class);
        return util.exportExcel(list, "shopping_address");
    }

    /**
     * 新增收货人信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存收货人信息
     */
    @RequiresPermissions("DS:shopping_address:add")
    @Log(title = "收货人信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ShippingAddress shippingAddress)
    {
        return toAjax(shippingAddressService.insertShippingAddress(shippingAddress));
    }

    /**
     * 修改收货人信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ShippingAddress shippingAddress = shippingAddressService.selectShippingAddressById(id);
        mmap.put("shippingAddress", shippingAddress);
        return prefix + "/edit";
    }

    /**
     * 修改保存收货人信息
     */
    @RequiresPermissions("DS:shopping_address:edit")
    @Log(title = "收货人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ShippingAddress shippingAddress)
    {
        return toAjax(shippingAddressService.updateShippingAddress(shippingAddress));
    }

    /**
     * 删除收货人信息
     */
    @RequiresPermissions("DS:shopping_address:remove")
    @Log(title = "收货人信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(shippingAddressService.deleteShippingAddressByIds(ids));
    }
}
