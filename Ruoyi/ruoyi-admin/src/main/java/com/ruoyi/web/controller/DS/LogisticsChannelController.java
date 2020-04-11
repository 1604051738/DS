package com.ruoyi.web.controller.DS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.DS.domain.Product;
import com.ruoyi.DS.domain.Warehouse;
import com.ruoyi.DS.service.IProductService;
import com.ruoyi.DS.service.IWarehouseService;
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
import com.ruoyi.DS.domain.LogisticsChannel;
import com.ruoyi.DS.service.ILogisticsChannelService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.client.RestTemplate;

/**
 * 物流渠道Controller
 * 
 * @author ruoyi
 * @date 2020-01-04
 */
@Controller
@RequestMapping("/DS/channel")
public class LogisticsChannelController extends BaseController
{
    Class<?> class1 = null;
    private String prefix = "DS/channel";

    @Autowired
    private ILogisticsChannelService logisticsChannelService;

    @Autowired
    private IWarehouseService warehouseService;

    @RequiresPermissions("DS:channel:view")
    @GetMapping()
    public String channel()
    {
        return prefix + "/channel";
    }

    /**
     * 查询物流渠道列表
     */
    @RequiresPermissions("DS:channel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LogisticsChannel logisticsChannel)
    {
        startPage();
        List<LogisticsChannel> list = logisticsChannelService.selectLogisticsChannelList(logisticsChannel);
        return getDataTable(list);
    }

    /**
     * 导出物流渠道列表
     */
    @RequiresPermissions("DS:channel:export")
    @Log(title = "物流渠道", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LogisticsChannel logisticsChannel)
    {
        List<LogisticsChannel> list = logisticsChannelService.selectLogisticsChannelList(logisticsChannel);
        ExcelUtil<LogisticsChannel> util = new ExcelUtil<LogisticsChannel>(LogisticsChannel.class);
        return util.exportExcel(list, "channel");
    }

    /**
     * 新增物流渠道
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存物流渠道
     */
    @RequiresPermissions("DS:channel:add")
    @Log(title = "物流渠道", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LogisticsChannel logisticsChannel)
    {
        return toAjax(logisticsChannelService.insertLogisticsChannel(logisticsChannel));
    }

    /**
     * 修改物流渠道
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        LogisticsChannel logisticsChannel = logisticsChannelService.selectLogisticsChannelById(id);
        mmap.put("logisticsChannel", logisticsChannel);
        return prefix + "/edit";
    }

    /**
     * 修改保存物流渠道
     */
    @RequiresPermissions("DS:channel:edit")
    @Log(title = "物流渠道", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LogisticsChannel logisticsChannel)
    {
        return toAjax(logisticsChannelService.updateLogisticsChannel(logisticsChannel));
    }

    /**
     * 删除物流渠道
     */
    @RequiresPermissions("DS:channel:remove")
    @Log(title = "物流渠道", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(logisticsChannelService.deleteLogisticsChannelByIds(ids));
    }


    @GetMapping("/refresh")
    @ResponseBody
    public Map<Long, String>  ajax(){
        Map<Long, String> map = new HashMap<Long, String>();
        List<Warehouse> list = warehouseService.selectWarehouseList(null);
        for (Warehouse warehouse: list
             ) {
            map.put(warehouse.getId(), warehouse.getChinesename());
        }
        return map;
    }

}
