package com.ruoyi.DS.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.DS.mapper.CategoryMapper;
import com.ruoyi.DS.domain.Category;
import com.ruoyi.DS.service.ICategoryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 目录分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-08
 */
@Service
public class CategoryServiceImpl implements ICategoryService 
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询目录分类
     * 
     * @param id 目录分类ID
     * @return 目录分类
     */
    @Override
    public Category selectCategoryById(Long id)
    {
        return categoryMapper.selectCategoryById(id);
    }

    /**
     * 查询目录分类列表
     * 
     * @param category 目录分类
     * @return 目录分类
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增目录分类
     * 
     * @param category 目录分类
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        category.setCreateTime(DateUtils.getNowDate());
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改目录分类
     * 
     * @param category 目录分类
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        category.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.updateCategory(category);
    }

    /**
     * 删除目录分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(String ids)
    {
        return categoryMapper.deleteCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除目录分类信息
     * 
     * @param id 目录分类ID
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long id)
    {
        return categoryMapper.deleteCategoryById(id);
    }

    /**
     * 查询目录分类树列表
     * 
     * @return 所有目录分类信息
     */
    @Override
    public List<Ztree> selectCategoryTree()
    {
        List<Category> categoryList = categoryMapper.selectCategoryList(new Category());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (Category category : categoryList)
        {
            if(category.getStatus() == 0){
                Ztree ztree = new Ztree();
                ztree.setId(category.getId());
                ztree.setpId(category.getParentdirectory());
                ztree.setName(category.getName());
                ztree.setTitle(category.getName());
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }
}
