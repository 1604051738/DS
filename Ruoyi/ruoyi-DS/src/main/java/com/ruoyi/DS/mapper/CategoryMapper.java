package com.ruoyi.DS.mapper;

import com.ruoyi.DS.domain.Category;
import java.util.List;

/**
 * 目录分类Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-08
 */
public interface CategoryMapper 
{
    /**
     * 查询目录分类
     * 
     * @param id 目录分类ID
     * @return 目录分类
     */
    public Category selectCategoryById(Long id);

    /**
     * 查询目录分类列表
     * 
     * @param category 目录分类
     * @return 目录分类集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增目录分类
     * 
     * @param category 目录分类
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改目录分类
     * 
     * @param category 目录分类
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 删除目录分类
     * 
     * @param id 目录分类ID
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * 批量删除目录分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCategoryByIds(String[] ids);
}
