package com.ruoyi.DS.service;

import com.ruoyi.DS.domain.Inventory;
import java.util.List;

/**
 * 库存Service接口
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
public interface IInventoryService 
{
    /**
     * 查询库存
     * 
     * @param id 库存ID
     * @return 库存
     */
    public Inventory selectInventoryById(Long id);

    /**
     * 查询库存列表
     * 
     * @param inventory 库存
     * @return 库存集合
     */
    public List<Inventory> selectInventoryList(Inventory inventory);

    /**
     * 新增库存
     * 
     * @param inventory 库存
     * @return 结果
     */
    public int insertInventory(Inventory inventory);

    /**
     * 修改库存
     * 
     * @param inventory 库存
     * @return 结果
     */
    public int updateInventory(Inventory inventory);

    /**
     * 批量删除库存
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInventoryByIds(String ids);

    /**
     * 删除库存信息
     * 
     * @param id 库存ID
     * @return 结果
     */
    public int deleteInventoryById(Long id);


    public Inventory selectInventoryBySKU(String sku);
}
