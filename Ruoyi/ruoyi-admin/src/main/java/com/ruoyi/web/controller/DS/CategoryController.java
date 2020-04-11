package com.ruoyi.web.controller.DS;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.utils.security.PermissionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.DS.domain.Category;
import com.ruoyi.DS.service.ICategoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 目录分类Controller
 * 
 * @author ruoyi
 * @date 2019-12-25
 */
@Transactional(rollbackFor = Exception.class)
@Controller
@RequestMapping("/DS/category")
public class CategoryController extends BaseController
{
    private String prefix = "DS/category";

    @Autowired
    private ICategoryService categoryService;

    @RequiresPermissions("DS:category:view")
    @GetMapping()
    public String category()
    {
        return prefix + "/category";
    }

    /**
     * 查询目录分类列表
     */
    @RequiresPermissions("DS:category:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Category category)
    {
        startPage();
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 导出目录分类列表
     */
    @RequiresPermissions("DS:category:export")
    @Log(title = "目录分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        return util.exportExcel(list, "category");
    }

    /**
     * 新增目录分类
     */
    @GetMapping("/add")
    public String add(Model model)
    {
        List<Category> categories = categoryService.selectCategoryList(null);
        model.addAttribute("list",categories);
        return prefix + "/add";
    }

    /**
     * 新增保存目录分类
     */
    @RequiresPermissions("DS:category:add")
    @Log(title = "目录分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Category category)
    {
        Date date = new Date();
        category.setCreateTime(date);
        category.setUpdateTime(date);
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        category.setCreateBy(userName);
        category.setUpdateBy("暂未更新");
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改目录分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap,Model model)
    {
        List<Category> categories = categoryService.selectCategoryList(null);
        model.addAttribute("list",categories);
        Category category = categoryService.selectCategoryById(id);
        mmap.put("category", category);
        return prefix + "/edit";
    }

    /**
     * 修改保存目录分类
     */
    @RequiresPermissions("DS:category:edit")
    @Log(title = "目录分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Category category)
    {
        Date date = new Date();
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        category.setUpdateBy(userName);
        category.setUpdateTime(date);
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除目录分类
     */
    @RequiresPermissions("DS:category:remove")
    @Log(title = "目录分类", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(categoryService.deleteCategoryByIds(ids));
    }
}
