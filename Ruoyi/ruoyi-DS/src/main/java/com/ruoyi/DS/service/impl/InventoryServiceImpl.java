package com.ruoyi.DS.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.DS.mapper.InventoryMapper;
import com.ruoyi.DS.domain.Inventory;
import com.ruoyi.DS.service.IInventoryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 库存Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
@Service
public class InventoryServiceImpl implements IInventoryService 
{
    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * 查询库存
     * 
     * @param id 库存ID
     * @return 库存
     */
    @Override
    public Inventory selectInventoryById(Long id)
    {
        return inventoryMapper.selectInventoryById(id);
    }

    /**
     * 查询库存列表
     * 
     * @param inventory 库存
     * @return 库存
     */
    @Override
    public List<Inventory> selectInventoryList(Inventory inventory)
    {
        return inventoryMapper.selectInventoryList(inventory);
    }

    /**
     * 新增库存
     * 
     * @param inventory 库存
     * @return 结果
     */
    @Override
    public int insertInventory(Inventory inventory)
    {
        return inventoryMapper.insertInventory(inventory);
    }

    /**
     * 修改库存
     * 
     * @param inventory 库存
     * @return 结果
     */
    @Override
    public int updateInventory(Inventory inventory)
    {
        return inventoryMapper.updateInventory(inventory);
    }

    /**
     * 删除库存对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInventoryByIds(String ids)
    {
        return inventoryMapper.deleteInventoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存信息
     * 
     * @param id 库存ID
     * @return 结果
     */
    @Override
    public int deleteInventoryById(Long id)
    {
        return inventoryMapper.deleteInventoryById(id);
    }

    @Override
    public Inventory selectInventoryBySKU(String sku) {
        return inventoryMapper.selectInventoryBySKU(sku);
    }
}
