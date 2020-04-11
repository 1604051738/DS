package com.ruoyi.web.controller.DS;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.DS.domain.Product;
import com.ruoyi.DS.service.IProductService;
import com.ruoyi.DS.utils.SkuCodeBuilder;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.security.PermissionUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.DS.domain.Skuproduct;
import com.ruoyi.DS.service.ISkuproductService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * SKU单品Controller
 *
 * @author ruoyi
 * @date 2019-12-26
 */
@Transactional(rollbackFor = Exception.class)
@Controller
@RequestMapping("/DS/skuproduct")
public class SkuproductController extends BaseController
{
    private String prefix = "DS/skuproduct";

    @Autowired
    private ISkuproductService skuproductService;

    @Autowired
    private IProductService productService;

    @Value("${ruoyi.profile}")
    private String path;


    @RequiresPermissions("DS:skuproduct:view")
    @GetMapping()
    public String skuproduct()
    {
        return prefix + "/skuproduct";
    }

    /**
     * 查询SKU单品列表
     */
    @RequiresPermissions("DS:skuproduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Skuproduct skuproduct)
    {
        startPage();
        List<Skuproduct> list = skuproductService.selectSkuproductList(skuproduct);
        return getDataTable(list);
    }

    /**
     * 导出SKU单品列表
     */
    @RequiresPermissions("DS:skuproduct:export")
    @Log(title = "SKU单品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Skuproduct skuproduct)
    {
        List<Skuproduct> list = skuproductService.selectSkuproductList(skuproduct);
        ExcelUtil<Skuproduct> util = new ExcelUtil<Skuproduct>(Skuproduct.class);
        return util.exportExcel(list, "skuproduct");
    }

    /**
     * 新增SKU单品
     */
    @GetMapping("/add")
    public String add(Model model)
    {
        List<Product> list = productService.selectProductList(null);
        model.addAttribute("list", list);
        return prefix + "/add";
    }

    /**
     * 新增保存SKU单品
     */
    @RequiresPermissions("DS:skuproduct:add")
    @Log(title = "SKU单品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file, Skuproduct skuproduct) throws IOException
    {
        String code = SkuCodeBuilder.getSkuCode(skuproduct.getProduct(), skuproductService);
        // 上传文件路径
        skuproduct.setSkucode(code);
        String filePath = Global.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        skuproduct.setFilepath(fileName);
        return toAjax(skuproductService.insertSkuproduct(skuproduct));
    }

    /**
     * 修改SKU单品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap,Model model)
    {
        List<Product> list = productService.selectProductList(null);
        model.addAttribute("list", list);
        Skuproduct skuproduct = skuproductService.selectSkuproductById(id);
        Product product = productService.selectProductById(skuproduct.getProduct());
        model.addAttribute("product",product);
        mmap.put("skuproduct", skuproduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存SKU单品
     */
    @RequiresPermissions("DS:skuproduct:edit")
    @Log(title = "SKU单品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestParam("file") Object file, Model model, Skuproduct skuproduct) throws IOException {
        String fileName;
        if (file.getClass() != String.class) {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            fileName = FileUploadUtils.upload(filePath, (MultipartFile) file);
        }
        else
            fileName = (String)file;
        skuproduct.setFilepath(fileName);
        model.addAttribute("path",path);
        return toAjax(skuproductService.updateSkuproduct(skuproduct));
    }

    /**
     * 删除SKU单品
     */
    @RequiresPermissions("DS:skuproduct:remove")
    @Log(title = "SKU单品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(skuproductService.deleteSkuproductByIds(ids));
    }


    @RequiresPermissions("DS:skuproduct:delete")
    @Log(title = "SKU单品", businessType = BusinessType.DELETE)
    @PostMapping( "/delete")
    @ResponseBody
    public Boolean delete(String ids)
    {
        Boolean result = false;
        Integer data = skuproductService.deleteSkuproductByIds(ids);
        if (data != 0){
            result = true;
        }
        return result;
    }

    /*
        ajax 提供option
     */
    @GetMapping("/refresh")
    @ResponseBody
    public Map<Long, String> refresh(){
        Map<Long, String> map = new HashMap<Long, String>();
        List<Product> products = productService.selectProductList(null);
        for (Product i:
             products) {
            map.put(i.getCode(),i.getChinesename());
        }
        return map;
    }


}
